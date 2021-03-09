package me.heizi.androidx.contents.server.controller

import me.heizi.androidx.contents.server.controller.interfaces.PathController
import me.heizi.androidx.contents.server.repositories.Dao


open class Delete(baseUri:String, dao: Dao): PathController<Int>(baseUri) {

}