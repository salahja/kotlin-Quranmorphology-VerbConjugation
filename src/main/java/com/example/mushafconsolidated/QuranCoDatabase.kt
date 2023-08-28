package com.example.mushafconsolidated

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mushafconsolidated.settingsimport.Constants
import java.io.File

abstract class QuranCoDatabase constructor() : RoomDatabase() {
    companion object {
        val mainDatabase = File("${Constants.FILEPATH}/${Constants.DATABASENAME}")

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: QuranAppDatabase? = null

        /** Note: When you modify the database schema,
        you'll need to update the version number and define a migration strategy.*/

        fun getDatabase(ctx: Context): QuranAppDatabase {
            return when (val temp = INSTANCE) {
                null -> synchronized(this) {
                    Room.databaseBuilder(
                        ctx.applicationContext, QuranAppDatabase::class.java,
                        "qurangrammar.db"
                    )
                        .createFromFile(mainDatabase)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }

                else -> temp
            }
        }
    }
}