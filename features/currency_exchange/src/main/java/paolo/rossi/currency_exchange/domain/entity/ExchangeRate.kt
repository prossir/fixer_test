package paolo.rossi.currency_exchange.domain.entity


data class ExchangeRate(
    val baseCurrency: String,
    val exchangeCurrency: String,
    val exchangeRate: Double
)