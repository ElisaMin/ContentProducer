package me.heizi.androidx.content_market.p

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.annotation.CallSuper
import me.heizi.androidx.content_market.p.controller.interfaces.ForDelete
import me.heizi.androidx.content_market.p.controller.interfaces.ForInsert
import me.heizi.androidx.content_market.p.controller.interfaces.ForQuery
import me.heizi.androidx.content_market.p.dao.Dao

/**
 * Content producer
 *
 * @property authority
 * @constructor Create empty Content producer
 */
abstract class ContentProducer <In: ForInsert, Qr: ForQuery, Dl: ForDelete, Up> constructor(
        private val authority:String,
)  : ContentProvider() {

    val baseUri = "content://$authority"
    abstract val dao: Dao

    abstract val controllers: Controllers<In, Qr, Dl, Up>

    @CallSuper
    override fun insert(uri: Uri, values: ContentValues?): Uri?
        = values?.let { controllers.insert.insert(uri,it) }

    @CallSuper
    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String?>?, sortOrder: String?): Cursor?
        = if (projection.isNullOrEmpty() or selectionArgs.isNullOrEmpty() )  throw Error("Nulled")
        else controllers.query.query(uri,projection!!,selectionArgs!!)
}