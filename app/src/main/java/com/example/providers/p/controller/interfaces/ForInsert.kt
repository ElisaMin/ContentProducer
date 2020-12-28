package com.example.providers.p.controller.interfaces

import android.content.ContentValues
import android.net.Uri
import com.example.providers.p.utils.Path.Companion.paths


/**
 * For insert IOC
 *
 * @constructor Create empty For insert
 */
open class ForInsert(baseUri:String):PathController<Uri>(baseUri) {

    fun insert(uri: Uri, contentValues: ContentValues):Uri? {
        val paths = uri.paths
        return if (paths.asList().first() == rootPath) map[paths.asList().last()] ?.call(this,contentValues)
        else null
    }
    fun insert(uri: Uri) = null

}

//fun main(args: Array<String>) {
//    fun dropOrSelf(it: String):String = if (it.startsWith("/")) it.drop(1) else it
//    println("/path".let(::dropOrSelf))
//}
