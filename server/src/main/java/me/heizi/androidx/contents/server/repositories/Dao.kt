package me.heizi.androidx.contents.server.repositories

import android.database.Cursor

interface Dao {
    fun query(sql:String,args:Array<Any?>):Cursor
}