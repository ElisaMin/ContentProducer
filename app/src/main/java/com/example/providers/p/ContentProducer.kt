package com.example.providers.p

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri
import com.example.providers.p.controller.interfaces.PathController
import com.example.providers.p.dao.Dao

/**
 * Content producer
 *
 * @property authority
 * @constructor Create empty Content producer
 */
abstract class ContentProducer constructor(
        private val authority:String,

)  : ContentProvider() {

    private val baseUri = "content://$authority"
    abstract val dao:Dao

    private val controllers by lazy {
        ControllerFactory(baseUri,dao)
    }




    init {
        //循环便利成员变量 进行操作
        javaClass.fields.forEach { field ->
            //Controller.baseUri赋值
            when(val obj = field.get(this)) {
                is PathController<*> -> {
                    obj.baseUri = baseUri
                }
            }

        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri?
        = values?.let { controllers.insert.insert(uri,it) }
}