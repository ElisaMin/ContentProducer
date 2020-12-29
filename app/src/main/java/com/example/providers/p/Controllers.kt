package com.example.providers.p

import com.example.providers.p.controller.interfaces.ForDelete
import com.example.providers.p.controller.interfaces.ForInsert
import com.example.providers.p.controller.interfaces.ForQuery
import com.example.providers.p.controller.interfaces.PathController
import com.example.providers.p.dao.Dao
import kotlin.reflect.KClass

interface Controllers<In:ForInsert, Qr:ForQuery, Dl:ForDelete, Up>{
    val insert:In
    val query:Qr
    val delete:Dl
    val update:Up

}