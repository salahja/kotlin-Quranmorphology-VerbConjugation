package sj.hisnul.newepository

import android.content.Context
import com.example.mushafconsolidated.QuranAppDatabase

object Graph {

    lateinit var db:QuranAppDatabase
        private set

    val repository by lazy {


        NewRepository(
           hduaItemDao=db.hDuaItemDao(),
        hduaCategoryDao=db.gethDuaCategoryDao(),
            hduaNamesDao=db.gethDuaNamesDao()

        )
    }

    fun provide(context: Context){
        db= QuranAppDatabase.getInstance(context)!!
    }

}