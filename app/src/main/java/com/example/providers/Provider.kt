package com.example.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.room.*
import com.example.providers.Provider.Databases.Companion.rem

class Provider : ContentProvider() {

    object Testing {

        fun String.加个ABC():String {
            return this + "/abc"
        }

        @JvmStatic fun main(args: Array<String>) {

            val bl = "ddd"
            val blAbc = bl.加个ABC()

            println(blAbc)
        }
    }

    /**
     * 目标: bookName typeName publisherName
     */
    object Table {

        @Entity(tableName = "book_types")
        data class BookType(
             @PrimaryKey(autoGenerate = true) val id:Int = 0,
             @ColumnInfo(name = "name") var name:String
        )
        @Entity(tableName = "publishers")
        data class Publisher (
            @PrimaryKey(autoGenerate = true) val id:Int = 0,
            @ColumnInfo(name = "name") var name:String
        )
        @Entity(
            tableName = "books",
            foreignKeys = [
                ForeignKey(
                    entity =  BookType::class,
                    parentColumns = ["id"],
                    childColumns = ["type_id"]
                ), ForeignKey(
                    entity = Publisher::class,
                    parentColumns = ["id"],
                    childColumns = ["publisher_id"]
                )
            ]
        )
        data class BookInf(
            @PrimaryKey(autoGenerate = true) val id:Int = 0,
            @ColumnInfo(name = "name") var name:String,
            @ColumnInfo(name="type_id")
//            @ForeignKey(entity = BookType::class,parentColumns = ["id"],childColumns = ["table_id"])
            var typeID:Int,
            @ColumnInfo(name="publisher_id") var publisherID:Int,
        )


    }

//    object POJO {
//        data class BookInfo(
//            val bookName:String,
//            val publisher:String,
//            val type:String
//        )
//    }

    object DAO {
        @Dao interface BookInfoDao{
//            @Query("SELECT b.name bookName,p.name publisher,t.name type FROM BOOKS b,PUBLISHERS p,book_types t WHERE type_id =t.id and publisher_id = type_id")
//            fun selectPojo(): List<POJO.BookInfo>

            @Insert
            fun addBook(bookInf: Table.BookInf)
            @Insert
            fun addPublisher(publisher: Table.Publisher)
            @Insert
            fun addType(type:Table.BookType)
        }

    }
    @Database(
        version = 1,
        exportSchema = false,
        entities = [
            Table.BookInf::class,
            Table.Publisher::class,
            Table.BookType::class
        ]
    )
    abstract class Databases :RoomDatabase() {

        abstract fun dao():DAO.BookInfoDao

         companion object {
             val dao get() = instance.dao()
             val instance get() =  _instance!!
             private var _instance:Databases?=null
             fun getInstance(context: Context):Databases {
                 if (_instance==null)
                     _instance = Room.databaseBuilder(
                         context,
                         Databases::class.java,
                         "book"
                     ).allowMainThreadQueries().build()
                 return instance
             }
             fun selectAllFromTable(table:String) = instance.query("select * from $table ", null)

             fun selectTypeNameByID(id: String) {
                 // select name from book_types where id = *id / *id=20
                 // select name from book_types where id = 20;
                 // select name from book_types where id = ?; 替换？成为20
                 // query () 方法 可以 替换值并查询
                 instance.query("select name from book_types where id = ?", arrayOf(id))

             }
             //"aaa".rem(null)
             operator fun String.rem(args: Array<String>?):Cursor {
                 return instance.query(this,args)
             }


         }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }
    // hello ^name
    // "hello %s"%name
    // System.out.printf("hello %s",name)
    // "hello $name"



    override fun getType(uri: Uri): String {
        return "vnd.android.cursor.dir/abc"
    }

    inline operator fun ContentValues.invoke(crossinline block:()->String): String? = this.getAsString(block()).takeIf { !it.isNullOrEmpty() }
    operator fun ContentValues.rem(key:String): Int? = this.getAsInteger(key).takeIf { it>0 }
    operator fun ContentValues?.plus(other:String) = 0.1

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        0.1.rem(1)
        0.1 % 1
        return values?.let {
            values{"bookName"}?.let {
                fun getOrThrow(key: String) = values % key ?: throw NoSuchFieldException("id")
                Databases.dao.addBook(
                    Table.BookInf(
                        name = it,
                        id =  getOrThrow("id"),
                        typeID = getOrThrow("type_id"),
                        publisherID = getOrThrow("publisher_id")
                    )
                )
            }
            values{"publisherName"}?.let {
                Databases.dao.addPublisher(
                    Table.Publisher(
                        name = it
                    )
                )
            }
            values{"typeName"}?.let {
                Databases.dao.addType(
                    Table.BookType(
                        name = it
                    )
                )
            }

            uri
        } ?: throw NullPointerException()
    }

    override fun onCreate(): Boolean {
        Databases.getInstance(context!!)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {

        fun getArg(key: String) = selectionArgs!![projection!!.indexOf(key)]
        return if (projection==null && selectionArgs==null)
            when(selection) {
                "type" -> "select * from types" % null
                "publisher" -> "select * from publisher " % null
                else->"SELECT b.name bookName,p.name publisher,t.name type FROM BOOKS b,PUBLISHERS p,book_types t WHERE type_id =t.id and publisher_id = type_id" % null
            }
        else if (projection==null || selectionArgs==null) throw IllegalArgumentException("为null")
        //当不为空时如果有
        else if (projection.contains("table")) {  when (getArg("table")) {
            "type" -> {
                if (projection.contains("id")) "select * from book_types where id = ?" % arrayOf(getArg("id"))
                else Databases.selectAllFromTable("type")
            }
            "publisher" ->Databases.selectAllFromTable("publishers")
            else -> null
        }}
        else null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}