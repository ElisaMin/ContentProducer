package com.example.providers.p.controller.interfaces

import com.example.providers.p.dao.Dao

open class ForDelete(baseUri:String, dao: Dao): PathController<Int>(baseUri) {

}