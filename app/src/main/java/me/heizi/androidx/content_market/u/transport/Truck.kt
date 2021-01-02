package me.heizi.androidx.content_market.u.transport

import android.content.ContentResolver

interface Truck {
    /**
     * Base uri
     * "content://authorities"
     */
    val baseUri:String
    /**
     * 索要封装对象
     */
    val contentResolver:ContentResolver

    /**
     * Get
     *
     * 封装[ContentResolver.query]
     */
    fun <T:Any> get(path:String,target: Class<T>, args:Map<String, String?>):List<T>?
}