package com.example.mushafconsolidated


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mushafconsolidated.DAO.AnaQuranChapterDao
import com.example.mushafconsolidated.DAO.BadalErabNotesDao
import com.example.mushafconsolidated.DAO.BookMarkDao
import com.example.mushafconsolidated.DAO.CorpusExpandedDao
import com.example.mushafconsolidated.DAO.HaliyaDao
import com.example.mushafconsolidated.DAO.HansDao
import com.example.mushafconsolidated.DAO.LaneDao
import com.example.mushafconsolidated.DAO.LaneRootDao
import com.example.mushafconsolidated.DAO.LughatDao
import com.example.mushafconsolidated.DAO.MafoolBihiDao
import com.example.mushafconsolidated.DAO.MafoolMutlaqEntDao
import com.example.mushafconsolidated.DAO.NewKanaDao
import com.example.mushafconsolidated.DAO.NewMudhafDao
import com.example.mushafconsolidated.DAO.NewNasbDao
import com.example.mushafconsolidated.DAO.NewShartDAO
import com.example.mushafconsolidated.DAO.NounCorpusDao
import com.example.mushafconsolidated.DAO.QariDao
import com.example.mushafconsolidated.DAO.QuranDao
import com.example.mushafconsolidated.DAO.QuranExplorerDao
import com.example.mushafconsolidated.DAO.RawDao
import com.example.mushafconsolidated.DAO.SifaDao
import com.example.mushafconsolidated.DAO.VerbCorpusDao
import com.example.mushafconsolidated.DAO.grammarRulesDao
import com.example.mushafconsolidated.DAO.liajlihiDao
import com.example.mushafconsolidated.DAO.qurandictionaryDao
import com.example.mushafconsolidated.DAO.surahsummaryDao
import com.example.mushafconsolidated.DAO.tameezDao
import com.example.mushafconsolidated.DAO.wbwDao
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.CorpusEntity
import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.NamesDetailsDao
import com.example.mushafconsolidated.Entities.NewKanaEntity
import com.example.mushafconsolidated.Entities.NewMudhafEntity
import com.example.mushafconsolidated.Entities.NewNasbEntity
import com.example.mushafconsolidated.Entities.NewShartEntity
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.Qari
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.SifaEntity
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.Entities.hanslexicon
import com.example.mushafconsolidated.Entities.lanelexicon
import com.example.mushafconsolidated.Entities.lanerootdictionary
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.Entities.qurandictionary
import com.example.mushafconsolidated.Entities.quranexplorer
import com.example.mushafconsolidated.Entities.surahsummary
import com.example.mushafconsolidated.Entities.wbwentity
import com.example.mushafconsolidated.settingsimport.Constants.Companion.DATABASENAME
import database.Dao.NamesDao
import database.entity.AllahNames
import sj.hisnul.Dao.hDuaCategoryDao
import sj.hisnul.Dao.hDuaItemDao
import sj.hisnul.Dao.hDuaNamesDao
import sj.hisnul.entity.AllahNamesDetails
import sj.hisnul.entity.hcategoryEnt
import sj.hisnul.entity.hduadetailsEnt
import sj.hisnul.entity.hduanamesEnt
import java.io.File

//@Database(entities= {VerseEntit.class,ErabEntity.class,ChaptersAnaEntity.class},version= 1)
//orig     entities = [lanerootdictionary::class, Qari::class, Cities::class, Countries::class, hcategory::class, hduadetails::class, hduanames::class, surahsummary::class, quranexplorer::class, AllahNamesDetails::class, AllahNames::class, DuaGroup::class, DuaDetails::class, MafoolMutlaqEnt::class, BadalErabNotesEnt::class, HalEnt::class, MafoolBihi::class, LiajlihiEnt::class, TameezEnt::class, GrammarRules::class, hanslexicon::class, qurandictionary::class, lanelexicon::class, lughat::class, NewNasbEntity::class, NewShartEntity::class, NewKanaEntity::class, NewMudhafEntity::class, SifaEntity::class, wbwentity::class, NounCorpus::class, VerbCorpus::class, QuranEntity::class, CorpusEntity::class, BookMarks::class, ChaptersAnaEntity::class],
@Database(
    entities = [hduadetailsEnt::class, hduanamesEnt::class, hcategoryEnt::class,AllahNamesDetails::class,lanerootdictionary::class, Qari::class, surahsummary::class, quranexplorer::class, AllahNames::class, MafoolMutlaqEnt::class, BadalErabNotesEnt::class, HalEnt::class, MafoolBihi::class, LiajlihiEnt::class, TameezEnt::class, GrammarRules::class, hanslexicon::class, qurandictionary::class, lanelexicon::class, lughat::class, NewNasbEntity::class, NewShartEntity::class, NewKanaEntity::class, NewMudhafEntity::class, SifaEntity::class, wbwentity::class, NounCorpus::class, VerbCorpus::class, QuranEntity::class, CorpusEntity::class, BookMarks::class, ChaptersAnaEntity::class],
    version = 1
)
abstract class QuranAppDatabase constructor() : RoomDatabase() {
    abstract fun AnaQuranChapterDao(): AnaQuranChapterDao

