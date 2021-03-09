package me.heizi.androidx.contents.server

import me.heizi.androidx.contents.server.controller.Delete
import me.heizi.androidx.contents.server.controller.Insert
import me.heizi.androidx.contents.server.controller.Query

interface Controllers<In: Insert, Qr: Query, Dl: Delete, Up>{
    val insert:In
    val query:Qr
    val delete:Dl
    val update:Up

}