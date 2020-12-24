package com.example.providers

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import androidx.core.database.getStringOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

class Repository constructor(
    private val resolver:ContentResolver
) {
    inline fun ContentResolver.query (
        uri: Uri,
        projection:Array<String>?=null,
        selection:String?=null,
        selectArgs:Array<String>?=null,
        sortOder:String?=null
    ):Cursor? = this.query(uri, projection,selection,selectArgs,sortOder)

    val types by lazy {
        MutableStateFlow("")
    }


    data class KV(
        @CursorField("")
        var id: Int = 0,
        var name:String?=null
    )

    annotation class CursorField(val name:String)

    fun getTypes() {
        resolver.query(
            uri = Uri.parse("content://examble.test/"),
            selection = "type"
        )?.let {}

    }


    private fun <T:Any> Cursor.convert( cls:KClass<T>) = ArrayList<T>().also {list->
        if(moveToFirst()) {
            do {
                val elm = cls.java.newInstance()
                for (f in cls.java.declaredFields) {
                    val ano =
                }
                list.add(

                    cls.java.newInstance().also { instance ->
                        cls.java.declaredFields.forEach { field->

                            field.getAnnotation(CursorField::class.java)?.let {ano ->
                                field.set(instance,
                                    when(this.getType(getColumnIndex(ano.name))) {
                                        Cursor.FIELD_TYPE_INTEGER->getString(getColumnIndex(ano.name))
                                        Cursor.FIELD_TYPE_STRING->getString(getColumnIndex(ano.name))
                                        Cursor.FIELD_TYPE_NULL -> null
                                        Cursor.FIELD_TYPE_BLOB -> getBlob(getColumnIndex(ano.name))
                                        Cursor.FIELD_TYPE_FLOAT->getFloat(getColumnIndex(ano.name))
                                        else ->null
                                    }
                                )
                            }?: kotlin.run {

                            }
                        }
                    }
                )
                cls.java.fields.forEach {

                }
            }while (moveToNext())
        }
    }

}
