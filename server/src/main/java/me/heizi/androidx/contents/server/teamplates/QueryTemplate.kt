package me.heizi.androidx.contents.server.teamplates

import android.content.ContentResolver
import android.database.CharArrayBuffer
import android.database.ContentObserver
import android.database.Cursor
import android.database.DataSetObserver
import android.net.Uri
import android.os.Bundle
import me.heizi.androidx.contents.server.teamplates.interfaces.Template

// if some kinda like error then return a error else a result
class QueryTemplate constructor(
    baseUri:String
): Template(baseUri) {

    inline fun <reified T:Cursor> runCatching(
        crossinline block:()->T
    ) :Cursor = try {
        block()
    }catch (e:Exception){
        ErrorCursor(
            error = "${e::class.simpleName}\n${e.message}",baseUri
        )
    }

    /**
     * Error cursor
     * 这不太重要
     *
     * @property error
     * @property baseUri
     * @constructor Create empty Error cursor
     */
    class ErrorCursor(
        private val error:String,
        private val baseUri: String
    ):Cursor {
        //useful
        override fun getNotificationUri(): Uri= Uri.parse("$baseUri/error")
        override fun getString(columnIndex: Int): String=error
        override fun getColumnName(columnIndex: Int): String="error"
        override fun close() {}
        //move or place
        override fun getPosition(): Int = -2
        override fun getCount(): Int = -1
        override fun move(offset: Int): Boolean =false
        override fun moveToPosition(position: Int): Boolean =false
        override fun moveToFirst(): Boolean = false
        override fun moveToLast(): Boolean = false
        override fun moveToNext(): Boolean = false
        override fun moveToPrevious(): Boolean = false
        override fun isFirst(): Boolean = false
        override fun isLast(): Boolean = false
        override fun isBeforeFirst(): Boolean = false
        override fun isAfterLast(): Boolean = false
        override fun getColumnCount(): Int =-1
        override fun getColumnIndex(columnName: String?): Int =-1
        override fun getColumnIndexOrThrow(columnName: String?): Int =-1

        //getter
        override fun getShort(columnIndex: Int): Short = -1
        override fun getInt(columnIndex: Int): Int =-1
        override fun getLong(columnIndex: Int): Long=-1L
        override fun getFloat(columnIndex: Int): Float=-1f
        override fun getDouble(columnIndex: Int): Double=-1.0
        override fun getType(columnIndex: Int): Int =-1
        override fun deactivate() {throw NoSuchMethodError()}
        override fun requery(): Boolean= false
        override fun isNull(columnIndex: Int): Boolean=true
        override fun isClosed(): Boolean=false
        //throws
        override fun getBlob(columnIndex: Int): ByteArray {throw NoSuchMethodError()}
        override fun getColumnNames(): Array<String>{throw NoSuchMethodError()}
        override fun registerContentObserver(observer: ContentObserver?) {throw NoSuchMethodError()}
        override fun unregisterContentObserver(observer: ContentObserver?){throw NoSuchMethodError()}
        override fun registerDataSetObserver(observer: DataSetObserver?){throw NoSuchMethodError()}
        override fun unregisterDataSetObserver(observer: DataSetObserver?){throw NoSuchMethodError()}
        override fun setNotificationUri(cr: ContentResolver?, uri: Uri?){throw NoSuchMethodError()}
        override fun getWantsAllOnMoveCalls(): Boolean{throw NoSuchMethodError()}
        override fun copyStringToBuffer(columnIndex: Int, buffer: CharArrayBuffer?) {throw NoSuchMethodError()}
        override fun setExtras(extras: Bundle?){throw NoSuchMethodError()}
        override fun getExtras(): Bundle {throw NoSuchMethodError()}
        override fun respond(extras: Bundle?):Bundle {throw NoSuchMethodError()}
    }

}