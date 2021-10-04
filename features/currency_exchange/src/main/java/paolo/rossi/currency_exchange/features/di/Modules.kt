package paolo.rossi.currency_exchange.features.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import paolo.rossi.core.extensions.listByElementsOf
import paolo.rossi.currency_exchange.features.mapper.CurrencyExchangeFailureMapper
import paolo.rossi.currency_exchange.features.mapper.CurrencyMapper
import paolo.rossi.currency_exchange.features.mapper.ExchangeRateMapper
import paolo.rossi.currency_exchange.features.views.CurrencyExchangeViewModel


val featuresModules by lazy {
    listByElementsOf<Module>(
        currencyExchangeFeatureModule
    )
}

internal val currencyExchangeFeatureModule = module {

    factory { CurrencyExchangeFailureMapper() }
    factory { CurrencyMapper() }
    factory { ExchangeRateMapper() }

    viewModel { CurrencyExchangeViewModel(get(), get(), get(), get(), get()) }

}
