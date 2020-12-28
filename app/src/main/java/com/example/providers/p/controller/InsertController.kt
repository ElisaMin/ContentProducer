package com.example.providers.p.controller

import android.content.ContentValues
import android.net.Uri
import com.example.providers.annotations.Path
import com.example.providers.p.Provider
import com.example.providers.p.Provider.Companion.baseUri
import com.example.providers.p.controller.interfaces.ForInsert
import com.example.providers.p.teamplates.InsertTemplate
import com.example.providers.p.dao.Databases as db
import com.example.providers.p.utils.convert
import kotlin.reflect.KCallable
import kotlin.reflect.KFunction

@Path("/put")
class InsertController :ForInsert() {

    private val template = InsertTemplate("${Provider.baseUri}/result")

    @Path("/types")
    fun addType(values: ContentValues): Uri = template.runCatching {
        db.dao.addType(values.convert())
    }

    @Path("/publisher")
    fun addPublisher(values: ContentValues) = template.runCatching {
        db.dao.addPublisher(values.convert())
    }

}