package com.example.providers.p.dao

import android.database.Cursor

interface Dao {


    fun query(sql:String,args:Array<Any?>):Cursor
}