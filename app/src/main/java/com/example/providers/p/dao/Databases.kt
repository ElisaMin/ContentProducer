package com.example.providers.p.dao

import android.content.Context
import android.database.Cursor
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.providers.p.dao.entities.BookInf
import com.example.providers.p.dao.entities.BookType
import com.example.providers.p.dao.entities.Publisher

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        BookInf::class,
        Publisher::class,
        BookType::class
    ]
)
abstract class Databases : RoomDatabase() {

    abstract fun dao(): BookInfoDao

     companion object {
         val dao get() = instance.dao()
         val instance get() =  _instance!!
         private var _instance: Databases?=null
         fun getInstance(context: Context): Databases {
             if (_instance ==null)
                 _instance = Room.databaseBuilder(
                     context,
                     Databases::class.java,
                     "book"
                 ).allowMainThreadQueries().build()
             return instance
         }
         fun selectAllFromTable(table:String) = instance.query("select * from $table ", null)
         operator fun String.rem(args: Array<String>?): Cursor {
             return instance.query(this,args)
         }


     }
}