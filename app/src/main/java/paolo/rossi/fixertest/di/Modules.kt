package paolo.rossi.fixertest.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import paolo.rossi.core.di.coreModules
import paolo.rossi.core.extensions.listByElementsOf
import paolo.rossi.currency_exchange.dependencies.di.currencyExchangeModules


internal fun injectAppModules(app: Application) {
    startKoin {
        androidLogger()
        androidContext(app)
        modules(baseModules)
    }
}

internal val baseModules by lazy {
    listByElementsOf<Module>(
        coreModules,
        featuresModules
    )
}

internal val featuresModules by lazy {
    listByElementsOf<Module>(
        currencyExchangeModules
    )
}