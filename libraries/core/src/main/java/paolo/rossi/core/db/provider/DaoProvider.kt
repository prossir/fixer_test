package paolo.rossi.core.db.provider

import paolo.rossi.core.db.dao.CurrencyDao
import paolo.rossi.core.db.dao.ExchangeRateDao

class DaoProvider(private val database: DatabaseProvider) {

    fun getCurrencyDao(): CurrencyDao = database.getInstance().currencyDao()
    fun getExchangeRateDao(): ExchangeRateDao = database.getInstance().exchangeRateDao()
}