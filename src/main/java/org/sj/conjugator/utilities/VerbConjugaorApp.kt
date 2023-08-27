package org.sj.conjugator.utilities

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import database.Dao.BuckwaterDao
import database.Dao.QuranVerbsDao
import database.Dao.QuranicVerbsDao
import database.Dao.kovDao
import database.Dao.mazeedDao
import database.Dao.mujarradDao
import database.Dao.verbcorpusDao
import database.entity.BuckwaterEntitiy
import database.entity.MazeedEntity
import database.entity.MujarradVerbs
import database.entity.QuranVerbsEntity
import database.entity.QuranicVerbsEntity
import database.entity.kov
import database.entity.verbcorpus


//@Database(entities= {VerseEntit.class,ErabEntity.class,ChaptersAnaEntity.class},version= 1)
@Database(
    entities = [MazeedEntity::class, MujarradVerbs::class, kov::class, verbcorpus::class, BuckwaterEntitiy::class, QuranVerbsEntity::class, QuranicVerbsEntity::class],
    version = 2
)
abstract class VerbDatabase : RoomDatabase() {

    open val databaseCreated: LiveData<Any> = TODO("initialize me")

    abstract fun BuckwaterDao(): BuckwaterDao?
    abstract fun QuranVerbsDao(): QuranVerbsDao?
    abstract fun QuranicVerbsDao(): QuranicVerbsDao?
    abstract fun verbcorpusDao(): verbcorpusDao?
    abstract fun kovDao(): kovDao?
    abstract fun mujarradDao(): mujarradDao?
    abstract fun mazeedDao(): mazeedDao?






   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var verbDatabaseInstance: VerbDatabase? = null

        fun getDatabase(context: Context): VerbDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return verbDatabaseInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, VerbDatabase::class.java, "v3-conjugator.db")
                    .createFromAsset("databases/v3-conjugator.db")
                    .fallbackToDestructiveMigration()
                   // .addCallback(initialCallBack)
                    .allowMainThreadQueries()
                    .build()
                verbDatabaseInstance = instance
                // return instance
                instance
            }
        }
   }
}

