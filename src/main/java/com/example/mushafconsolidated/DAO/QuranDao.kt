package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.QuranEntity


//.QuranEntity


@Dao
interface QuranDao {
    @Query("SELECT * FROM qurans where surah=:surahid")
    fun getQuranVersesBySurah(surahid: Int): List<QuranEntity?>?

    @Query("SELECT * FROM qurans where surah=:surahid")
    fun getTranslation(surahid: Int): List<QuranEntity?>?

    @get:Query("SELECT * FROM qurans order by surah,ayah")
    val allQuran: List<QuranEntity?>?

    @Query("SELECT * FROM qurans where juz=:part")
    fun getQuranbyJuz(part: Int): List<QuranEntity?>?

    @Query("SELECT * FROM qurans where surah=:surahid and ayah=:ayahid")
    fun getsurahayahVerses(surahid: Int, ayahid: Int): List<QuranEntity?>?

    @Query("select * from qurans where docid  between :start and :end")
    fun getVersesByPart(start: Int, end: Int): List<QuranEntity?>?

    @Query("select * from qurans where  surah =:sura and page = :pageno order by ayah")
    fun getAyahsByPage(sura: Int, pageno: Int): List<QuranEntity?>?

    @Query("select * from qurans where  surah =:juz and page = :pageno order by ayah")
    fun getAyahsByPagejuz(juz: Int, pageno: Int): List<QuranEntity?>?

    @Query("select * from qurans where surah = :surahid and ayah>=:from and ayah<=:toid order by ayah  ")
    fun getQuranbySurahAyahrange(surahid: Int, from: Int, toid: Int): List<QuranEntity?>?

    //getQuranbySurahAyahrange
    //select * from qurans where ayah>=50 and ayah<=78 and surah=9
    @Query("select page from qurans where surah = :pos order by ayah limit 1 ")
    fun getSuraStartpage(pos: Int): Int
}