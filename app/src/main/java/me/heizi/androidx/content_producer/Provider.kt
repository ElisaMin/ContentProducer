package me.heizi.androidx.content_producer

import android.content.ContentValues
import android.net.Uri
import me.heizi.androidx.contents.server.Controllers
import me.heizi.androidx.content_producer.dao.Databases
import me.heizi.androidx.content_producer.repo.InsertController
import me.heizi.androidx.content_producer.repo.QueryController
import me.heizi.androidx.contents.server.ContentProducer
import me.heizi.androidx.contents.server.controller.Delete
import me.heizi.androidx.contents.server.repositories.Dao

class  Provider  : ContentProducer<InsertController, QueryController, Delete, Any?>(
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

    override val controllers = object:
        Controllers<InsertController, QueryController, Delete, Any?> {
                override val insert: InsertController =
                    InsertController(baseUri)
                override val query: QueryController =
                    QueryController(dao, baseUri)
                override val delete: Delete = Delete(baseUri,dao)
                override val update = null
    }


}