package com.example.providers.p.controller

import android.content.ContentValues
import android.net.Uri
import com.example.providers.annotations.Path
import com.example.providers.p.controller.interfaces.ForInsert
import com.example.providers.p.teamplates.InsertTemplate
import com.example.providers.p.utils.convert
import com.example.providers.p.dao.Databases as db


@Path("/put")
class InsertController constructor (
    baseUri:String,
) :ForInsert(baseUri) {

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