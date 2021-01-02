package me.heizi.androidx.content_market.p.teamplates

/**
 * Status
 *
 * @constructor Create empty Status
 */
sealed class  Status {
    data class Success<T> (val result:T) : Status()
    data class Error(val message:String?,val exceptionType:String?=null): Status()

    companion object {
        inline fun <reified T> runCatching(crossinline block: () -> T): Status {
            return try {
                Success(block())
            } catch (e: Exception) {
                Error(e.message, exceptionType = e.javaClass.name)
            }
        }
    }

}