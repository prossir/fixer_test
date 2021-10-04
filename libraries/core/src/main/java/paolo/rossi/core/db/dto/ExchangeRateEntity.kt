package paolo.rossi.core.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index


@Entity(
    tableName = ExchangeRateEntity.TABLE_NAME,
    indices = [
        Index(value = [ExchangeRateEntity.FIELD_BASE_CURRENCY]),
        Index(value = [ExchangeRateEntity.FIELD_EXCHANGE_CURRENCY]),
    ],
    primaryKeys = [
        ExchangeRateEntity.FIELD_BASE_CURRENCY,
        ExchangeRateEntity.FIELD_EXCHANGE_CURRENCY
    ]
)
data class ExchangeRateEntity(
    @ColumnInfo(name = FIELD_BASE_CURRENCY)
    val baseCurrency: String,
    @ColumnInfo(name = FIELD_EXCHANGE_CURRENCY)
    val exchangeCurrency: String,
    @ColumnInfo(name = FIELD_EXCHANGE_RATE)
    val exchangeRate: Double
) {

    companion object {
        internal const val TABLE_NAME = "exchangeRate"

        internal const val FIELD_BASE_CURRENCY = "baseCurrency"
        internal const val FIELD_EXCHANGE_CURRENCY = "exchangeCurrency"
        internal const val FIELD_EXCHANGE_RATE = "rate"
    }

}