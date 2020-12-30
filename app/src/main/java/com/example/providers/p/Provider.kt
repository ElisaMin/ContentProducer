package com.example.providers.p

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.example.providers.p.controller.InsertController
import com.example.providers.p.controller.QueryController
import com.example.providers.p.controller.interfaces.ForDelete
import com.example.providers.p.dao.Dao
import com.example.providers.p.dao.Databases.Companion.rem
import com.example.providers.p.dao.Databases
import com.example.providers.p.dao.entities.BookInf
import com.example.providers.p.dao.entities.BookType
import com.example.providers.p.dao.entities.Publisher

class  Provider  : ContentProducer <InsertController,QueryController,ForDelete,Any?> (
        "examble.test"
)  {
    override val dao: Dao
        get() = Databases.Dao


    override fun getType(uri: Uri): String {
        return "vnd.android.cursor.dir/abc"
    }

    override fun onCreate(): Boolean {
        Databases.getInstance(context!!)
        return true
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }
    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }

    override val controllers = object:Controllers<InsertController, QueryController, ForDelete, Any?> {
                override val insert: InsertController = InsertController(baseUri)
                override val query: QueryController = QueryController(dao,baseUri)
                override val delete: ForDelete = ForDelete(baseUri,dao)
                override val update = null
    }


}