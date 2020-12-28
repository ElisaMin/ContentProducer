package com.example.providers.p.controller.interfaces

import android.database.Cursor
import com.example.providers.annotations.Path
import com.example.providers.p.dao.Dao


open class ForQuery constructor(
    val dao: Dao,
    baseUri:String,
) :PathController<Cursor>(baseUri) {
    operator fun String.rem(args: Array<Any?>) = dao.query(this,args)

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