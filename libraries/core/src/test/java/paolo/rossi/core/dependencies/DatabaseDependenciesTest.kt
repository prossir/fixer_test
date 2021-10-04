package paolo.rossi.core.dependencies

import org.amshove.kluent.shouldNotBeNull
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import paolo.rossi.core.db.provider.DaoProvider
import paolo.rossi.core.db.provider.DatabaseProvider
import paolo.rossi.core.dependencies.di.injectTestModules

class DatabaseDependenciesTest : AutoCloseKoinTest() {

    @Before
    fun setup() {
        injectTestModules()
    }

    @Test
    fun `solving dependencies for DatabaseProvider`() {
        get<DatabaseProvider>().shouldNotBeNull()
    }

    @Test
    fun `solving dependencies for DaoProvider`() {
        get<DaoProvider>().shouldNotBeNull()
    }

}