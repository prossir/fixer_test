package paolo.rossi.core.db.provider

import paolo.rossi.core.db.dao.CurrencyDao

class DaoProvider(private val database: DatabaseProvider) {

    fun getCurrencyDao(): CurrencyDao = database.getInstance().currencyDao()

}