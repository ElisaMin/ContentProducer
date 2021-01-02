package com.example.providers.u.transport

import android.content.ContentResolver
import kotlin.reflect.KClass


/**
 * Transports manager
 * @目标: 用代理实现来自[Transporter]的处理[ContentResolver]请求
 * @constructor Create empty Transports manager
 */
interface TransportsManager {

    companion object {

    }

    val truck:Truck

    /**
     * Resign transport
     *
     * 注册[Transporter]
     */
    fun <T:Transporter> resignTransport(klass:KClass<T>)

    /**
     * Get
     *
     * 获取[T]的代理后对象
     */
    operator fun <T:Transporter>  get(klass:KClass<T>):T
}