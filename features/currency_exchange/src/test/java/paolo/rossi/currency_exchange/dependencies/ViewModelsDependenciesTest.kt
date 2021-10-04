package paolo.rossi.currency_exchange.dependencies

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldNotBeNull
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import paolo.rossi.currency_exchange.dependencies.di.injectTestModules
import paolo.rossi.currency_exchange.features.currency_exchange.presentation.CurrencyExchangeViewModelTest


class ViewModelsDependenciesTest: AutoCloseKoinTest() {

    @Before
    fun setup() {
        injectTestModules()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `solving dependencies for CurrencyExchangeViewModel`() {
        get<CurrencyExchangeViewModelTest>().shouldNotBeNull()
    }

}