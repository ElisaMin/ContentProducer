package com.example.providers.p.teamplates

import android.content.ContentValues
import android.net.Uri
import androidx.core.net.toUri

/**
 * Insert template
 *
 * @property resultUri (schema/path.../result)/success or (schema/path.../result)/failed?msg=bababa&
 * @constructor Create empty Insert template
 */
class InsertTemplate constructor(
    val resultUri:String
) {

    inline fun <reified T> runCatching(crossinline block:()->T):Uri {
        val result = Status.runCatching(block)
        return when(result) {
            is Status.Success<*> -> "$resultUri/success"
            is Status.Error -> "$resultUri/failed?msg=${result.message}&type=${result.exceptionType}"
        }.toUri()
    }

}
