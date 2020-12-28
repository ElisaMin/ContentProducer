package com.example.providers.p.controller.interfaces

import android.database.Cursor
import com.example.providers.annotations.Path


open class ForQuery:PathController<Cursor>() {


}


//open class a : PathController<String>() {
//    fun boot(path:String):String? {
//
//        return map[path]?.call(this,"")
//    }
//}
//@Path("root")
//open class b:a() {
//    init {
//        println(map)
//    }
//    @Path("haha") fun haha(b: String) = "adfgfgsdfgfffgfggb"
//}
//fun main() {
//    println(b().boot("haha"))
//}