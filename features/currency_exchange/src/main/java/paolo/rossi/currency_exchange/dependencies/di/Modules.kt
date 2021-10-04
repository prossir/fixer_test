package paolo.rossi.currency_exchange.dependencies.di

import org.koin.core.module.Module
import paolo.rossi.core.extensions.listByElementsOf
import paolo.rossi.currency_exchange.data.di.dataModules
import paolo.rossi.currency_exchange.domain.di.domainModules
import paolo.rossi.currency_exchange.features.di.featuresModules


val currencyExchangeModules by lazy {
    listByElementsOf<Module>(
        dataModules,
        domainModules,
        featuresModules
    )
}
