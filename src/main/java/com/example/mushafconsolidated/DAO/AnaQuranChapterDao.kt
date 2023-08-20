package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity


@Dao
interface AnaQuranChapterDao {
    @get:Query("SELECT * FROM chaptersana ORDER BY chapterid")
    val chapters: List<ChaptersAnaEntity?>?

    @Query("SELECT * FROM chaptersana where chapterid=:id")
    fun getSingleChapters(id: Int): List<ChaptersAnaEntity?>?
}

