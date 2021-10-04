package paolo.rossi.currency_exchange.data.mapper

import paolo.rossi.core.db.dto.ExchangeRateEntity
import paolo.rossi.core.mapper.SingleMapper
import paolo.rossi.currency_exchange.data.data_source.remote.dto.ExchangeRateResponse


class ExchangeRateRemoteMapper: SingleMapper<ExchangeRateResponse, ExchangeRateEntity>() {

    override fun map(value: ExchangeRateResponse) = ExchangeRateEntity(
        baseCurrency = value.baseCurrency,
        exchangeCurrency = value.exchangeCurrency,
        exchangeRate = value.exchangeRate
    )

}