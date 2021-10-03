package paolo.rossi.core.network.utils

import retrofit2.HttpException
import retrofit2.Response


suspend fun <T : Any> safeApiCall(bock: suspend () -> Response<T>): T? {
    try {
        val response = bock.invoke()
        if (response.isSuccessful) {
            return response.buildSuccess()
        } else {
            throw HttpException(response)
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        throw e.buildFailure()
    }
}