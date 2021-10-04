package paolo.rossi.currency_exchange.dependencies.di

import android.content.Context
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import paolo.rossi.core.di.coreModules
import paolo.rossi.core.extensions.listByElementsOf


internal fun injectTestModules() {
    startKoin {
        printLogger()
        modules(basesTestModules)
    }
}

private val basesTestModules by lazy {
    listByElementsOf<Module>(
        coreModules,
        currencyExchangeModules,
        mocksModule
    )
}

private val mocksModule = module {
    factory { mockk<Context>() } // overriding context
}
