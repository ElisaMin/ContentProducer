package com.example.providers.u.repositories

import android.content.ContentResolver
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.room.Insert
import com.example.providers.annotations.Field
import com.example.providers.annotations.Path
import com.example.providers.u.utils.convert
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
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





    fun getUserByName(name:String)
        = doGet(KV::class,)


    data class KV(
        var id: Int = 0,
        var name:String?=null
    )

    //    annotation class FieldName(val name:String)


    fun getTypeByID(id:String)
        = doGet<KV>(
            path = "getTypeID",
            "id" to id
        )


    fun getBaseUri() = "content://examble.test"
    private fun uri(path: String)=Uri.parse("${getBaseUri()}$path")


    annotation class GET
    annotation class PUT
    annotation class UPDATE
    annotation class DEL

    interface Transporter{
        @GET @Path("getUserByName")
        fun getUserByName(name:String):KV
    }
    fun getInstance() {

    }

    private fun <T:Any> doInsert (
        table:String,
        instance:T,
    ):Boolean {
        val values = ContentValues()

        for (f in instance::class.java.declaredFields) {
            val colName  = f.getAnnotation(Field::class.java)?.name ?: f.name
            f.type.javaClass
            val colValue = f.get(instance::class.java)
            values.put(colName,colValue?.toString())
        }

        return resolver.insert (uri("/"), values)?.getQueryParameter("success") == "1"
    }

    private fun <T:Any> doGet(path: String,vararg map:Pair<String,String?>) {

    }

    private fun <T:Any> doGet(
        cls:KClass<T>,
        path:String = "",
        values:Map<String,String>? = null
    ) {
        resolver.query(
            uri(path),
            projection = values?.keys?.toTypedArray(),
            selectArgs = values?.values?.toTypedArray()
        )?.convert(cls)
    }
}

interface A {
    fun a():String
}
//TODO("创建一个对象在a函数返回'refrection' ")

fun getA():A = Proxy.newProxyInstance(A::class.java.classLoader, arrayOf(A::class.java))
    result@{ proxy, method:Method, args ->
        return@result "refrection"
    } as A

fun main() {
    getA().a().let(::println)
}

