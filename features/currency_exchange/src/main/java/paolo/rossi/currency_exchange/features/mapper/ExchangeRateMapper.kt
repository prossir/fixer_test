package paolo.rossi.currency_exchange.features.mapper

import paolo.rossi.core.mapper.SingleMapper
import paolo.rossi.currency_exchange.domain.entity.ExchangeRate
import paolo.rossi.currency_exchange.features.dto.ExchangeRateModel


class ExchangeRateMapper: SingleMapper<ExchangeRate, ExchangeRateModel>() {

    override fun map(value: ExchangeRate) = ExchangeRateModel(
        baseCurrency = value.baseCurrency,
        exchangeCurrency = value.exchangeCurrency,
        exchangeRate = value.exchangeRate
    )

}