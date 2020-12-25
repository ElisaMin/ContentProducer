package com.example.providers

import android.content.ContentResolver
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.core.database.getStringOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

class Repository constructor(
    private val resolver:ContentResolver
) {
    fun ContentResolver.query (
        uri: Uri,
        projection:Array<String>?=null,
        selection:String?=null,
        selectArgs:Array<String>?=null,
        sortOder:String?=null
    ):Cursor? = this.query(uri, projection,selection,selectArgs,sortOder)

//    val types by lazy {
//        MutableStateFlow("")
//    }


    data class KV(
        var id: Int = 0,
        var name:String?=null
    )

    annotation class CursorField(val name:String)
//    annotation class FieldName(val name:String)

    private fun getTypes() {
        resolver.query(
            uri = uri("/"),
            selection = "type"
        )?.let {}
    }

    fun getTypeByID(id:String) = doGet(
        KV::class,
        "/type",
        values = mapOf(
            "id" to id
        )
    )

    fun getBaseUri() = "content://examble.test"
    private fun uri(path: String)=Uri.parse("${getBaseUri()}$path")


    private fun <T:Any> doInsert (
        table:String,
        instance:T,
    ):Boolean {
        val values = ContentValues()

        for (f in instance::class.java.declaredFields) {
            val colName  = f.getAnnotation(CursorField::class.java)?.name ?: f.name
            f.type.javaClass
            val colValue = f.get(instance::class.java)
            values.put(colName,colValue?.toString())
        }

        return resolver.insert (uri("/"), values)?.getQueryParameter("success") == "1"
    }

    private fun <T:Any> doGet(
        cls:KClass<T>,
        path:String,
        values:Map<String,String>? = null
    ) {
        resolver.query(
            uri(path),
            projection = values?.keys?.toTypedArray(),
            selectArgs = values?.values?.toTypedArray()
        )?.convert(cls)
    }


    private fun <T:Any> Cursor.convert( cls:KClass<T>) = if(moveToFirst()) {

        ArrayList<T>().also {list->
            do {
                //获取单例
                val elm = cls.java.newInstance()
                //循环查找类的字段
                for (f in cls.java.declaredFields) {
                    //检查是否存在@CursorFlied 存在时拿name不存在时拿变量名称
                    val columnIndex = getColumnIndex(
                        f.getAnnotation(CursorField::class.java)?.name ?: f.name
                    )
                    //检查是否通过当通过时判断类型
                    if (columnIndex!=-1) when(this.getType(columnIndex)) {

                        Cursor.FIELD_TYPE_INTEGER ->
                            f.setInt(elm,getInt(columnIndex))

                        Cursor.FIELD_TYPE_STRING ->
                            f.set(elm,getString(columnIndex))

                        Cursor.FIELD_TYPE_BLOB ->
                            f.set(elm,getBlob(columnIndex))

                        Cursor.FIELD_TYPE_FLOAT ->
                            f.setFloat(elm,getFloat(columnIndex))
                    }
                }
                list.add(elm)
            }while (moveToNext())
            close()
        }
    } else null

}
