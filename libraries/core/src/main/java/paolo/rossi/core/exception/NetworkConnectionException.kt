package paolo.rossi.core.exception

import java.io.IOException

class NetworkConnectionException(
    message: String? = null,
    cause: Throwable? = null
) : IOException(message ?: cause?.message, cause)
