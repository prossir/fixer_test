package paolo.rossi.currency_exchange.domain.use_case.di

import org.koin.dsl.module
import paolo.rossi.currency_exchange.domain.use_case.GetCurrenciesUseCase


internal val currencyExchangeUseCasesModule = module {
    factory { GetCurrenciesUseCase(get()) }
}