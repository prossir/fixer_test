package paolo.rossi.core.exception

class ServerErrorException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message ?: cause?.message, cause)
