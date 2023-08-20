package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import database.Dao.BuckwaterDao
import database.Dao.QuranVerbsDao
import database.Dao.QuranicVerbsDao
import database.Dao.kovDao
import database.Dao.mazeedDao
import database.Dao.mujarradDao
import database.Dao.verbcorpusDao
import database.entity.BuckwaterEntitiy
import database.entity.Mazeed
import database.entity.MujarradVerbs
import database.entity.QuranVerbsEntity
import database.entity.QuranicVerbsEntity
import database.entity.kov
import database.entity.verbcorpus


//@Database(entities= {VerseEntit.class,ErabEntity.class,ChaptersAnaEntity.class},version= 1)
@Database(
    entities = [Mazeed::class, MujarradVerbs::class, kov::class, verbcorpus::class, BuckwaterEntitiy::class, QuranVerbsEntity::class, QuranicVerbsEntity::class],
    version = 2
)
abstract class VerbDatabase : RoomDatabase() {
    abstract fun BuckwaterDao(): BuckwaterDao?
    abstract fun QuranVerbsDao(): QuranVerbsDao?
    abstract fun QuranicVerbsDao(): QuranicVerbsDao?
    abstract fun verbcorpusDao(): verbcorpusDao?
    abstract fun kovDao(): kovDao?
    abstract fun mujarradDao(): mujarradDao?
    abstract fun mazeedDao(): mazeedDao?

    companion object {
        var verbDatabaseInstance: VerbDatabase? = null
        private val initialCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                //  new InitialAsyncTask(quranAppDatabaseInstance).execute();
            }
        }

        @Synchronized
        fun getInstance(context: Context?): VerbDatabase? {
            if (null == verbDatabaseInstance) {
                verbDatabaseInstance = databaseBuilder(
                    context!!,
                    VerbDatabase::class.java, "v3-conjugator.db"
                )
                    .createFromAsset("databases/v3-conjugator.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(initialCallBack)
                    .allowMainThreadQueries()
                    .build()
            }
            return verbDatabaseInstance
        }

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `mazeeddictionary` (`root` TEXT NOT NULL, `form` TEXT NOT NULL, `verbtype` TEXT NOT NULL, `id` INTEGER NOT NULL, `babname` TEXT NOT NULL, PRIMARY KEY(`id`))")
            }
        }
    }
}
