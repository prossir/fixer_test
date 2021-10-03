package paolo.rossi.core.exception


class ServerInfoException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message ?: cause?.message, cause)
