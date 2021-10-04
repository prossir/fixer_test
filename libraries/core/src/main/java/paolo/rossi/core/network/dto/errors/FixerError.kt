package paolo.rossi.core.network.dto.errors


import kotlinx.serialization.SerialName


data class FixerError(
    @SerialName(FIELD_CODE)
    val code: Int,
    @SerialName(FIELD_TYPE)
    val type: String?,
    @SerialName(FIELD_INFO)
    val info: String?
) {

    companion object {
        const val FIELD_CODE = "code"
        const val FIELD_TYPE = "type"
        const val FIELD_INFO = "info"
    }

}