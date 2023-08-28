package com.example.mushafconsolidated.Utils






import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.mushafconsolidated.DAO.BookMarkDao
import com.example.mushafconsolidated.DAO.BookMarksPojo
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.CorpusExpandWbwPOJO
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance
import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.KanaPOJO
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.MudhafPOJO
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO
import com.example.mushafconsolidated.Entities.NewKanaEntity
import com.example.mushafconsolidated.Entities.NewMudhafEntity
import com.example.mushafconsolidated.Entities.NewNasbEntity
import com.example.mushafconsolidated.Entities.NewShartEntity
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.Qari
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.RootVerbDetails
import com.example.mushafconsolidated.Entities.RootWordDetails
import com.example.mushafconsolidated.Entities.ShartPOJO
import com.example.mushafconsolidated.Entities.SifaEntity
import com.example.mushafconsolidated.Entities.SifaPOJO
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.Entities.TameezPOJO
import com.example.mushafconsolidated.Entities.TameezPojoList
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Entities.hanslexicon
import com.example.mushafconsolidated.Entities.lanelexicon
import com.example.mushafconsolidated.Entities.lanerootdictionary
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.Entities.qurandictionary
import com.example.mushafconsolidated.Entities.quranexplorer
import com.example.mushafconsolidated.Entities.surahsummary
import com.example.mushafconsolidated.Entities.wbwentity
import com.example.mushafconsolidated.QuranAppDatabase
import com.example.mushafconsolidated.model.Juz
import database.entity.AllahNames
import sj.hisnul.entity.AllahNamesDetails
import sj.hisnul.entity.hcategory
import sj.hisnul.entity.hduadetails
import sj.hisnul.entity.hduanames


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class Utils {
    var thiscontext: Context? = null

    constructor(context: Context?) {
      /*  Utils.Companion.database = context?.let { QuranAppDatabase.Companion.getDatabase(context) }!!
        database= QuranAppDatabase.getDatabase(context)
        thiscontext = context*/
        Utils.Companion.database = context?.let { QuranAppDatabase.Companion.getInstance(it) }!!
        thiscontext = context
    }

    constructor() {}

    private class deleteWordAsyncTask internal constructor(private val mAsyncTaskDao: BookMarkDao) :
        AsyncTask<BookMarks?, Void?, Void?>() {


        override fun doInBackground(vararg params: BookMarks?): Void? {
            mAsyncTaskDao.deletebookmark(params.get(0))
            return null
        }
    }

    fun getSurahSummary(id: Int): List<surahsummary?>? {
        return Utils.Companion.database?.surahsummaryDao()?.getSurahSummary(id)
    }

    fun getNamesDetails(id: Int): List<AllahNamesDetails?>? {
        return Utils.Companion.database?.NamesDetailsDao()?.ALLAH_NAMES_DETAILS_DETAILS(id)
    }


    fun getMudhafSurahNew(id: Int): List<NewMudhafEntity?>? {
        return Utils.Companion.database?.NewMudhafDao()?.getMudhafSurah(id)
    }

    fun getMudhafSurahAyahNew(id: Int, aid: Int): List<NewMudhafEntity?>? {
        return Utils.Companion.database?.NewMudhafDao()?.getMudhafSurahAyah(id, aid)
    }

    fun getAllAnaChapters(): List<ChaptersAnaEntity?>? {

        return database?.AnaQuranChapterDao()?.chapters
    }

    fun getSingleChapter(id: Int): List<ChaptersAnaEntity?>? {
        Log.d(Utils.Companion.TAG, "getSingleChapter: started")
        return Utils.Companion.database?.AnaQuranChapterDao()?.getSingleChapters(id)
    }

    val bookMarks: List<BookMarks?>?
        get() {
            Log.d(Utils.Companion.TAG, "getAllItems: started")
            return Utils.Companion.database?.BookMarkDao()?.getBookMarks()
        }

    fun getQuranRoot(id: Int, verseid: Int, wordid: Int): List<VerbCorpus?>? {
        Log.d(Utils.Companion.TAG, "getQuranRoot: started")
        return Utils.Companion.database?.VerbCorpusDao()?.getQuranRoot(id, verseid, wordid)
    }

    fun getQuranRootaAyah(id: Int, verseid: Int): List<VerbCorpus?>? {
        Log.d(Utils.Companion.TAG, "getQuranRoot: getQuranRootaAyah")
        return Utils.Companion.database?.VerbCorpusDao()?.getQuranRootaAyah(id, verseid)
    }

    fun getQuranNouns(id: Int, verseid: Int, wordid: Int): List<NounCorpus?>? {
        Log.d(Utils.Companion.TAG, "getQuranNouns: started")
        return Utils.Companion.database?.NounCorpusDao()?.getQuranNouns(id, verseid, wordid)
    }

    fun getQuranNounAyah(id: Int, verseid: Int): List<NounCorpus?>? {
        Log.d(Utils.Companion.TAG, "getQuranNouns: started")
        return Utils.Companion.database?.NounCorpusDao()?.getQuranNounAyah(id, verseid)
    }

    fun getwbwTranslatonbywordNew(id: Int, ayaid: Int, wordid: Int): List<wbwentity?>? {
        Log.d(Utils.Companion.TAG, "getwbwTranslatonbyword: started")
        return Utils.Companion.database?.wbwDao()?.getwbwTranslationbywordno(id, ayaid, wordid)
    }

    fun insertBookMark(entity: BookMarks?) {
        //    database?.BookMarkDao().deleteAllBookMakrs();
        AsyncTask.execute(Runnable({
            AsyncTask.execute(object : Runnable {
                public override fun run() {
                    // and deleting
                    Utils.Companion.database?.BookMarkDao()?.insertBookmark(entity)
                    //  runOnUiThread(new Runnable() {
                    //  public void run() {
                    //   itemTextView.setText("item deleted");
                    //    }
                    //  });
                }
            })
        }))
    }

    fun getCorpusWbwBySurahAyahWordid(
        tid: Int,
        aid: Int,
        wid: Int,
    ): List<NewCorpusExpandWbwPOJO?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.translation,\n" +
                    "       Qurans.ur_junagarhi,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.rootaraone,\n" +
                    "       CorpusExpand.rootaratwo,\n" +
                    "       CorpusExpand.rootarathree,\n" +
                    "       CorpusExpand.rootarafour,\n" +
                    "       CorpusExpand.rootarafive,\n" +
                    "       CorpusExpand.lemaraone,\n" +
                    "       CorpusExpand.lemaratwo,\n" +
                    "       CorpusExpand.lemarathree,\n" +
                    "       CorpusExpand.lemarafour,\n" +
                    "       CorpusExpand.lemarafive,\n" +
                    "            CorpusExpand.form_one,\n" +
                    "       CorpusExpand.form_two,\n" +
                    "       CorpusExpand.form_three,\n" +
                    "       CorpusExpand.form_four,\n" +
                    "       CorpusExpand.form_five,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       wbw.en,\n" +
                    "       wbw.bn,\n" +
                    "       wbw.[in],\n" +
                    "       wbw.ur,\n" +
                    "       qurans.qurantext\n" +
                    "  FROM corpusexpand,\n" +
                    "       qurans,\n" +
                    "       wbw\n" +
                    " WHERE CorpusExpand.surah == \""
                    + tid + "\""
                    + "AND CorpusExpand.ayah== \""
                    + aid + "\""
                    + "AND CorpusExpand.wordno==\""
                    + wid + "\""
                    + "AND \n" +
                    "       corpusexpand.surah = wbw.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno AND\n" +
                    "       corpusexpand.surah = qurans.surah AND \n" +
                    "       corpusexpand.ayah = qurans.ayah  \n" +
                    " ORDER BY corpusexpand.surah,\n" +
                    "          corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getNewCorpusWbw(query)
    }

    fun getCorpusWbwBySurahAyah(tid: Int, aid: Int): List<NewCorpusExpandWbwPOJO?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.translation,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.rootaraone,\n" +
                    "       CorpusExpand.rootaratwo,\n" +
                    "       CorpusExpand.rootarathree,\n" +
                    "       CorpusExpand.rootarafour,\n" +
                    "       CorpusExpand.rootarafive,\n" +
                    "       CorpusExpand.lemaraone,\n" +
                    "       CorpusExpand.lemaratwo,\n" +
                    "       CorpusExpand.lemarathree,\n" +
                    "       CorpusExpand.lemarafour,\n" +
                    "       CorpusExpand.lemarafive,\n" +
                    "            CorpusExpand.form_one,\n" +
                    "       CorpusExpand.form_two,\n" +
                    "       CorpusExpand.form_three,\n" +
                    "       CorpusExpand.form_four,\n" +
                    "       CorpusExpand.form_five,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       wbw.en,\n" +
                    "       wbw.bn,\n" +
                    "       wbw.[in],\n" +
                    "       wbw.ur,\n" +
                    "       qurans.qurantext\n" +
                    "  FROM corpusexpand,\n" +
                    "       qurans,\n" +
                    "       wbw\n" +
                    " WHERE CorpusExpand.surah == \""
                    + tid + "\""
                    + "AND CorpusExpand.ayah== \""
                    + aid + "\""
                    + "AND \n" +
                    "       corpusexpand.surah = wbw.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno AND\n" +
                    "       corpusexpand.surah = qurans.surah AND \n" +
                    "       corpusexpand.ayah = qurans.ayah  \n" +
                    " ORDER BY corpusexpand.surah,\n" +
                    "          corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getNewCorpusWbw(query)
    }

    fun getCorpusWbwBySurahForTameez(tid: Int): List<CorpusExpandWbwPOJO?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.translation,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       wbw.en,\n" +
                    "       wbw.bn,\n" +
                    "       wbw.[in],\n" +
                    "       wbw.ur,\n" +
                    "       qurans.qurantext\n" +
                    "  FROM corpusexpand,\n" +
                    "       qurans,\n" +
                    "       wbw,\n" +
                    "       tameez\n" +
                    " WHERE CorpusExpand.surah == \""
                    + tid + "\""
                    + "AND \n" +
                    "       corpusexpand.surah = wbw.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno AND\n" +
                    "       corpusexpand.surah = qurans.surah AND \n" +
                    "       corpusexpand.ayah = qurans.ayah AND \n" +
                    "       corpusexpand.surah = tameez.surah AND \n" +
                    "       corpusexpand.ayah = tameez.ayah  \n" +
                    " ORDER BY corpusexpand.surah,\n" +
                    "          corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getCorpusWbwfortameez(query)
    }

    fun getCorpusWbwBySurah(tid: Int): List<CorpusExpandWbwPOJO?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.translation,\n" +
                    "       Qurans.passage_no,\n" +
                    "       Qurans.ar_irab_two,\n" +
                    "       Qurans.tafsir_kathir,\n" +
                    "       Qurans.en_transliteration,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.ur_junagarhi,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       wbw.en,\n" +
                    "       wbw.bn,\n" +
                    "       wbw.[in],\n" +
                    "       wbw.ur,\n" +
                    "       qurans.qurantext\n" +
                    "  FROM corpusexpand,\n" +
                    "       qurans,\n" +
                    "       wbw\n" +
                    " WHERE CorpusExpand.surah == \""
                    + tid + "\""
                    + "AND \n" +
                    "       corpusexpand.surah = wbw.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno AND\n" +
                    "       corpusexpand.surah = qurans.surah AND \n" +
                    "       corpusexpand.ayah = qurans.ayah  \n" +
                    " ORDER BY corpusexpand.surah,\n" +
                    "          corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getCorpusWbw(query)
    }

    fun getCorpusWbwBySurahAyahtopic(tid: Int, aid: Int): List<CorpusExpandWbwPOJO>{
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.translation,\n" +
                    "       Qurans.passage_no,\n" +
                    "       Qurans.ar_irab_two,\n" +
                    "       Qurans.tafsir_kathir,\n" +
                    "       Qurans.en_transliteration,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.ur_junagarhi,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       wbw.en,\n" +
                    "       wbw.bn,\n" +
                    "       wbw.[in],\n" +
                    "       wbw.ur,\n" +
                    "       qurans.qurantext\n" +
                    "  FROM corpusexpand,\n" +
                    "       qurans,\n" +
                    "       wbw\n" +
                    " WHERE CorpusExpand.surah == \""
                    + tid + "\""
                    + "AND CorpusExpand.ayah== \""
                    + aid + "\""
                    + "AND \n" +
                    "       corpusexpand.surah = wbw.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno AND\n" +
                    "       corpusexpand.surah = qurans.surah AND \n" +
                    "       corpusexpand.ayah = qurans.ayah  \n" +
                    " ORDER BY corpusexpand.surah,\n" +
                    "          corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database.RawDao().getCorpusWbwSurahAyah(query)
    }

    fun getnounoccuranceHarfNasbZarf(tid: String): List<CorpusNounWbwOccurance?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "         Qurans.qurantext,\n" +
                    "       qurans.translation,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       nouncorpus.tag,\n" +
                    "       nouncorpus.propone ,\n" +
                    "       nouncorpus.proptwo,\n" +
                    "       nouncorpus.form,\n" +
                    "       nouncorpus.gendernumber,\n" +
                    "       nouncorpus.type,\n" +
                    "       nouncorpus.cases,\n" +
                    "       wbw.en\n" +
                    "      FROM corpusexpand,nouncorpus,\n" +
                    "       wbw,qurans\n" +
                    "    where   nouncorpus.tag = \""
                    + tid + "\""
                    + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno " +
                    "and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah  order by corpusexpand.surah,corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getnounoccurance(query)
    }

    fun getVerbOccuranceBreakVerses(tid: String): List<CorpusVerbWbwOccurance?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.qurantext,\n" +
                    "       Qurans.translation,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       verbcorpus.voice,\n" +
                    "       verbcorpus.form ,\n" +
                    "       verbcorpus.thulathibab,\n" +
                    "       verbcorpus.tense,\n" +
                    "       verbcorpus.gendernumber,\n" +
                    "       verbcorpus.mood_kananumbers,\n" +
                    "       verbcorpus.kana_mood,\n" +
                    "       wbw.en\n" +
                    "      FROM corpusexpand,verbcorpus,\n" +
                    "       wbw,qurans\n" +
                    " WHERE (corpusexpand.tagone = \"V\" OR \n" +
                    "        corpusexpand.tagtwo = \"V\" OR \n" +
                    "        corpusexpand.tagthree = \"V\" OR \n" +
                    "        Corpusexpand.tagfour = \"V\" OR \n" +
                    "        corpusexpand.tagfive = \"V\") AND \n" +
                    "       corpusexpand.lemaraone||corpusexpand.lemaratwo||corpusexpand.lemarathree||corpusexpand.lemarafour||corpusexpand.lemarafive=  \""
                    + tid + "\""
                    + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = verbcorpus.chapterno AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = verbcorpus.verseno AND \n" +
                    "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = verbcorpus.wordno and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getVerbOccuranceBreakVerses(query)
    }

    fun getNounOccuranceBreakVerses(tid: String): List<CorpusNounWbwOccurance?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.qurantext,\n" +
                    "       qurans.translation,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       nouncorpus.tag,\n" +
                    "       nouncorpus.propone ,\n" +
                    "       nouncorpus.proptwo,\n" +
                    "       nouncorpus.form,\n" +
                    "       nouncorpus.gendernumber,\n" +
                    "       nouncorpus.type,\n" +
                    "       nouncorpus.cases,\n" +
                    "       wbw.en\n" +
                    "      FROM corpusexpand,nouncorpus,\n" +
                    "       wbw,qurans\n" +
                    "    where  corpusexpand.lemaraone||corpusexpand.lemaratwo||corpusexpand.lemarathree||corpusexpand.lemarafour||corpusexpand.lemarafive=  \""
                    + tid + "\""
                    + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno" +
                    " and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getnounoccurance(query)
    }

    fun getRootDetails(tid: String): List<RootWordDetails?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.araone ||CorpusExpand. aratwo ||CorpusExpand. arathree || CorpusExpand.arafour ||CorpusExpand. arafive as arabic,\n" +
                    "CorpusExpand.lemaraone ||CorpusExpand. lemaratwo ||CorpusExpand. lemarathree || CorpusExpand.lemarafour ||CorpusExpand. lemarafive as lemma,\n" +
                    "CorpusExpand.araone,CorpusExpand.aratwo,CorpusExpand.arathree,CorpusExpand.arafour,CorpusExpand.arafive,\n" +
                    "CorpusExpand.tagone,CorpusExpand.tagtwo,CorpusExpand.tagthree,CorpusExpand.tagfour,CorpusExpand.tagfive,\n" +
                    "CorpusExpand.tagone||\"-\" ||CorpusExpand. tagtwo||\"-\" ||CorpusExpand. tagthree ||\"-\"|| CorpusExpand.tagfour ||CorpusExpand. tagfive as tag,\n" +
                    "       qurandictionary.surah,\n" +
                    "       qurandictionary.ayah,\n" +
                    "       qurandictionary.rootarabic,\n" +
                    "\t   wbw.en,\n" +
                    "\t   chaptersana.abjadname,chaptersana.namearabic,chaptersana.nameenglish \n" +
                    "\t  \n" +
                    " \n" +
                    "      FROM corpusexpand,qurandictionary,wbw,chaptersana\n" +
                    "\t  where  qurandictionary.surah = CorpusExpand.surah AND  qurandictionary.ayah = CorpusExpand.ayah  \n" +
                    "\tand qurandictionary.wordno = CorpusExpand.wordno  AND qurandictionary.surah=wbw.surah and qurandictionary.ayah=wbw.ayah\n" +
                    "and qurandictionary.wordno=wbw.wordno and qurandictionary.surah=chaptersana.chapterid and qurandictionary.rootarabic=  \""
                    + tid + "\"")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getrootdetails(query)
    }

    fun getRootVerbDetails(tid: String): List<RootVerbDetails?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.araone ||CorpusExpand. aratwo ||CorpusExpand. arathree || CorpusExpand.arafour ||CorpusExpand. arafive as arabic,\n" +
                    "CorpusExpand.lemaraone ||CorpusExpand. lemaratwo ||CorpusExpand. lemarathree || CorpusExpand.lemarafour ||CorpusExpand. lemarafive as lemma,\n" +
                    "CorpusExpand.araone,CorpusExpand.aratwo,CorpusExpand.arathree,CorpusExpand.arafour,CorpusExpand.arafive,\n" +
                    "CorpusExpand.tagone,CorpusExpand.tagtwo,CorpusExpand.tagthree,CorpusExpand.tagfour,CorpusExpand.tagfive,\n" +
                    "       qurandictionary.surah,\n" +
                    "       qurandictionary.ayah,\n" +
                    "       qurandictionary.rootarabic,qurandictionary.wordno,\n" +
                    "\t   wbw.en,\n" +
                    "\t   chaptersana.abjadname,chaptersana.namearabic,chaptersana.nameenglish,\n" +
                    "\t   verbcorpus.form,verbcorpus.thulathibab,verbcorpus.gendernumber,verbcorpus.tense,verbcorpus.voice,verbcorpus.mood_kananumbers,verbcorpus.lemma_a\n" +
                    "\t  \n" +
                    " \n" +
                    "      FROM corpusexpand,qurandictionary,wbw,chaptersana,verbcorpus\n" +
                    "\t  where (CorpusExpand.tagone=\"V\" OR CorpusExpand.tagtwo=\"V\" OR CorpusExpand.tagthree=\"V\" OR CorpusExpand.tagfour=\"V\" \n" +
                    "\t or CorpusExpand.tagfive==\"V\" )and qurandictionary.surah = CorpusExpand.surah AND  qurandictionary.ayah = CorpusExpand.ayah  \n" +
                    "\t \tand qurandictionary.wordno = verbcorpus.wordno  AND qurandictionary.surah=verbcorpus.chapterno and qurandictionary.ayah=verbcorpus.verseno\n" +
                    "\tand qurandictionary.wordno = CorpusExpand.wordno  AND qurandictionary.surah=wbw.surah and qurandictionary.ayah=wbw.ayah\n" +
                    "and qurandictionary.wordno=wbw.wordno and qurandictionary.surah=chaptersana.chapterid and qurandictionary.rootarabic=  \""
                    + tid + "\"")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getverbdetails(query)
    }

    fun getnounoccurancebysurahayah(tid: Int, vid: Int): List<CorpusNounWbwOccurance?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.qurantext,\n" +
                    "       qurans.translation,\n" +
                    "       qurans.en_jalalayn,\n" +
                    "       qurans.ur_jalalayn,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       nouncorpus.tag,\n" +
                    "       nouncorpus.propone ,\n" +
                    "       nouncorpus.proptwo,\n" +
                    "       nouncorpus.form,\n" +
                    "       nouncorpus.gendernumber,\n" +
                    "       nouncorpus.type,\n" +
                    "       nouncorpus.cases,\n" +
                    "       wbw.en\n" +
                    "      FROM corpusexpand,nouncorpus,\n" +
                    "       wbw,qurans\n" +
                    "    where   CorpusExpand.surah = \""
                    + tid + "\""
                    + "and Corpusexpand.ayah=\""
                    + vid + "\""
                    + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno " +
                    "and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getnounoccurancebysurahayah(query)
    }

    fun getHarfNasbIndexesnew(id: Int): List<NewNasbEntity?>? {
        return Utils.Companion.database?.NewNasbDao()?.getHarfNasbIndices(id)
    }

    fun getHarfNasbIndSurahAyahSnew(id: Int, aid: Int): List<NewNasbEntity?>? {
        return Utils.Companion.database?.NewNasbDao()?.getHarfNasbIndicesSurahAyah(id, aid)
    }

    //  List<Book> result = booksDao.getBooks(query);
    val mudhaf: List<MudhafPOJO?>?
        get() {
            val sqlverb: String =
                "select newmudhaf.surah,newmudhaf.ayah,newmudhaf.startindex,newmudhaf.endindex, newmudhaf.comment,qurans.qurantext,qurans.translation from newmudhaf,qurans where \n" +
                        "newmudhaf.surah=qurans.surah and newmudhaf.ayah=qurans.ayah"
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
            //  List<Book> result = booksDao.getBooks(query);
            return Utils.Companion.database?.RawDao()?.getMudhaf(query)
        }

    //  List<Book> result = booksDao.getBooks(query);
    val mousufSIfa: List<SifaPOJO?>?
        get() {
            val sqlverb: String =
                ("select sifa.surah,sifa.ayah,sifa.wordno,sifa.startindex,sifa.endindex,sifa.phrasetype,sifa.ismousufconnected,\n" +
                        "sifa.comment\n" +
                        ",qurans.qurantext,qurans.translation from sifa,qurans where sifa.surah=qurans.surah and \n" +
                        "sifa.ayah=qurans.ayah")
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
            //  List<Book> result = booksDao.getBooks(query);
            return Utils.Companion.database?.RawDao()?.getMousufSifa(query)
        }

    //     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
    val sharts: List<ShartPOJO?>?
        get() {
            val sqlverb: String =
                ("select shart.surah,shart.ayah,shart.indexstart,shart.indexend,shart.shartindexstart,shart.shartindexend,\n" +
                        "shart.jawabshartindexstart,shart.jawabshartindexend,shart.sharttype,shart.comment\n" +
                        ",qurans.qurantext,qurans.translation from shart,qurans where shart.surah=qurans.surah and \n" +
                        "shart.ayah=qurans.ayah  order by shart.surah,shart.ayah")
            //     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
            //  List<Book> result = booksDao.getBooks(query);
            return Utils.Companion.database?.RawDao()?.getShart(query)
        }

    //     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
    val tameez: List<TameezPojoList?>?
        get() {
            val sqlverb: String = ("select tameez.surah,tameez.ayah,tameez.wordno,tameez.word \n" +
                    "                 ,qurans.qurantext,qurans.translation from tameez,qurans where tameez.surah=qurans.surah and  \n" +
                    "               tameez.ayah=qurans.ayah  order by tameez.surah,tameez.ayah")
            //     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
            //  List<Book> result = booksDao.getBooks(query);
            return Utils.Companion.database?.RawDao()?.getTameez(query)
        }

    //     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
    val kanaPojo: ArrayList<KanaPOJO>
        get() {
            val sqlverb: String =
                ("select newkana.surah,newkana.ayah,newkana.indexstart,newkana.indexend,newkana.khabarstart,newkana.khabarend,\n" +
                        "newkana.ismkanastart,newkana.ismkanaend,newkana.mahdoof,newkana.comment\n" +
                        ",qurans.qurantext,qurans.translation from newkana,qurans where newkana.surah=qurans.surah and \n" +
                        "newkana.ayah=qurans.ayah    order by newkana.surah,newkana.ayah ")
            //     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
            //  List<Book> result = booksDao.getBooks(query);
            return Utils.Companion.database?.RawDao()?.getKana(query) as ArrayList<KanaPOJO>
        }

    fun getSifabySurah(id: Int): List<SifaEntity?>? {
        return Utils.Companion.database?.SifaDao()?.getSifaindexesBySurah(id)
    }

    fun getSifabySurahAyah(id: Int, aid: Int): List<SifaEntity?>? {
        return Utils.Companion.database?.SifaDao()?.getSifaindexesBySurahAyah(id, aid)
    }

    fun getShartSurahNew(id: Int): List<NewShartEntity?>? {
        return Utils.Companion.database?.NewShartDAO()?.getShartBySurah(id)
    }

    fun getRootDictionary(id: String): List<lughat?>? {
        return Utils.Companion.database?.LughatDao()?.getRootWordDictionary(id.trim({ it <= ' ' }))
    }

    fun getLanesDifinition(id: String): List<lanelexicon?>? {
        return Utils.Companion.database?.LaneDao()?.getLanesDefinition(id.trim({ it <= ' ' }))
    }

    fun getLanesRootDefinition(id: String): List<lanerootdictionary?>? {
        return Utils.Companion.database?.LaneRootDao()?.getLanesRootDefinition(id.trim({ it <= ' ' }))
    }

    fun getHansDifinition(id: String): List<hanslexicon?>? {
        return Utils.Companion.database?.HansDao()?.getHansDefinition(id.trim({ it <= ' ' }))
    }

    fun getArabicWord(id: String?): List<lughat?>? {
        return Utils.Companion.database?.LughatDao()?.getArabicWord(id)
    }

    val quranDictionary: List<qurandictionary?>?
        get() {
            return Utils.Companion.database?.qurandictionaryDao()?.dictionary
        }

    fun getQuranDictionarybyroot(root: String?): List<qurandictionary?>? {
        return Utils.Companion.database?.qurandictionaryDao()?.getDictionaryroot(root)
    }

    fun getTopicSearch(id: String?): List<quranexplorer?>? {
        return Utils.Companion.database?.QuranExplorerDao()?.getFilter(id)
    }

    val topicSearchAll: List<quranexplorer?>?
        get() {
            return Utils.Companion.database?.QuranExplorerDao()?.aLL
        }

    fun getKananew(id: Int): List<NewKanaEntity?>? {
        return Utils.Companion.database?.NewKanaDao()?.getkanabysurah(id)
    }

    fun getKanaSurahAyahnew(id: Int, aid: Int): List<NewKanaEntity?>? {
        return Utils.Companion.database?.NewKanaDao()?.getkanabysurahAyah(id, aid)
    }

    fun getShartSurahAyahNew(id: Int, ayah: Int): List<NewShartEntity?>? {
        return Utils.Companion.database?.NewShartDAO()?.getShartBySurahAyah(id, ayah)
    }

    fun getwbwQuranBySurahAyah(id: Int, aid: Int): List<wbwentity?>? {
        return Utils.Companion.database?.wbwDao()?.getwbwQuranBySurahAyah(id, aid)
    }

    fun getwbwQuranbTranslation(
        sid: Int,
        aid: Int,
        firstwordindex: Int,
        lastwordindex: Int,
    ): List<wbwentity?>? {
        return Utils.Companion.database?.wbwDao()?.getwbwQuranbTranslation(sid, aid, firstwordindex, lastwordindex)
    }

    fun getNounBreakup(tid: String): List<NounCorpusBreakup?>? {
        val sqlverb: String =
            ("SELECT count(root_a) as count,surah,ayah, lemma_a,form,araword,tag,propone,proptwo FROM nouncorpus where root_a =\""
                    + tid + "\""
                    + " group by lemma_a,root_a,tag,propone,proptwo order by surah,ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getNounBreakup(query)
    }

    fun getVerbBreakUp(tid: String): List<VerbCorpusBreakup?>? {
        val sqlverb: String =
            ("SELECT count(root_a) as count,root_a,lemma_a,form,thulathibab,tense,voice FROM verbcorpus where root_a =\""
                    + tid + "\""
                    + " group by root_a,form order by chapterno,verseno")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.getVerbBreakup(query)
    }

    val grammarRules: List<GrammarRules?>?
        get() {
            return Utils.Companion.database?.grammarRulesDao()?.grammarRules
        }

    fun getGrammarRulesByRules(harf: String?): List<GrammarRules?>? {
        return Utils.Companion.database?.grammarRulesDao()?.getGrammarRulesByHarf(harf)
    }

    fun getTameezWord(surah: Int, ayah: Int, wordno: Int): List<TameezEnt?>? {
        return Utils.Companion.database?.tameezDao()?.getTameezWord(surah, ayah, wordno)
    }

    val tameezall: ArrayList<TameezEnt>
        get() {
            return Utils.Companion.database?.tameezDao()?.getall() as ArrayList<TameezEnt>
        }

    fun getTameezsurah(surah: Int): List<TameezEnt?>? {
        return Utils.Companion.database?.tameezDao()?.getTameezSurah(surah)
    }

    fun gettameezverses(): List<TameezPOJO?>? {
        val sql: String =
            "SELECT surah,COUNT(ayah) as versescount FROM tameez group by surah order BY surah,ayah"
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sql)
        //  List<Book> result = booksDao.getBooks(query);
        return Utils.Companion.database?.RawDao()?.gettameezversescount(query)
    }


    fun getMafoolLiajlihi(surah: Int, ayah: Int, wordno: Int): List<LiajlihiEnt>? {
        return Utils.Companion.database?.liajlihiDao()?.getMafoolLiajlihi(surah, ayah, wordno)
    }

    fun getMafoolLiajlihisurah(surah: Int): List<LiajlihiEnt?>? {
        return Utils.Companion.database?.liajlihiDao()?.getMafoolLiajlihisurah(surah)
    }

    fun getMafoolbihiId(surah: Int, ayah: Int, wordno: Int): List<MafoolBihi?>? {
        return Utils.Companion.database?.MafoolBihiDao()?.getMafoolbihi(surah, ayah, wordno)
    }

    fun getMafoolbihiword(surah: Int, ayah: Int, wordno: Int): List<MafoolBihi>? {
        return Utils.Companion.database?.MafoolBihiDao()?.getMafoolbihi(surah, ayah, wordno)
    }

    fun updateMafoolword(wordn: Int, id: Int): Int? {
        val updatemafoolrslt: Int? =
            Utils.Companion.database?.MafoolBihiDao()?.updateMafoolWord(wordn, id)
        return updatemafoolrslt
    }

    val mafoolbihiquran: ArrayList<MafoolBihi>
        get() {
            return Utils.Companion.database?.MafoolBihiDao()?.mafoolbihiq as ArrayList<MafoolBihi>
        }

    fun getMafoolBySurah(surah: Int): List<MafoolBihi?>? {
        return Utils.Companion.database?.MafoolBihiDao()?.getBySurah(surah)
    }

    fun getHaliaErab(surah: Int, ayah: Int): List<HalEnt>? {
        return Utils.Companion.database?.HaliyaDao()?.getHaliya(surah, ayah)
    }

    fun getHaliaErabBysurah(surah: Int): List<HalEnt?>? {
        return Utils.Companion.database?.HaliyaDao()?.getHaliyaSurah(surah)
    }

    fun getBadalErabSA(surah: Int, ayah: Int): List<BadalErabNotesEnt?>? {
        return Utils.Companion.database?.BadalErabNotesDao()?.getBadalNotesSurahAyah(surah, ayah)
    }

    fun getBadalrabSurah(surah: Int): List<BadalErabNotesEnt?>? {
        return Utils.Companion.database?.BadalErabNotesDao()?.getBadalNotesSurah(surah)
    }
    fun getMutlaqsurah(surah: Int): List<MafoolMutlaqEnt?>? {
        return database?.MafoolMutlaqEntDao()?.getMutlaqsurah(surah)
    }

    fun getMafoolMutlaqword(
        surah: Int,
        ayah: Int,
        wordno: Int,
    ): List<MafoolMutlaqEnt>? {
        return database?.MafoolMutlaqEntDao()
            ?.getMafoolbihiword(surah, ayah, wordno)
    }

    fun getQuranbySurah(id: Int): List<QuranEntity?>? {
        return database.QuranDao()?.getQuranVersesBySurah(id)
    }

    fun getAyahsByPageQuran(surah: Int, pageno: Int): List<QuranEntity?>? {
        return database.QuranDao()?.getAyahsByPage(surah, pageno)
    }

    fun getsurahayahVerses(id: Int, aid: Int): List<QuranEntity?>? {
        return database.QuranDao()?.getsurahayahVerses(id, aid)
    }

    fun getQuranbySurahAyahrange(surahid: Int, from: Int, to: Int): List<QuranEntity?>? {
        return  database.QuranDao()!!.getQuranbySurahAyahrange(surahid, from, to)
    }

    /*
       public ArrayList<MafoolMutlaqEnt> getMafoolMutlaqword(int surah, int ayah, int wordno) {
        return (ArrayList<MafoolMutlaqEnt>) database.MafoolMutlaqEntDao().getMafoolbihiword(surah, ayah, wordno);

    }

     */

 /*
    fun getMafoolMutlaqword(surah: Int, ayah: Int, wordno: Int): List<MafoolMutlaqEnt?>? {
        return Utils.Companion.database?.MafoolMutlaqEntDao()?.getMafoolbihiword(surah, ayah, wordno)
    }

    fun getMutlaqsurah(surah: Int): List<MafoolMutlaqEnt?>? {
        return Utils.Companion.database?.MafoolMutlaqEntDao()?.getMutlaqsurah(surah)
    }*/

    fun getAllList(): List<hduanames?>? {
        return database.hDuaNamesDao()?.duanames
    }


    fun getdualistbychapter(cid: Int): List<hduanames?>? {
        return Utils.Companion.database?.hDuaNamesDao()?.getdualistbychapter(cid)
    }

    val hcategory: ArrayList<hcategory>
        get() {
            return Utils.Companion.database?.hDuaCategoryDao()?.getcatetory() as ArrayList<hcategory>
        }



    val names: ArrayList<AllahNames>
        get() {
            return Utils.Companion.database?.NamesDao()?.ALLAH_NAMES_LIST() as ArrayList<AllahNames>
        }

    fun getDuaCATNAMES(tid: String?): List<hduanames?>? {
        val verb: String = String.format(
            "select * from hduanames where (category = '%s'   or category like '%%,%s'   or category like '%s, %% 'or category like '%%,%s,%%'" +
                    "", tid, tid, tid, tid
        )
        val fs: String = verb + ")"
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(fs)
        return Utils.Companion.database?.RawDao()?.getDuaCATNAMES(query)
    }
    fun updateFav(fav: Int, id: Int): Int? {
        val updadateRoots: Int? = Utils.Companion.database?.hDuaNamesDao()?.updateFav(fav, id)
        return updadateRoots
    }

    fun getDunamesbyid(id: String?): List<hduanames?>? {
        return Utils.Companion.database?.hDuaNamesDao()?.getDuanamesid(id)
    }

    fun getDunamesbyCatId(id: String?): List<hduanames?>? {
        return Utils.Companion.database?.hDuaNamesDao()?.getDunamesbyCatId(id)
    }

    fun getDunamesbyCatIdnew(id: String?): List<hduanames?>? {
        return Utils.Companion.database?.hDuaNamesDao()?.getDunamesbyCatIdnew(id)
    }

    fun getDuanamesDetails(id: String?): List<hduanames?>? {
        return Utils.Companion.database?.hDuaNamesDao()?.getDuanamesByID(id)
    }

    fun getBookmarked(id: Int): List<hduanames?>? {
        return Utils.Companion.database?.hDuaNamesDao()?.getBookmarked(id)
    }

    fun getIsmarked(id: String?): List<hduanames?>? {
        return Utils.Companion.database?.hDuaNamesDao()?.isBookmarked(id)
    }

    fun gethDuadetailsitems(id: String?): List<hduadetails?>? {
        return Utils.Companion.database?.hDuaItemDao()?.getDitem(id)
    }

    //muslim mate



    //  List<Book> result = booksDao.getBooks(query);
    val collectionC: List<BookMarksPojo?>?
        get() {
            val sql: String =
                "select  count(*) as count, * from bookmark where header != \"pins\" group by header"
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sql)
            //  List<Book> result = booksDao.getBooks(query);
            return Utils.Companion.database?.RawDao()?.getCollectionCount(query)
        }

    //  List<Book> result = booksDao.getBooks(query);
    val juz: List<Juz>
        get() {
            val sql: String =
                "select  a.nameenglish,a.namearabic ,a.chapterid , b. juz, min(b.page) page,b.ayah,b.qurantext from chaptersana a, qurans b where a.chapterid=b.surah group by juz"
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sql)
            //  List<Book> result = booksDao.getBooks(query);
            return Utils.Companion.database?.RawDao()!!.getJuz(query)
        }

    val qaris: List<Qari>
        get() {
            return Utils.Companion.database?.QariDao()!!.qaris
        }

    companion object {
        private val TAG: String = "Utils"
        private lateinit var database: QuranAppDatabase


        fun getRootwordsbyLetter(tid: String): List<qurandictionary?>? {
            val sb = StringBuilder()

            val sqlverb: String =
                "SELECT distinct rootarabic FROM qurandictionary where rootarabic like \"%\\+tid "
            //   sb.append(sqlverb).append(" ").append("\%\")
            val ss: String =
                "SELECT distinct ROOTARABIC FROM QURANDICTIONARY where rootarabic like  \"%" + tid + "\""
            val sss: String =
                "SELECT distinct ROOTARABIC FROM QURANDICTIONARY where rootarabic like" + tid + "  \"%" + tid + "\""
            //   sb.append("SELECT distinct ROOTARABIC FROM QURANDICTIONARY where rootarabic like ").append("\"").append("%").append(tid).append("\"");
            sb.append("SELECT distinct ROOTARABIC FROM QURANDICTIONARY where rootarabic like ")
                .append("\"").append(tid).append("%").append("\"")
            val let: String = String.format(
                "select rootarabic from qurandictionary where (rootarabic like '%s'",
                tid
            )
            val verb: String =
                "SELECT distinct ROOTARABIC FROM QURANDICTIONARY where rootarabic like %" + tid
            val fs: String = let + ")"

            val qquery="SELECT distinct ROOTARABIC FROM QURANDICTIONARY "
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sb.toString())
            return Utils.Companion.database.RawDao().getRootsbyLetter(query)
        }

        val bookMarksNew: List<BookMarks?>?
            get() {
                return Utils.Companion.database?.BookMarkDao()?.getBookMarks()
            }

        fun getAllBookmarks(pins: String?): List<BookMarks?>? {
            return Utils.Companion.database?.BookMarkDao()?.getAllBookmarks(pins)
        }

        fun getsurahayahVerses(id: Int, aid: Int): List<QuranEntity?>? {
            return Utils.Companion.database?.QuranDao()?.getsurahayahVerses(id, aid)
        }

            fun getByfirstletter(id: String): List<qurandictionary> {
            return Utils.Companion.database?.qurandictionaryDao()!!.getByfirstletter(id)
        }




        fun getQuranbySurahAyahrange(surahid: Int, from: Int, to: Int): List<QuranEntity?>? {
            return Utils.Companion.database?.QuranDao()?.getQuranbySurahAyahrange(surahid, from, to)
        }

        //select * from qurans where ayah>=50 and ayah<=78 and surah=9
        val quran: List<QuranEntity>
            get() {
                return Utils.Companion.database?.QuranDao()?.allQuran as List<QuranEntity>
            }

        fun deleteBookMarks(bookMarks: BookMarks?) {
            object : AsyncTask<Void?, Void?, Void?>() {
                override fun doInBackground(vararg voids: Void?): Void? {
                    Utils.Companion.database?.BookMarkDao()?.deletebookmark(bookMarks)
                    return null
                }
            }.execute()
        }

        fun deleteBookmark(bookmark: BookMarks?) {
            val bookMarkDao: BookMarkDao? = Utils.Companion.database?.BookMarkDao()
            if (bookMarkDao != null) {
                deleteWordAsyncTask(bookMarkDao).execute(bookmark)
            }
        }

        fun deleteCollection(count: String?) {
            Utils.Companion.database?.BookMarkDao()?.deleteCollection(count)
        }

        fun getQuranbyJuz(juz: Int): List<QuranEntity?>? {
            return Utils.Companion.database?.QuranDao()?.getQuranbyJuz(juz)
        }

        val collectionCount: List<BookMarks?>?
            get() {
                return Utils.Companion.database?.BookMarkDao()?.collectionCount
            }

        fun getCollectionbygroup(header: String?): List<BookMarks?>? {
            return Utils.Companion.database?.BookMarkDao()?.getCollectionbygroup(header)
        }


        fun getAyahsByPageQuran(surah: Int, pageno: Int): List<QuranEntity?>? {
            return Utils.Companion.database?.QuranDao()?.getAyahsByPage(surah, pageno)
        }

        fun getAyahsByPagejuz(juz: Int, pageno: Int): List<QuranEntity?>? {
            return Utils.Companion.database?.QuranDao()?.getAyahsByPagejuz(juz, pageno)
        }
    }
}