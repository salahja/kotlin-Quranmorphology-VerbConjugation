package database.Dao

import androidx.room.Dao
import androidx.room.Query
import database.entity.QuranVerbsEntity

@Dao
interface QuranVerbsDao {
    @Query(value = "SELECT * FROM quranverbs order by CASE when :isASC=1 THEN form END ASC,CASE when :isASC=0 THEN frequency END DESC ")
    fun getverbsbyForm(isASC: Int): List<QuranVerbsEntity?>?

    @Query(value = "SELECT * FROM quranverbs order by CASE when :isASC=1 THEN form END ASC,CASE when :isASC=0 THEN frequency END DESC ")
    fun getverbsbyFrequency(isASC: Int): List<QuranVerbsEntity?>?
}