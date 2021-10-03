package paolo.rossi.currency_exchange.data.di

import org.koin.core.module.Module
import org.koin.dsl.module
import paolo.rossi.core.extensions.listByElementsOf
import paolo.rossi.currency_exchange.data.data_source.local.CurrencyLocalDataSource
import paolo.rossi.currency_exchange.data.data_source.remote.CurrencyRemoteDataSource
import paolo.rossi.currency_exchange.data.data_source.remote.api.di.networkModule
import paolo.rossi.currency_exchange.data.mapper.CurrencyLocalMapper
import paolo.rossi.currency_exchange.data.mapper.CurrencyRemoteMapper
import paolo.rossi.currency_exchange.data.repository.CurrencyDataRepository
import paolo.rossi.currency_exchange.domain.repository.CurrencyRepository


internal val dataModules by lazy {
    listByElementsOf<Module>(
        networkModule,
        repositoriesModules
    )
}

internal val repositoriesModules by lazy {
    listByElementsOf<Module>(
        currencyExchangeDataModule
    )
}

internal val currencyExchangeDataModule = module {
    factory { CurrencyLocalMapper() }
    factory { CurrencyRemoteMapper() }

    single { CurrencyLocalDataSource(get()) }
    single { CurrencyRemoteDataSource(get()) }

    single<CurrencyRepository> { CurrencyDataRepository(get(), get(), get(), get()) }
}