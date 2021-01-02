package me.heizi.androidx.content_market.p.controller

import me.heizi.androidx.content_market.annotation.Path
import me.heizi.androidx.content_market.p.controller.interfaces.ForInsert
import me.heizi.androidx.content_market.p.teamplates.InsertTemplate


@Path("/put")
class InsertController constructor (
    baseUri:String,
) : ForInsert(baseUri) {

    private val template = InsertTemplate(baseUri)

//    @Path("/types")
//    fun addType(values: ContentValues): Uri = template.runCatching {
//        me.heizi.androidx.content_market.p.dao.Databases.dao.addType(values.convert())
//    }
//
//    @Path("/publisher")
//    fun addPublisher(values: ContentValues) = template.runCatching {
//        me.heizi.androidx.content_market.p.dao.Databases.dao.addPublisher(values.convert())
//    }

}