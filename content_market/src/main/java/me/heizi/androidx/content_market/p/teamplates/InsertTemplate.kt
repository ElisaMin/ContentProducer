package me.heizi.androidx.content_market.p.teamplates

import android.net.Uri
import androidx.core.net.toUri
import me.heizi.androidx.content_market.annotation.Path
import me.heizi.androidx.content_market.p.teamplates.interfaces.Template
import kotlin.reflect.full.findAnnotation

/**
 * Insert template
 *
 * @property resultUri (schema/path.../result)/success or (schema/path.../result)/failed?msg=bababa&
 * @constructor Create empty Insert template
 */

class InsertTemplate constructor(
    baseUri:String
): Template(baseUri) {

    val resultUri = this::class.findAnnotation<Path>()?.path.takeIf { !(it.isNullOrEmpty() and it!!.endsWith("/")) }?.let { realPath ->
        "$baseUri/$realPath/result"
    } ?: "$baseUri/result"

    inline fun <reified T> runCatching(crossinline block:()->T):Uri {
        val result = Status.runCatching(block)
        return when(result) {
            is Status.Success<*> -> "$resultUri/success"
            is Status.Error -> "$resultUri/failed?msg=${result.message}&type=${result.exceptionType}"
        }.toUri()
    }
}