    // public abstract WordbywordPojoDao WordbywordPojoDao();
    abstract fun BookMarkDao(): BookMarkDao
    abstract fun RawDao(): RawDao
    abstract fun getCorpusExpandDao(): CorpusExpandedDao
    abstract fun QuranDao(): QuranDao
     abstract fun VerbCorpusDao(): VerbCorpusDao
    abstract fun NounCorpusDao(): NounCorpusDao
    abstract fun wbwDao(): wbwDao?
    abstract fun SifaDao(): SifaDao?

    //  public abstract ShartDAO ShartDAO();
    abstract fun NewShartDAO(): NewShartDAO?

    //  public abstract KanaDao KanaDao();
    //public abstract NasbDao NasbDao();
    abstract fun NewNasbDao(): NewNasbDao?
    abstract fun NewMudhafDao(): NewMudhafDao?
    abstract fun NewKanaDao(): NewKanaDao?
    abstract fun LughatDao(): LughatDao?
    abstract fun LaneDao(): LaneDao?
    abstract fun LaneRootDao(): LaneRootDao
    abstract fun HansDao(): HansDao
    abstract fun qurandictionaryDao(): qurandictionaryDao
    abstract fun grammarRulesDao(): grammarRulesDao?
    abstract fun tameezDao(): tameezDao
    abstract fun liajlihiDao(): liajlihiDao
    abstract fun MafoolBihiDao(): MafoolBihiDao
    abstract fun HaliyaDao(): HaliyaDao
    abstract fun BadalErabNotesDao(): BadalErabNotesDao
    abstract fun MafoolMutlaqEntDao(): MafoolMutlaqEntDao

    abstract fun NamesDetailsDao(): NamesDetailsDao?
    abstract fun QuranExplorerDao(): QuranExplorerDao?
    abstract fun surahsummaryDao(): surahsummaryDao
    abstract fun QariDao(): QariDao


   abstract fun NamesDao(): NamesDao?
    abstract fun hDuaItemDao(): hDuaItemDao
    abstract fun gethDuaNamesDao(): hDuaNamesDao
    abstract fun gethDuaCategoryDao(): hDuaCategoryDao



/*
    abstract fun CountryDao(): CountryDao?
    abstract fun CitiesDAO(): CitiesDAO?
*/


    companion object {
        //  public static  <QuranAppDatabase> quranAppDatabaseInstance;
        private val initialCallBack: Callback = object : Callback() {
            public override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                //  new InitialAsyncTask(quranAppDatabaseInstance).execute();
            }
        }



        var quranAppDatabaseInstance: QuranAppDatabase? = null
        var quranAppDatabaseInstanceasset: QuranAppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): QuranAppDatabase? {
            val FILEPATH =
                context.getExternalFilesDir(null)!!.absolutePath + "/" + context.resources.getString(
                    R.string.app_folder_path
                )
            if (null == quranAppDatabaseInstance) {
                val mainDatabase = File("$FILEPATH/$DATABASENAME")
   /*     quranAppDatabaseInstanceasset = Room.databaseBuilder(
                    context,
                    QuranAppDatabase::class.java, "qurangrammar.db"
                )
                    .createFromAsset("databases/qurangrammar.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(initialCallBack)
                    .allowMainThreadQueries()
                    .build()
*/



                quranAppDatabaseInstance = Room.databaseBuilder(
                    context,
                    QuranAppDatabase::class.java, "qurangrammar.db"
                )
                    .createFromFile(mainDatabase) //          .fallbackToDestructiveMigration()
                    .addCallback(initialCallBack)
                    .allowMainThreadQueries()
                    .build()

            }
             return quranAppDatabaseInstance
        //    return quranAppDatabaseInstanceasset;
        }
    }
}