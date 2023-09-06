package sj.hisnul

import android.content.Context
import com.example.mushafconsolidated.QuranAppDatabase
/*
  val duaitem=  DuaItem.getAllDuaItems()
    val duacategory=Duacategory.getCategory()
   val duanames=              Duanames.getAllduanames() */
object DuaGrpah {
    lateinit var db:QuranAppDatabase

        private set

    val repository by lazy {

         Repository(


             DuaItem=                  db.duaItemdao(),
             Duacategory=       db.duaCategorydao(),
             Duanames=    db.duaNamesdao()



             )

    }

    fun provide (context : Context){
        db= QuranAppDatabase.getInstance(context)!!
    }

}