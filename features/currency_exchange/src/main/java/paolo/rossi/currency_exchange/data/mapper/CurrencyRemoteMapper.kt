package paolo.rossi.currency_exchange.data.mapper

import paolo.rossi.core.db.model.CurrencyEntity
import paolo.rossi.core.mapper.SingleMapper
import paolo.rossi.currency_exchange.data.data_source.remote.dto.CurrencyResponse


class CurrencyRemoteMapper: SingleMapper<CurrencyResponse, CurrencyEntity>() {

    override fun map(value: CurrencyResponse) = CurrencyEntity(
        abbreviation = value.abbreviation,
        name = value.name
    )

}