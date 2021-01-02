package com.example.providers.u.utils

import android.database.Cursor
import com.example.providers.annotations.Field
import kotlin.reflect.KClass

/**
 * 将[Cursor]转换成[ArrayList]
 * 以[T]内的成员变量名字为准 需要时加上[Field]
 * <code>
 *     .........
 *     @Field("user_id")
 *     val userID ......
 * </code>
 * @param cls 转换成为的目标
 */
fun <T:Any> Cursor.convert(cls: KClass<T>) = if(moveToFirst()) {
    ArrayList<T>().also {list->
        do {
            //获取单例
            val elm = cls.java.newInstance()
            //循环查找类的字段
            for (f in cls.java.declaredFields) {
                //检查是否存在@CursorFlied 存在时拿name不存在时拿变量名称
                val columnIndex = getColumnIndex(
                        f.getAnnotation(Field::class.java)?.name ?: f.name
                )
                //检查是否通过当通过时判断类型
                if (columnIndex!=-1) when(this.getType(columnIndex)) {
                    Cursor.FIELD_TYPE_INTEGER -> f.setInt(elm,getInt(columnIndex))
                    Cursor.FIELD_TYPE_STRING -> f.set(elm,getString(columnIndex))
                    Cursor.FIELD_TYPE_BLOB -> f.set(elm,getBlob(columnIndex))
                    Cursor.FIELD_TYPE_FLOAT -> f.setFloat(elm,getFloat(columnIndex))
                }
            }
            list.add(elm)
        }while (moveToNext())
        close()
    }
} else null

/**
 * Convert
 *
 * JAVA版
 */
fun <T:Any> Cursor.convert(cls: Class<T>) = if(moveToFirst()) {
    ArrayList<T>().also {list->
        do {
            //获取单例
            val elm = cls.newInstance()
            //循环查找类的字段
            for (f in cls.declaredFields) {
                //检查是否存在@CursorFlied 存在时拿name不存在时拿变量名称
                val columnIndex = getColumnIndex(
                        f.getAnnotation(Field::class.java)?.name ?: f.name
                )
                //检查是否通过当通过时判断类型
                if (columnIndex!=-1) when(this.getType(columnIndex)) {
                    Cursor.FIELD_TYPE_INTEGER -> f.setInt(elm,getInt(columnIndex))
                    Cursor.FIELD_TYPE_STRING -> f.set(elm,getString(columnIndex))
                    Cursor.FIELD_TYPE_BLOB -> f.set(elm,getBlob(columnIndex))
                    Cursor.FIELD_TYPE_FLOAT -> f.setFloat(elm,getFloat(columnIndex))
                }
            }
            list.add(elm)
        }while (moveToNext())
        close()
    }
} else null
