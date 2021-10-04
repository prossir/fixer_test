package paolo.rossi.core.exception

import paolo.rossi.core.network.dto.errors.FixerError


class FixerException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message ?: cause?.message, cause) {

    companion object {
        fun fromError(fixerError: FixerError?) : FixerException {
            val message = when(fixerError?.code) {
                105 -> { "You can only use EUR in this demo app. Please select that specific currency to test." }
                else -> { fixerError?.info ?: "Unknown" }
            }
            return FixerException(message = message, cause = Throwable("${fixerError?.code} - ${fixerError?.type} - ${fixerError?.info}"))
        }


    }

}
