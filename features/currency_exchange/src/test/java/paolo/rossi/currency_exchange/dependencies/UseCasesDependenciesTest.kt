package paolo.rossi.currency_exchange.dependencies

import org.amshove.kluent.shouldNotBeNull
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import paolo.rossi.currency_exchange.dependencies.di.injectTestModules
import paolo.rossi.currency_exchange.domain.use_case.GetCurrenciesUseCase
import paolo.rossi.currency_exchange.domain.use_case.GetExchangeRatesOfCurrencyUseCase


class UseCasesDependenciesTest: AutoCloseKoinTest() {

    @Before
    fun setup() {
        injectTestModules()
    }

    @Test
    fun `solving dependencies for GetCurrenciesUseCase`() {
        get<GetCurrenciesUseCase>().shouldNotBeNull()
    }

    @Test
    fun `solving dependencies for GetExchangeRatesOfCurrencyUseCase`() {
        get<GetExchangeRatesOfCurrencyUseCase>().shouldNotBeNull()
    }

}