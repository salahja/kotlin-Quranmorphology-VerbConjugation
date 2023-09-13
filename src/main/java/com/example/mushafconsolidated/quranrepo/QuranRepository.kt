package com.example.mushafconsolidated.quranrepo






import androidx.lifecycle.LiveData
import com.example.mushafconsolidated.DAO.AnaQuranChapterDao
import com.example.mushafconsolidated.DAO.BadalErabNotesDao
import com.example.mushafconsolidated.DAO.BookMarkDao
import com.example.mushafconsolidated.DAO.HaliyaDao
import com.example.mushafconsolidated.DAO.HansDao
import com.example.mushafconsolidated.DAO.LaneRootDao
import com.example.mushafconsolidated.DAO.MafoolBihiDao
import com.example.mushafconsolidated.DAO.MafoolMutlaqEntDao
import com.example.mushafconsolidated.DAO.NounCorpusDao
import com.example.mushafconsolidated.DAO.QuranDao
import com.example.mushafconsolidated.DAO.VerbCorpusDao
import com.example.mushafconsolidated.DAO.liajlihiDao
import com.example.mushafconsolidated.DAO.surahsummaryDao
import com.example.mushafconsolidated.DAO.tameezDao
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.Entities.hanslexicon
import com.example.mushafconsolidated.Entities.lanerootdictionary
import com.example.mushafconsolidated.Entities.surahsummary
import com.example.mushafconsolidated.model.QuranCorpusWbw


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class QuranRepository(
    /* val buckwaterDao: BuckwaterDao,
     val quranVerbsDao: QuranVerbsDao,

     val verbcorpusDao: verbcorpusDao,
     val kovDao: kovDao,
    ,*/



    val qurandao: QuranDao,
    val ssummary: surahsummaryDao,
    val chaptersdao: AnaQuranChapterDao,
    val mafoolb: MafoolBihiDao,


    var jumlahaliya: HaliyaDao,
    val tammezent: tameezDao,
    val mutlaqent: MafoolMutlaqEntDao,
    val liajlihient: liajlihiDao,
    val badalErabNotesEnt: BadalErabNotesDao,
    val bookm:BookMarkDao,
    val hansdao: HansDao,
    val lanesdao:LaneRootDao,

    val ajlihiworddao:  liajlihiDao,
    val mutlaqworddao: MafoolMutlaqEntDao,
    val tameezword: tameezDao,

    val nouncorpusdao: NounCorpusDao,
    val mafoolbihi:MafoolBihiDao,
    val verbcorpusdao:VerbCorpusDao,
    ) {


   fun getVerbRootBySurahAyahWord(surah :Int,ayah : Int,wordno :Int): List<VerbCorpus> = verbcorpusdao.getVerbRootsurahayahwordid(surah,ayah,wordno)

    fun getQuranCorpusWbw(surah :Int,ayah : Int,wordno :Int): List<QuranCorpusWbw> = qurandao.getQuranCorpusWbw(surah,ayah,wordno)
    fun getNounsbysurahayahword(surah :Int,ayah : Int,wordno :Int): List<NounCorpus> = nouncorpusdao.getQuranNounsBysurahayahword(surah,ayah,wordno)
    fun getNounsbysurahayah(surah :Int,ayah : Int): List<NounCorpus> = nouncorpusdao.getQuranNounAyah(surah,ayah)
    fun getMafoolbihi(surah :Int,ayah : Int,wordno :Int): List<MafoolBihi> = mafoolbihi.getMafoolbihi(surah,ayah,wordno)
    fun gethalsurahayah(cid :Int,aid : Int): List<HalEnt> = jumlahaliya.getHaliya(cid,aid)
    fun getAjlihiword(surah :Int,ayah : Int,wordno :Int): List<LiajlihiEnt> = ajlihiworddao.getMafoolLiajlihi(surah,ayah,wordno)

    fun getMutlaqWOrd(surah :Int,ayah : Int,wordno :Int): List<MafoolMutlaqEnt> = mutlaqworddao.getMafoolbihiword(surah,ayah,wordno)
    fun getTameezword(surah :Int,ayah : Int,wordno :Int): List<TameezEnt> = tameezword.getTameezWord(surah,ayah,wordno)
    fun getNouncorpus(surah :Int,ayah : Int,wordno :Int): List<NounCorpus> = nouncorpusdao.getQuranNounsBysurahayahword(surah,ayah,wordno)
    fun allquran(): LiveData<List<QuranEntity>> = qurandao.allQuranl()
    val chapters:LiveData<List<ChaptersAnaEntity>> = chaptersdao.chaptersl()
    val bookmarlist:LiveData<List<BookMarks>> = bookm.getBookMarksLive()
    val bookmarckcollection:LiveData<List<BookMarks>> = bookm.getCollectionbygroupsLive()
     val chaptersmutable: List<ChaptersAnaEntity?>? = chaptersdao.chapterslist()
    fun getsurahbychap(cid: Int):LiveData<List<QuranEntity>> = qurandao.getQuranVersesBySurahl(cid)
    fun getHansRoot(cid: String): List<hanslexicon> = hansdao.getHansDefinitionl(cid)
    fun getLanesRoot(cid: String): List<lanerootdictionary> = lanesdao.getLanesRootDefinition(cid)
    fun getsurahbyayah(cid: Int,ayid : Int):LiveData<List<QuranEntity>> = qurandao.getsurahayahVersesl(cid,ayid)
    fun getsurahbyayahlist(cid: Int,ayid : Int):List<QuranEntity> = qurandao.getsurahayahVerseslist(cid,ayid)
    fun getSurahSummary(cid :Int):LiveData<List<surahsummary>> = ssummary.getSurahSummary(cid)
    fun getMafoolbihiSurah(cid :Int): List<MafoolBihi> = mafoolb.getBySurah(cid)
    fun gettameezsurah(cid :Int): List<TameezEnt> = tammezent.getTameezSurah(cid)
    fun gethalsurah(cid :Int): List<HalEnt> = jumlahaliya.getHaliyaSurah(cid)
    fun getmutlaqsura(cid :Int): List<MafoolMutlaqEnt> = mutlaqent.getMutlaqsurah(cid)
    fun getliajlihsura(cid :Int): List<LiajlihiEnt> = liajlihient.getMafoolLiajlihisurah(cid)

    fun getbadalnotes(cid :Int): List<BadalErabNotesEnt> = badalErabNotesEnt.getBadalNotesSurah(cid)

    suspend fun insertlive(entity : BookMarks) {
        bookm.insertBookmark(entity)


    }
    suspend fun delete(entity : BookMarks) {
        bookm.deletebookmarkl(entity)


    }
    suspend fun deletecollection(entity : String) {
        bookm.deleteCollectionl(entity)


    }






/*
    fun getMujarradroot(root : String): LiveData<List<MujarradVerbs>>
            =mujarradao.getverbTrilive(root)

    fun getMujarradWeakness(kov : String): LiveData<List<MujarradVerbs>>
            =mujarradao.getMujarradWeaknesslive(kov)
    suspend fun insertlive(entity : MujarradVerbs) {
        mujarradao.insertlive(entity)
    }



    fun getMazeedroot(root : String): LiveData<List<MazeedEntity>>
    =mazeeddao.getMazeedRootlive(root)

    fun getMazeedWeakness(kov : String): LiveData<List<MazeedEntity>>
            =mazeeddao.getMazeedWeaknesslive(kov)
    suspend fun insertlive(entity : MazeedEntity) {
        mazeeddao.insertlive(entity)
    }

*/



}