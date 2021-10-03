package paolo.rossi.core.network.handlers



internal class SuccessHandle<T> {

    fun build(value: T?): T? {
        //val body = value as? BaseResponse<*>
        /*ServerError.valueOf(body!!.status)?.let {
            throw ServerErrorException(message = body.message)
        }

        ServerInfo.valueOf(body.status)?.let {
            throw ServerInfoException(message = body.message)
        }

        SessionError.valueOf(body.status)?.let {
            throw SessionErrorException(message = body.message)
        } */

        return value
    }

}
