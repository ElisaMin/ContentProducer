package com.example.providers.p

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.example.providers.p.controller.InsertController
import com.example.providers.p.dao.Databases.Companion.rem
import com.example.providers.p.dao.Databases
import com.example.providers.p.dao.entities.BookInf
import com.example.providers.p.dao.entities.BookType
import com.example.providers.p.dao.entities.Publisher

class Provider : ContentProvider() {


    companion object {
        const val baseUri = "content://examble.test"
    }



    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }




    override fun getType(uri: Uri): String {
        return "vnd.android.cursor.dir/abc"
    }



    private val insertController = InsertController()
    override fun insert(uri: Uri, values: ContentValues?): Uri? = if (values==null) insertController.insert(uri) else insertController.insert(uri,values)

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
            "publisher" -> Databases.selectAllFromTable("publishers")
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