package paolo.rossi.currency_exchange.domain.di

import org.koin.core.module.Module
import paolo.rossi.core.extensions.listByElementsOf
import paolo.rossi.currency_exchange.domain.use_case.di.currencyExchangeUseCasesModule


internal val domainModules by lazy {
    listByElementsOf<Module>(
        useCasesModules
    )
}

private val useCasesModules by lazy {
    listOf(
        currencyExchangeUseCasesModule
    )
}
