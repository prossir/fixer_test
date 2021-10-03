package paolo.rossi.core.network.handlers

import paolo.rossi.core.exception.NetworkConnectionException
import paolo.rossi.core.exception.ServerErrorException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


internal class FailureHandle {

    fun build(e: Throwable): Throwable {
        return when (e) {
            is HttpException -> buildExceptionByType(e)
            is SocketTimeoutException -> e
            is IOException -> NetworkConnectionException(cause = e)
            else -> ServerErrorException(cause = e)
        }
    }

    private fun buildExceptionByType(e: HttpException): Exception {
        return when (e.code()) {
            //UNAUTHORIZED_CODE, FORBIDDEN_CODE -> UnauthorizedException(cause = e)
            else -> ServerErrorException(cause = e)
        }
    }

    companion object {
        const val UNAUTHORIZED_CODE = 401
    }

}
