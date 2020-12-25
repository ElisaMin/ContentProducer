package com.example.providers.p.utils

import android.net.Uri

/**
 * Path
 *
 * @constructor 最终：List
 * @constructor 达成：String
 */
class Path  (
    private val list:List<String>
) {
    constructor(path: String):this(ArrayList<String>().also {
        it.addAll(
            path.split(
                "/",
                ignoreCase = true
            )
        )
    })

    companion object {
        val Uri.paths get() = Path(path?:"/")
    }
//    val last get() = list.last()
    fun asList()  = list

    operator fun get(string: String):Boolean
        = list.contains(string)
}