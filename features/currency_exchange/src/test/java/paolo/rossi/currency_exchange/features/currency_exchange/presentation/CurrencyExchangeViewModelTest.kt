package paolo.rossi.currency_exchange.features.currency_exchange.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import paolo.rossi.currency_exchange.rules.CoroutineTestRule
import paolo.rossi.currency_exchange.TestDataProvider
import paolo.rossi.currency_exchange.domain.entity.Currency
import paolo.rossi.currency_exchange.domain.entity.ExchangeRate
import paolo.rossi.currency_exchange.domain.use_case.GetCurrenciesUseCase
import paolo.rossi.currency_exchange.domain.use_case.GetExchangeRatesOfCurrencyUseCase
import paolo.rossi.currency_exchange.features.dto.CurrencyModel
import paolo.rossi.currency_exchange.features.dto.ExchangeRateModel
import paolo.rossi.currency_exchange.features.mapper.CurrencyExchangeFailureMapper
import paolo.rossi.currency_exchange.features.mapper.CurrencyMapper
import paolo.rossi.currency_exchange.features.mapper.ExchangeRateMapper
import paolo.rossi.currency_exchange.features.views.CurrencyExchangeViewModel
import paolo.rossi.currency_exchange.features.views.CurrencyExchangeViewState


@ExperimentalCoroutinesApi
class CurrencyExchangeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val getCurrenciesUseCaseMock by lazy { mockk<GetCurrenciesUseCase>() }
    private val getExchangeRatesOfCurrencyUseCaseMock by lazy { mockk<GetExchangeRatesOfCurrencyUseCase>() }

    private val currencyMapper by lazy { CurrencyMapper() }
    private val exchangeRateMapper by lazy { ExchangeRateMapper() }
    private val failureMapper by lazy { mockk<CurrencyExchangeFailureMapper>(relaxed = true) }

    private lateinit var viewModel: CurrencyExchangeViewModel
    private val observer = mockk<Observer<CurrencyExchangeViewState>>(relaxed = true)

    @Before
    fun setup() {
        viewModel = CurrencyExchangeViewModel(getCurrenciesUseCaseMock, getExchangeRatesOfCurrencyUseCaseMock,
            currencyMapper, exchangeRateMapper, failureMapper)
        viewModel.state.observeForever(observer)
    }

    @Test
    fun `check if the currency mapper works correctly`() {
        // Given
        val dummy = TestDataProvider.currencyDomain

        // When
        val mapped = currencyMapper.map(dummy)

        // Then
        dummy shouldBeInstanceOf Currency::class
        mapped shouldBeInstanceOf CurrencyModel::class
        mapped.abbreviation shouldBeEqualTo dummy.abbreviation
        mapped.name shouldBeEqualTo dummy.name
    }

    @Test
    fun `check if the exchange rate mapper works correctly`() {
        // Given
        val dummy = TestDataProvider.exchangeRateDomain

        // When
        val mapped = exchangeRateMapper.map(dummy)

        // Then
        dummy shouldBeInstanceOf ExchangeRate::class
        mapped shouldBeInstanceOf ExchangeRateModel::class
        mapped.baseCurrency shouldBeEqualTo dummy.baseCurrency
        mapped.exchangeCurrency shouldBeEqualTo dummy.exchangeCurrency
        mapped.exchangeRate shouldBeEqualTo dummy.exchangeRate
    }


}