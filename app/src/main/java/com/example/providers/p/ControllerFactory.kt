package com.example.providers.p

import com.example.providers.p.controller.interfaces.ForInsert
import com.example.providers.p.controller.interfaces.ForQuery
import com.example.providers.p.dao.Dao

interface ControllerFactory {
    val insert:ForInsert
    val query:ForQuery
    companion object {
        operator fun invoke(
                baseUri:String,
                dao: Dao
        ) :ControllerFactory = object : ControllerFactory {
            private val _insert by lazy {
                ForInsert(baseUri)
            }
            override val insert: ForInsert
                get() = _insert
            override val query: ForQuery
                get() = query
        }
    }
}