package paolo.rossi.currency_exchange.data.mapper

import paolo.rossi.core.db.model.CurrencyEntity
import paolo.rossi.core.mapper.SingleMapper
import paolo.rossi.currency_exchange.domain.entity.Currency


class CurrencyLocalMapper: SingleMapper<CurrencyEntity, Currency>() {

    override fun map(value: CurrencyEntity) = Currency(
        abbreviation = value.abbreviation,
        name = value.name
    )

}