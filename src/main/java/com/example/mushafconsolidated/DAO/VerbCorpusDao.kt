package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.VerbCorpus

//.VerbCorpus


@Dao
interface VerbCorpusDao {
    //chapterid, ayanumber, wordno
    @Query("SELECT * FROM verbcorpus where chapterno=:surahid and verseno=:ayaid and wordno=:wordid")
    fun getVerbRootsurahayahwordid(surahid: Int, ayaid: Int, wordid: Int): List<VerbCorpus>

    @Query("SELECT * FROM verbcorpus where chapterno=:surahid and verseno=:ayaid ")
    fun getQuranRootaAyah(surahid: Int, ayaid: Int): List<VerbCorpus?>?

    @Query("SELECT * FROM verbcorpus where root_a=:root")
    fun getQuranRootbyString(root: String?): List<VerbCorpus?>?
    @Query("SELECT count(root_a) as count, * FROM verbcorpus where root_a=:verbroot   group by root_a,form order by chapterno,verseno")
    fun allverbcount(verbroot :String): List<VerbCorpus>
}