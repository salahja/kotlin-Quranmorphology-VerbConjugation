package com.example.mushafconsolidated.DAO
import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.surahsummary


//.surahsummary


@Dao
interface surahsummaryDao {
    @Query("SELECT * FROM surahsummary where surahid=:id   ORDER BY surahid")
     fun getSurahSummary(id: Int): List<surahsummary?>?
}