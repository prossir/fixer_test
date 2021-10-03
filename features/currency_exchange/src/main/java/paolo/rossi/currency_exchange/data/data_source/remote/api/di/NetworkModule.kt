package paolo.rossi.currency_exchange.data.data_source.remote.api.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import paolo.rossi.core.network.di.NAMED_API_BASE
import paolo.rossi.currency_exchange.data.data_source.remote.api.CurrencyExchangeApi
import retrofit2.Retrofit


val networkModule = module {
    single { provideApi(get(named(NAMED_API_BASE))) }
}

private fun provideApi(retrofit: Retrofit): CurrencyExchangeApi {
    return retrofit.create(CurrencyExchangeApi::class.java)
}
