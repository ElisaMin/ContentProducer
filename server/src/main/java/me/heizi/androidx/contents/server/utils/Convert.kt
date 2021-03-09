package me.heizi.androidx.contents.server.utils

import android.content.ContentValues
import me.heizi.androidx.contents.server.annotations.Field

/**
 * Convert
 * 将ContentValues转换成相应的对象
 * 例子：<br>
 * 在类A有firstName变量 Values是first_name 则需要加上[Field]注解该变量
 * <code>
 *     .....
 *     @Field("first_name")
 *     val firstName ......
 * </code>
 *
 * @param T：目标类型
 * @return
 */
inline fun <reified T:Any> ContentValues.convert():T {
    val cls = T::class.java
    val instance = cls.newInstance()
    for (f in cls.declaredFields) {
        val key = f.getAnnotation(Field::class.java)?.name ?: f.name
        f.set(instance,this[key])
    }
    return instance
 }
