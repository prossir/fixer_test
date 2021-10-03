package paolo.rossi.core.db.di

import org.koin.dsl.module
import paolo.rossi.core.db.provider.DaoProvider
import paolo.rossi.core.db.provider.DatabaseProvider


internal val databaseModule = module {
    single { DatabaseProvider(get()) }
    single { DaoProvider(get()) }
}
