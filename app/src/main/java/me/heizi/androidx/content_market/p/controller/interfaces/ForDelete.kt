package me.heizi.androidx.content_market.p.controller.interfaces

import com.androidx.providers.p.controller.interfaces.PathController
import me.heizi.androidx.content_market.p.dao.Dao

open class ForDelete(baseUri:String, dao: Dao): PathController<Int>(baseUri) {

}