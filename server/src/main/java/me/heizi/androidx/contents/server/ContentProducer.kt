package me.heizi.androidx.contents.server

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.annotation.CallSuper
import me.heizi.androidx.contents.server.controller.Delete
import me.heizi.androidx.contents.server.controller.Insert
import me.heizi.androidx.contents.server.controller.Query
import me.heizi.androidx.contents.server.repositories.Dao

/**
 * Content producer
 *
 * @property authority
 * @constructor Create empty Content producer
 */
abstract class ContentProducer <In: Insert, Qr: Query, Dl: Delete, Up> constructor(
        private val authority:String,
)  : ContentProvider() {

    val baseUri = "content://$authority"
    abstract val dao: Dao

    abstract val controllers: Controllers<In, Qr, Dl, Up>

    @CallSuper
    final override fun insert(uri: Uri, values: ContentValues?): Uri?
        = values?.let { controllers.insert.insert(uri,it) }

    @CallSuper
    final override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String?>?, sortOrder: String?): Cursor?
        = if (projection.isNullOrEmpty() or selectionArgs.isNullOrEmpty() )  throw Error("Nulled")
        else controllers.query.query(uri,projection!!,selectionArgs!!)
}
