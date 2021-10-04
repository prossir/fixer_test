package paolo.rossi.currency_exchange

import paolo.rossi.currency_exchange.domain.entity.Currency
import paolo.rossi.currency_exchange.domain.entity.ExchangeRate

object TestDataProvider {

    val exchangeRateDomain = ExchangeRate(baseCurrency = "EUR", exchangeCurrency = "SOL", exchangeRate = 5.0)
    val currencyDomain = Currency(abbreviation = "SOL", "SOL")

}