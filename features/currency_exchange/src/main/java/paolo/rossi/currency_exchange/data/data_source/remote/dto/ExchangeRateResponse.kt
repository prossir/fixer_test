package paolo.rossi.currency_exchange.data.data_source.remote.dto




data class ExchangeRateResponse(
    val baseCurrency: String,
    val exchangeCurrency: String,
    val exchangeRate: Double
)