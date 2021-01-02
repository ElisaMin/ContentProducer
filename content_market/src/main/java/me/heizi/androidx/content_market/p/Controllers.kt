package me.heizi.androidx.content_market.p

import me.heizi.androidx.content_market.p.controller.interfaces.ForDelete
import me.heizi.androidx.content_market.p.controller.interfaces.ForInsert
import me.heizi.androidx.content_market.p.controller.interfaces.ForQuery

interface Controllers<In: ForInsert, Qr: ForQuery, Dl: ForDelete, Up>{
    val insert:In
    val query:Qr
    val delete:Dl
    val update:Up

}