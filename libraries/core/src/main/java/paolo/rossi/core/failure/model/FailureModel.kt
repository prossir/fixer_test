package paolo.rossi.core.failure.model

import androidx.annotation.StringRes


data class FailureModel(
    @StringRes val title: Int = NONE,
    @StringRes val message: Int = NONE,
    val code: Int = NONE,
    val localizedMessage: String? = EMPTY_STRING,
    val origin : Throwable
) {

    companion object {
        const val NONE = -1
        const val EMPTY_STRING = ""
    }

}
