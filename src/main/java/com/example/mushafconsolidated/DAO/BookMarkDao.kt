package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mushafconsolidated.Entities.BookMarks

@Dao
interface BookMarkDao {


    @Query("SELECT * FROM bookmark ORDER BY datetime")
    fun getBookMarks(): List<BookMarks?>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(entity: BookMarks?)

    @Delete
    fun deletebookmark(bookMarks: BookMarks?)

    @Query("Delete from bookmark")
    fun deleteAllBookMakrs()

    @get:Query(" select  count(*) as count, * from bookmark group by header")
    val collectionCount: List<BookMarks?>?

    @Query(" select    * from bookmark where header=:str group by header")
    fun getCollectionbygroup(str: String?): List<BookMarks?>?

    @Query(" select    * from bookmark where header=:str order by datetime")
    fun getAllBookmarks(str: String?): List<BookMarks?>?

    @Query(" delete from bookmark where header=:str ")
    fun deleteCollection(str: String?): Int
}
