package sj.hisnul

import sj.hisnul.Dao.DuaItemdao
import sj.hisnul.Dao.Duacategorydao
import sj.hisnul.Dao.Duanamesdao




class Repository(
    private val DuaItem: DuaItemdao,
    private val Duacategory: Duacategorydao,
    private val Duanames: Duanamesdao,


)

{
     val duaitem=  DuaItem.getAllDuaItems()
     val duacategory=Duacategory.getCategory()
    val duanames=              Duanames.getAllduanames()
   val duanmesid= Duanames.getDuanamesid(id =String())
    fun getdunamesbyid(id : String)=Duanames.getDuanamesid(id)


}