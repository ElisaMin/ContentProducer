package me.heizi.androidx.content_market.p.dao

import android.content.Context
import android.database.Cursor
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.heizi.androidx.content_market.p.dao.entities.BookInf
import me.heizi.androidx.content_market.p.dao.entities.BookType
import me.heizi.androidx.content_market.p.dao.entities.Publisher

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        BookInf::class,
        Publisher::class,
        BookType::class
    ]
)
abstract class Databases : RoomDatabase(){

    abstract fun dao(): BookInfoDao


    object Dao : me.heizi.androidx.content_market.p.dao.Dao {
        override fun query(sql: String, args: Array<Any?>): Cursor {
            return instance.query(sql,args)
        }
    }

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
         operator fun String.rem(args: Array<String>?): Cursor {
             return instance.query(this,args)
         }


     }
}