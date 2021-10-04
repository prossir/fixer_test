package paolo.rossi.currency_exchange.data.data_source.remote

import paolo.rossi.core.BuildConfig
import paolo.rossi.core.exception.EmptyDataException
import paolo.rossi.core.exception.FixerException
import paolo.rossi.core.network.utils.safeApiCall
import paolo.rossi.currency_exchange.data.data_source.remote.api.CurrencyExchangeApi
import paolo.rossi.currency_exchange.data.data_source.remote.dto.CurrencyResponse
import paolo.rossi.currency_exchange.data.data_source.remote.dto.ExchangeRateResponse
import paolo.rossi.currency_exchange.utils.extensions.toListOfCurrencies
import paolo.rossi.currency_exchange.utils.extensions.toListOfExchangeRates


class CurrencyRemoteDataSource(private val api: CurrencyExchangeApi) {

    suspend fun getCurrencies() : List<CurrencyResponse> {
        val response = safeApiCall {
            api.getCurrencies(BuildConfig.API_KEY)
        }

        return if (response == null) {
            throw EmptyDataException()
        } else if(!response.state) {
            throw FixerException.fromError(response.error)
        } else {
            response.data.toListOfCurrencies()
        }
    }

    suspend fun getExchangeRates(baseCurrency: String): List<ExchangeRateResponse> {
        val response = safeApiCall {
            api.getExchangeRatesOfCurrency(BuildConfig.API_KEY, baseCurrency)
        }

        return if (response == null) {
            throw EmptyDataException()
        } else if(!response.state) {
            throw FixerException.fromError(response.error)
        } else {
            response.data.toListOfExchangeRates(baseCurrency)
        }
    }

}