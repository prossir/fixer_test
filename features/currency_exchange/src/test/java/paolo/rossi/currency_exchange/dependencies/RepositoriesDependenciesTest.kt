package paolo.rossi.currency_exchange.dependencies

import org.amshove.kluent.shouldNotBeNull
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import paolo.rossi.currency_exchange.dependencies.di.injectTestModules
import paolo.rossi.currency_exchange.domain.repository.CurrencyRepository


class RepositoriesDependenciesTest: AutoCloseKoinTest() {

    @Before
    fun setup() {
        injectTestModules()
    }

    @Test
    fun `solving dependencies for CurrencyRepository`() {
        get<CurrencyRepository>().shouldNotBeNull()
    }

}