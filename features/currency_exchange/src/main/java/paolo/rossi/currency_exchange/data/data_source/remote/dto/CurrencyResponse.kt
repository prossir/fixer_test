package paolo.rossi.currency_exchange.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrencyResponse(
    @SerialName(FIELD_ID)
    @SerializedName(FIELD_ID)
    val abbreviation: String,
    @SerialName(FIELD_NAME)
    @SerializedName(FIELD_NAME)
    val name: String
) {

    companion object {
        private const val FIELD_ID = "id"
        private const val FIELD_NAME = "name"
    }

}