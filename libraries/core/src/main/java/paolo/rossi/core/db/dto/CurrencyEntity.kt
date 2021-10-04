package paolo.rossi.core.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = CurrencyEntity.TABLE_NAME,
    indices = [
        Index(value = [CurrencyEntity.FIELD_ID])
    ]
)
data class CurrencyEntity(
    @ColumnInfo(name = FIELD_ID)
    @PrimaryKey
    var abbreviation: String,
    @ColumnInfo(name = FIELD_NAME)
    var name: String,
) {
    companion object {
        internal const val TABLE_NAME = "currency"

        internal const val FIELD_ID = "abbreviation"
        internal const val FIELD_NAME = "name"
    }
}