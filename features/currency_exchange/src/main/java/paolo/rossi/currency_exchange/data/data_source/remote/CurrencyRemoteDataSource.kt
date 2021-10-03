package paolo.rossi.currency_exchange.data.data_source.remote

import paolo.rossi.core.exception.EmptyDataException
import paolo.rossi.core.network.utils.safeApiCall
import paolo.rossi.currency_exchange.data.data_source.remote.api.CurrencyExchangeApi
import paolo.rossi.currency_exchange.data.data_source.remote.dto.CurrencyResponse
import paolo.rossi.currency_exchange.utils.extensions.toListOfCurrencies


class CurrencyRemoteDataSource(private val api: CurrencyExchangeApi) {

    suspend fun getCurrencies() : List<CurrencyResponse> {

        val response = safeApiCall {
            api.getCurrencies()
        }

        return if (response?.data == null) {
            throw EmptyDataException()
        } else {
            response.data.toListOfCurrencies()
        }
    }

}