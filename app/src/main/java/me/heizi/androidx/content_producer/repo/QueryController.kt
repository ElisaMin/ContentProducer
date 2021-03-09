package me.heizi.androidx.content_producer.repo

import me.heizi.androidx.contents.server.annotations.Path
import me.heizi.androidx.contents.server.controller.Query
import me.heizi.androidx.contents.server.repositories.Dao
import me.heizi.androidx.contents.server.teamplates.QueryTemplate

@Path("get")
class QueryController(dao: Dao, baseUri:String,): Query(dao,baseUri) {

    val template = QueryTemplate(baseUri)

    @Path("findUsers")
    fun findUsers(map: Map<String,String?>) =  template.runCatching{
        "select * from users where name like '%?%'" % arrayOf(map["nameLike"])
    }

}