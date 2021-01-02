package me.heizi.androidx.content_market.p

import android.content.ContentValues
import android.net.Uri
import me.heizi.androidx.content_market.p.ContentProducer
import me.heizi.androidx.content_market.p.Controllers
import me.heizi.androidx.content_market.p.controller.InsertController
import me.heizi.androidx.content_market.p.controller.QueryController
import me.heizi.androidx.content_market.p.controller.interfaces.ForDelete
import me.heizi.androidx.content_market.p.dao.Dao
import me.heizi.androidx.content_market.p.dao.Databases

class  Provider  : ContentProducer<InsertController, QueryController, ForDelete, Any?>(
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

    override val controllers = object: Controllers<InsertController, QueryController, ForDelete, Any?> {
                override val insert: InsertController = InsertController(baseUri)
                override val query: QueryController = QueryController(dao,baseUri)
                override val delete: ForDelete = ForDelete(baseUri,dao)
                override val update = null
    }


}