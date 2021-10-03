package paolo.rossi.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import paolo.rossi.core.db.dao.CurrencyDao
import paolo.rossi.core.db.model.CurrencyEntity


@Database(
    entities = [
        CurrencyEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        const val VERSION = 1
        const val NAME = "fixerTestDb"
    }

}