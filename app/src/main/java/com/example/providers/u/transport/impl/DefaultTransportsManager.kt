package com.example.providers.u.transport.impl

import android.content.ContentResolver
import com.example.providers.u.transport.Transporter
import com.example.providers.u.transport.TransportsManager
import kotlin.reflect.KClass


/**
 * Default transports manager
 *
 * @property contentResolver
 * @constructor Create empty Default transports manager
 */
private class DefaultTransportsManager constructor(
    override val contentResolver: ContentResolver
) : TransportsManager {

    interface Truck {
        fun <T:Any> get(target:KClass<T>,args:Map<String,Any?>):List<T>
    }

    val transporters = HashSet<KClass<*>>()

    override fun <T : Transporter> resignTransport(klass: KClass<T>) {
        //TODO 使用HashMap 存储送来的玩意
    }

    override fun <T : Transporter> get(klass: KClass<T>): T {
        TODO("还原Poxed的Transporter")
    }
}