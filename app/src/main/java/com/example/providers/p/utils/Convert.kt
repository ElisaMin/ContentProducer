package com.example.providers.p.utils

import android.content.ContentValues
import com.example.providers.annotations.Field

inline fun <reified T:Any> ContentValues.convert():T {
    val cls = T::class.java
    val instance = cls.newInstance()
    for (f in cls.declaredFields) {
        val key = f.getAnnotation(Field::class.java)?.name ?: f.name
        f.set(instance,this[key])
    }
    return instance
 }
