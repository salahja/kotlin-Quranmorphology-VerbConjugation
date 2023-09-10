package sj.hisnul.Repo






import androidx.lifecycle.LiveData
import sj.hisnul.Dao.hDuaItemDao
import sj.hisnul.entity.hduadetailsEnt


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class DuaItemRepository(

    private val gethDuaNamesDao: hDuaItemDao,





) {
    fun getDuaitems(id: String?):   LiveData<List<hduadetailsEnt>>
            = gethDuaNamesDao.getDitem(id)






}