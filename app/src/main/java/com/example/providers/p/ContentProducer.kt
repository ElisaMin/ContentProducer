package com.example.providers.p

import android.content.ContentProvider
import com.example.providers.p.controller.interfaces.PathController

/**
 * Content producer
 *
 * @property authority
 * @constructor Create empty Content producer
 */
abstract class ContentProducer constructor(
        private val authority:String,
)  : ContentProvider() {
    private val baseUri = "content://$authority"


    init {
        //循环便利成员变量 进行操作
        javaClass.fields.forEach { field ->
            //Controller.baseUri赋值
            when(val obj = field.get(this)) {
                is PathController<*> -> {
                    obj.baseUri = baseUri
                }
            }

        }
    }
}
//data class b(var b: String)
//class a (
//        b: String
//){
//    lateinit var d:String
//    val f = b("f")
//    lateinit var e:a
//}

//fun getA() {
//    a::class.constructors.forEach {
////        if (it.parameters.size == 1 && it.parameters[0])
//    }
//}
//fun main() {
//    val aa =a("2")
//    a::class.declaredMemberProperties.forEach {
//
//        println(it.getter)
//
//        when(it) {
//            is KMutableProperty<*> -> {
//                println(it.returnType)
//            }
//            else -> {
//                (it.get(aa) as b).also { it.b = "aaa"} .let(::println)
//            }
//        }
//    }
//        a::class.let {  cls ->
//            cls.constructors.forEach {
////                if (it.parameters.size==1 && it.parameters[0].type.equals())
//
//
//
//                it.parameters[0].type.let {
//                    it == String::class.starProjectedType
//                }.let(::println)
//
//
//            }
//        }

//}