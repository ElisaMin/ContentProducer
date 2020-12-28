package com.example.providers.p.controller

import android.database.Cursor
import androidx.room.Query
import com.example.providers.p.dao.Databases as db
import com.example.providers.annotations.Path
import com.example.providers.p.controller.interfaces.ForQuery
import com.example.providers.p.dao.Dao
import com.example.providers.p.teamplates.QueryTemplate

@Path("get")
class QueryController(dao: Dao,baseUri:String,):ForQuery(dao,baseUri) {

    val template = QueryTemplate(baseUri)

    @Path("userExist")
    fun isUserExists(map: Map<String,Any?>) =  template.runCatching{
        "" % arrayOf()
    }

    fun query(argKey:Array<String>,argValue:Array<Any?>) {

    }

}