package me.heizi.androidx.content_market.p.dao

import androidx.room.Dao
import androidx.room.Insert
import me.heizi.androidx.content_market.p.dao.entities.BookInf
import me.heizi.androidx.content_market.p.dao.entities.BookType
import me.heizi.androidx.content_market.p.dao.entities.Publisher

@Dao
interface BookInfoDao{
//            @Query("SELECT b.name bookName,p.name publisher,t.name type FROM BOOKS b,PUBLISHERS p,book_types t WHERE type_id =t.id and publisher_id = type_id")
//            fun selectPojo(): List<POJO.BookInfo>

    @Insert
    fun addBook(bookInf: BookInf)
    @Insert
    fun addPublisher(publisher: Publisher)
    @Insert
    fun addType(type: BookType)
}