package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import com.example.mushafconsolidated.Entities.NounCorpus


@Dao
interface NounCorpusDao {
    @RewriteQueriesToDropUnusedColumns //chapterid, ayanumber, wordno
    @Query("SELECT * FROM nouncorpus where surah=:surahid and ayah=:ayaid and wordno=:wordid order by surah,ayah,wordno")
    fun getQuranNouns(surahid: Int, ayaid: Int, wordid: Int): List<NounCorpus?>?

    @Query("SELECT * FROM nouncorpus where surah=:surahid and ayah=:ayaid  order by surah,ayah,wordno")
    fun getQuranNounAyah(surahid: Int, ayaid: Int): List<NounCorpus?>?

    //   @Query("SELECT count(root_a), lemma_a,form,araword,tag,propone,proptwo FROM nouncorpus where root_a=:verbroot group by lemma_a,root_a,tag,propone,proptwo")
    @get:Query("SELECT * FROM nouncorpus  ")
    val allnouns: List<NounCorpus?>?
    //   List<NounCorpus> getNounBreakUp(String verbroot);
    //  select  count(root_a),root_a,lemma_a ,form ,araword,tag from nouncorpus where
    // root_a="كلم" group by lemma_a,root_a,tag,propone,proptwo
}