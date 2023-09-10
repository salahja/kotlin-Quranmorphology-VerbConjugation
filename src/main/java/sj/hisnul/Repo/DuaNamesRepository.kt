package sj.hisnul.Repo






import androidx.lifecycle.LiveData
import sj.hisnul.Dao.hDuaNamesDao
import sj.hisnul.entity.hduanamesEnt


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class DuaNamesRepository(

    private val gethDuaNamesDao: hDuaNamesDao,





) {
    fun getDuanames(): LiveData<List<hduanamesEnt>> = gethDuaNamesDao.duanameslive()


    fun getdualistbychapter(cid: Int):   LiveData<List<hduanamesEnt>>
            = gethDuaNamesDao.getdualistbychapter(cid)




    fun getDunamesbyid(id: String?):   LiveData<List<hduanamesEnt>>
            = gethDuaNamesDao.getDuanamesid(id)


    fun getDunamesbyCatId(id: String?):   LiveData<List<hduanamesEnt>>
            =gethDuaNamesDao.getDunamesbyCatId(id!!)


    fun getDunamesbyCatIdnew(id: String?):   LiveData<List<hduanamesEnt>>
            =gethDuaNamesDao.getDunamesbyCatIdnew(id)


    fun getDuanamesDetails(id: String?):   LiveData<List<hduanamesEnt>>
            =gethDuaNamesDao.getDuanamesByID(id)


    fun getBookmarked(id: Int):   LiveData<List<hduanamesEnt>>
            =gethDuaNamesDao.getBookmarked(id)


    fun getIsmarked(id: String?):   LiveData<List<hduanamesEnt>>
            =gethDuaNamesDao.isBookmarked(id)

    suspend fun updateFav(fav: Int, id: Int) {
      gethDuaNamesDao.updateFav(fav, id)
    }


}