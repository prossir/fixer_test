package paolo.rossi.currency_exchange.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName


data class CurrencyResponseHolder<T, Z>(
    @SerializedName(FIELD_STATE)
    @SerialName(FIELD_STATE)
    val state: Boolean,
    @SerializedName(FIELD_FIXER_ERROR)
    @SerialName(FIELD_FIXER_ERROR)
    val error: Z?,
    @SerializedName(FIELD_DATA)
    @SerialName(FIELD_DATA)
    val data: T
) {
    companion object {
        private const val FIELD_STATE = "success"
        private const val FIELD_FIXER_ERROR = "error"
        private const val FIELD_DATA = "symbols"
    }
}
