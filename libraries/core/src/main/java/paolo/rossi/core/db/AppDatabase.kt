package paolo.rossi.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import paolo.rossi.core.db.dao.CurrencyDao
import paolo.rossi.core.db.dao.ExchangeRateDao
import paolo.rossi.core.db.dto.CurrencyEntity
import paolo.rossi.core.db.dto.ExchangeRateEntity


@Database(
    entities = [
        CurrencyEntity::class,
        ExchangeRateEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
    abstract fun exchangeRateDao(): ExchangeRateDao

    companion object {
        const val VERSION = 1
        const val NAME = "fixerTestDb"
    }

}