package paolo.rossi.core.network.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName


data class BaseResponse<T>(
    @SerialName(FIELD_STATE)
    @SerializedName(FIELD_STATE)
    val state: Boolean,
    @SerialName(FIELD_MESSAGE)
    @SerializedName(FIELD_MESSAGE)
    val message: String,
    @SerialName(FIELD_DATA)
    @SerializedName(FIELD_DATA)
    val data: T
) {
    companion object {
        private const val FIELD_STATE = "status"
        private const val FIELD_MESSAGE = "message"
        private const val FIELD_DATA = "body"
    }
}
