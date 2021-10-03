package paolo.rossi.core.db.provider

import android.content.Context
import androidx.room.Room
import paolo.rossi.core.db.AppDatabase


class DatabaseProvider(
    private val context: Context,
) {

    private var database: AppDatabase? = null

    fun getInstance(): AppDatabase =
        database ?: synchronized(this) {
            database ?: buildDatabase().also { database = it }
        }

    private fun buildDatabase(): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
            .build()

}