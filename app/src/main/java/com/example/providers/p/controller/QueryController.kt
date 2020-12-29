package com.example.providers.p.controller

import android.database.Cursor
import android.net.Uri
import androidx.room.Query
import com.example.providers.p.dao.Databases as db
import com.example.providers.annotations.Path
import com.example.providers.p.controller.interfaces.ForQuery
import com.example.providers.p.dao.Dao
import com.example.providers.p.teamplates.QueryTemplate
import com.example.providers.p.utils.Path.Companion.paths

@Path("get")
class QueryController(dao: Dao,baseUri:String,):ForQuery(dao,baseUri) {

    val template = QueryTemplate(baseUri)

    @Path("findUsers")
    fun findUsers(map: Map<String,String?>) =  template.runCatching{
        "select * from users where name like '%?%'" % arrayOf(map["nameLike"])
    }

}