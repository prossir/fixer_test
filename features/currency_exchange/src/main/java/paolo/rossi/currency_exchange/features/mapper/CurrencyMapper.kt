package paolo.rossi.currency_exchange.features.mapper

import paolo.rossi.core.mapper.SingleMapper
import paolo.rossi.currency_exchange.domain.entity.Currency
import paolo.rossi.currency_exchange.features.dto.CurrencyModel


class CurrencyMapper: SingleMapper<Currency, CurrencyModel>() {

    override fun map(value: Currency) = CurrencyModel(
        abbreviation = value.abbreviation,
        name = value.name
    )

}