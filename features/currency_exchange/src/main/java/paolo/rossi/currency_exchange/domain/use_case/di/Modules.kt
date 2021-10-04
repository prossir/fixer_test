package paolo.rossi.currency_exchange.domain.use_case.di

import org.koin.dsl.module
import paolo.rossi.currency_exchange.domain.use_case.GetCurrenciesUseCase
import paolo.rossi.currency_exchange.domain.use_case.GetExchangeRatesOfCurrencyUseCase


internal val currencyExchangeUseCasesModule = module {
    factory { GetCurrenciesUseCase(get()) }
    factory { GetExchangeRatesOfCurrencyUseCase(get()) }
}