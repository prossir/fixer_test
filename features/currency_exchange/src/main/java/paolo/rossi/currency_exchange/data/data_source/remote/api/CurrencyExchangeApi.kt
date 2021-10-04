package paolo.rossi.currency_exchange.data.data_source.remote.api

import paolo.rossi.currency_exchange.data.data_source.remote.dto.CurrencyResponseHolder
import paolo.rossi.core.network.dto.errors.FixerError
import paolo.rossi.currency_exchange.data.data_source.remote.dto.ExchangeRateResponseHolder
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyExchangeApi {

    @GET("symbols")
    suspend fun getCurrencies(@Query("access_key") apiKey: String):
            Response<CurrencyResponseHolder<Map<String, String>, FixerError>>

    @GET("latest")
    suspend fun getExchangeRatesOfCurrency(@Query("access_key") apiKey: String,
                                           @Query("base") baseCurrency: String):
            Response<ExchangeRateResponseHolder<Map<String, Double>, FixerError>>

}