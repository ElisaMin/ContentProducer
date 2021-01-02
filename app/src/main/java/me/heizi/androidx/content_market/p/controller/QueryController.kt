package me.heizi.androidx.content_market.p.controller

import me.heizi.androidx.content_market.annotations.Path
import me.heizi.androidx.content_market.p.controller.interfaces.ForQuery
import me.heizi.androidx.content_market.p.dao.Dao
import me.heizi.androidx.content_market.p.teamplates.QueryTemplate

@Path("get")
class QueryController(dao: Dao, baseUri:String,): ForQuery(dao,baseUri) {

    val template = QueryTemplate(baseUri)

    @Path("findUsers")
    fun findUsers(map: Map<String,String?>) =  template.runCatching{
        "select * from users where name like '%?%'" % arrayOf(map["nameLike"])
    }

}