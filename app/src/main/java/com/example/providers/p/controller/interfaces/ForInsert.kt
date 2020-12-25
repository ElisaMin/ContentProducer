package com.example.providers.p.controller.interfaces

import android.content.ContentValues
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.providers.annotations.Path
import com.example.providers.p.utils.Path.Companion.paths
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation


open class ForInsert {

    private val map = HashMap<String, KCallable<Uri>>()
    private val rootPath = (this.javaClass.getAnnotation(Path::class.java)?.path ?: throw NullPointerException("on ${this.javaClass.name}") )
        .let(::dropOrSelf)
    private fun dropOrSelf(it: String):String = if (it.startsWith("/")) it.drop(1) else it
    init {
        for (m in this::class.members) {
            m.findAnnotation<Path>()?.let {
                kotlin.runCatching {
                    m as KCallable<Uri>
                }.getOrNull()?.let { m->
                    map[it.path.let(::dropOrSelf)] = m
                }
            }
        }
        resignPath()?.let(map::putAll)

    }

    fun resignPath():Map<String,KCallable<Uri>>?=null



    fun insert(uri: Uri, contentValues: ContentValues):Uri? {
        val paths = uri.paths
        return if (paths.asList().first() == rootPath) map[paths.asList().last()] ?.call(contentValues)
        else null
    }
    fun insert(uri: Uri) = null

}

//fun main(args: Array<String>) {
//    fun dropOrSelf(it: String):String = if (it.startsWith("/")) it.drop(1) else it
//    println("/path".let(::dropOrSelf))
//}
