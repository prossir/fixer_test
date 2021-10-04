package paolo.rossi.core.failure.mapper

import paolo.rossi.core.R
import paolo.rossi.core.exception.FixerException
import paolo.rossi.core.exception.NetworkConnectionException
import paolo.rossi.core.exception.ServerErrorException
import paolo.rossi.core.failure.model.FailureModel
import paolo.rossi.core.mapper.SingleMapper


abstract class FailureMapper: SingleMapper<Throwable, FailureModel>() {
    override fun map(value: Throwable) = when (value) {
        is NetworkConnectionException -> FailureModel(
            title = R.string.error_no_internet_connection_title,
            message = R.string.error_no_internet_connection_description,
            origin = value
        )
        is ServerErrorException -> FailureModel(
            title = R.string.error_ups,
            message = R.string.error_server_request_error_description,
            localizedMessage = value.localizedMessage,
            origin = value
        )
        is FixerException -> FailureModel(
            title = R.string.error_ups,
            message = R.string.error_server_request_error_description,
            localizedMessage = value.message,
            origin = value
        )
        else -> FailureModel(
            title = R.string.error_ups,
            message = R.string.error_an_error_occurred,
            origin = value
        )
    }
}
