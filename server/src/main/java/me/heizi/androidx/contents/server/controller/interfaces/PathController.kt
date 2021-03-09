package me.heizi.androidx.contents.server.controller.interfaces

import me.heizi.androidx.contents.server.annotations.Path
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

/**
 * Path controller
 * url不能为/结尾
 * @param T
 * @property baseUri
 * @constructor Create empty Path controller
 */
abstract class PathController <T> constructor(
    var baseUri:String
) {

    private val _map = HashMap<String, KFunction<T>>()
    val map:Map<String,KFunction<T>> get() = _map

    val rootPath = (this.javaClass.getAnnotation(Path::class.java)?.path ?: throw NullPointerException("on ${this.javaClass.name}") )
        .let(::dropOrSelf)
    private fun dropOrSelf(it: String):String = if (it.startsWith("/")) it.drop(1) else it

    init {
        //base uri and throw
        if (baseUri.endsWith("/")) throw IllegalAccessException("不能以/结尾")
        //循环查找所有的function
        for (m in this::class.members) {
            //存储在map
            m.findAnnotation<Path>()?.let {
                kotlin.runCatching {
                    m as KFunction<T>
                }.getOrNull()?.let { m->
                    _map[it.path.let(::dropOrSelf)] = m
                }
            }
        }
        //添加所有的Path
        resignPath()?.let(_map::putAll)
    }

    fun resignPath():Map<String, KFunction<T>>?=null
}