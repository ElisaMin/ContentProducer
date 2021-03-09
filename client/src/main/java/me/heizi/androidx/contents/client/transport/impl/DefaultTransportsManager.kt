
package me.heizi.androidx.contents.client.transport.impl

import android.content.ContentResolver
import me.heizi.androidx.contents.client.annotations.GET
import me.heizi.androidx.contents.client.transport.Transporter
import me.heizi.androidx.contents.client.transport.TransportsManager
import me.heizi.androidx.contents.client.transport.Truck
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy
import kotlin.reflect.KClass


/**
 * Default transports manager
 *
 * transport manager 实现
 * @property contentResolver
 * @constructor Create empty Default transports manager
 */
private class DefaultTransportsManager constructor (
        contentResolver: ContentResolver,
        baseUri: String
) : TransportsManager {

    override val truck: Truck = DefaultTruck(contentResolver,baseUri)

    val transporters = HashSet<KClass<*>>()


    override fun <T : Transporter> resignTransport(klass: KClass<T>) {
        transporters.add(klass)
    }

    val invocationHandler = InvocationHandler handler@{ self, method,args ->
        if (args.size != 1 && args[0] !is Map<*,*>) throw KotlinReflectionNotSupportedError("现在暂时无法达到,请保证只有一个参数并且参数类型为Map<String,String?>")
        method.getAnnotation(GET::class.java)?.let {
            truck.get(it.path,method.returnType,args[0] as Map<String, String?>)
        }
    }

    override fun <T : Transporter> get(klass: KClass<T>): T =
        Proxy.newProxyInstance(klass.java.classLoader,arrayOf(klass.java), invocationHandler) as T

}
