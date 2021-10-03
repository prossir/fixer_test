package paolo.rossi.core.network.utils

import paolo.rossi.core.network.handlers.FailureHandle
import paolo.rossi.core.network.handlers.SuccessHandle
import retrofit2.Response


internal fun <T : Any> Response<T>.buildSuccess() = SuccessHandle<T>().build(this.body())

internal fun Throwable.buildFailure() = FailureHandle().build(this)
