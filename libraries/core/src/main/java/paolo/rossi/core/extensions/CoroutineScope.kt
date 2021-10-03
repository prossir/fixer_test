package paolo.rossi.core.extensions

import kotlinx.coroutines.*
import timber.log.Timber


fun CoroutineScope.safeLaunch(
    exception: Throwable.() -> Unit = {},
    bock: suspend CoroutineScope.() -> Unit
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d("Coroutine Error -> handleException = $throwable")
        exception(throwable)
    }
    return this.launch(exceptionHandler) { bock(this) }
}

suspend fun <T> CoroutineScope.withDispatcher(
    dispatcher: CoroutineDispatcher,
    block: suspend CoroutineScope.() -> T
) = withContext(dispatcher) { block() }
