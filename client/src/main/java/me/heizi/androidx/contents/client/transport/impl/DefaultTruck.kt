package me.heizi.androidx.contents.client.transport.impl

import android.content.ContentResolver
import android.net.Uri
import me.heizi.androidx.contents.client.utils.convert
import me.heizi.androidx.contents.client.transport.Truck

/**
 * Default truck
 *
 * Truck实现
 * @property contentResolver
 * @property baseUri
 * @constructor Create empty Default truck
 */
class DefaultTruck constructor(
        override val contentResolver: ContentResolver,
        override val baseUri: String
) : Truck {
    override fun <T : Any> get(path:String,target: Class<T>, args: Map<String,String?>): List<T>? {
        return contentResolver.query(
                Uri.parse("$baseUri/get/$path"),
                args.keys.toTypedArray(),
                null,
                args.values.toTypedArray()
                ,null
        )?.use close@ {
            if (it.position==-2) throw NullPointerException("无结果")
            return@close it.convert(target)
        }
    }
}