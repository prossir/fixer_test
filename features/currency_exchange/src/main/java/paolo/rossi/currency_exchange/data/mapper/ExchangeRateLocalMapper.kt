package paolo.rossi.currency_exchange.data.mapper

import paolo.rossi.core.db.dto.ExchangeRateEntity
import paolo.rossi.core.mapper.SingleMapper
import paolo.rossi.currency_exchange.domain.entity.ExchangeRate

class ExchangeRateLocalMapper : SingleMapper<ExchangeRateEntity, ExchangeRate>() {

    override fun map(value: ExchangeRateEntity) = ExchangeRate(
        baseCurrency = value.baseCurrency,
        exchangeCurrency = value.exchangeCurrency,
        exchangeRate = value.exchangeRate
    )

}