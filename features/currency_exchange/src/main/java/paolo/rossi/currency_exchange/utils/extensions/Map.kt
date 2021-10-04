package paolo.rossi.currency_exchange.utils.extensions

import paolo.rossi.currency_exchange.data.data_source.remote.dto.CurrencyResponse
import paolo.rossi.currency_exchange.data.data_source.remote.dto.ExchangeRateResponse


fun Map<String, String>.toListOfCurrencies() : List<CurrencyResponse> {
    val currencies = arrayListOf<CurrencyResponse>()
    for ((key, value) in this) {
        currencies.add(CurrencyResponse(abbreviation = key, name = value))
    }
    return currencies
}

fun Map<String, Double>.toListOfExchangeRates(baseCurrency: String) : List<ExchangeRateResponse> {
    val exchangeRates = arrayListOf<ExchangeRateResponse>()
    for ((key, value) in this) {
        exchangeRates.add(ExchangeRateResponse(baseCurrency = baseCurrency, exchangeCurrency = key, exchangeRate = value))
    }
    return exchangeRates
}