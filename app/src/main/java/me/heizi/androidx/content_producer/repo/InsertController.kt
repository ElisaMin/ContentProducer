package me.heizi.androidx.content_producer.repo

import android.content.ContentValues
import android.net.Uri
import me.heizi.androidx.contents.server.annotations.Path
import me.heizi.androidx.contents.server.controller.Insert
import me.heizi.androidx.contents.server.teamplates.InsertTemplate
import me.heizi.androidx.contents.server.utils.convert
import me.heizi.androidx.content_producer.dao.Databases as db


@Path("/put")
class InsertController constructor (
    baseUri:String,
) : Insert(baseUri) {

    private val template = InsertTemplate(baseUri)

    @Path("/types")
    fun addType(values: ContentValues): Uri = template.runCatching {
        db.dao.addType(values.convert())
    }

    @Path("/publisher")
    fun addPublisher(values: ContentValues) = template.runCatching {
        db.dao.addPublisher(values.convert())
    }

}