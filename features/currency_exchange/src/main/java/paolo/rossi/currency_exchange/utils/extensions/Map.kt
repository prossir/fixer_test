package paolo.rossi.currency_exchange.utils.extensions

import paolo.rossi.currency_exchange.data.data_source.remote.dto.CurrencyResponse


fun Map<String, String>.toListOfCurrencies() : List<CurrencyResponse> {
    val listOfCurrencies = arrayListOf<CurrencyResponse>()
    for ((key, value) in this) {
        listOfCurrencies.add(CurrencyResponse(abbreviation = key, name = value))
    }
    return listOfCurrencies
}