package me.heizi.androidx.content_market.p.dao.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "books",
//    foreignKeys = [
//        ForeignKey(
//            entity = BookType::class,
//            parentColumns = ["id"],
//            childColumns = ["type_id"]
//        ), ForeignKey(
//            entity = Publisher::class,
//            parentColumns = ["id"],
//            childColumns = ["publisher_id"]
//        )
//    ]
)
data class BookInf(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name="type_id")
//            @ForeignKey(entity = BookType::class,parentColumns = ["id"],childColumns = ["table_id"])
    var typeID:Int,
    @ColumnInfo(name="publisher_id") var publisherID:Int,
)