package me.heizi.androidx.contents.server.utils

import android.net.Uri

/**
 * Path 封装了个List
 * 用/分割字符串
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
    /**
     * As list 获取列表
     */
    fun asList()  = list

    /**
     * Get 判断是否存在该 path
     * @param string
     * @return
     */
    operator fun get(string: String):Boolean
        = list.contains(string)
}