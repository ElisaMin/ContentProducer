package com.example.providers.p.controller.interfaces

import android.content.ContentValues
import android.net.Uri
import com.example.providers.annotations.Path
import com.example.providers.p.utils.Path.Companion.paths
import kotlin.reflect.KCallable
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

/**
 * Path controller
 * url不能为/结尾
 * @param T
 * @property baseUri
 * @constructor Create empty Path controller
 */
open class PathController <T>{
//    constructor() {
////        if(baseUri ==null) baseUri = this::class.findAnnotation<BaseUri>()?.uri ?: throw NullPointerException("on ${this.javaClass.name}")
//    }
//    constructor(uri: String) {
//        this.baseUri= uri
//    }
//    annotation class BaseUri(val uri:String)
    lateinit var baseUri:String

    private val _map = HashMap<String, KFunction<T>>()
    val map:Map<String,KFunction<T>> get() = _map

    val rootPath = (this.javaClass.getAnnotation(Path::class.java)?.path ?: throw NullPointerException("on ${this.javaClass.name}") )
        .let(::dropOrSelf)
    private fun dropOrSelf(it: String):String = if (it.startsWith("/")) it.drop(1) else it

    init {
        if (baseUri.endsWith("/")) throw IllegalAccessException("不能以/结尾")
        for (m in this::class.members) {
            m.findAnnotation<Path>()?.let {
                kotlin.runCatching {
                    m as KFunction<T>
                }.getOrNull()?.let { m->
                    _map[it.path.let(::dropOrSelf)] = m
                }
            }
        }
        resignPath()?.let(_map::putAll)
    }

    fun resignPath():Map<String, KFunction<T>>?=null
}