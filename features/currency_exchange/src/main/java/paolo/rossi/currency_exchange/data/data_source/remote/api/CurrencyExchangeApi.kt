package paolo.rossi.currency_exchange.data.data_source.remote.api

import paolo.rossi.core.network.dto.BaseResponse
import retrofit2.Response
import retrofit2.http.GET


interface CurrencyExchangeApi {

    @GET("symbols")
    suspend fun getCurrencies() :
            Response<BaseResponse<Map<String, String>>>

}