package sj.hisnul.VIewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.QuranAppDatabase
import com.example.mushafconsolidated.Utils
import kotlinx.coroutines.launch
import sj.hisnul.Repo.DuaItemRepository
import sj.hisnul.entity.hduadetailsEnt
import sj.hisnul.entity.hduanamesEnt

class DuaItemVM(application: Application)  :AndroidViewModel(application) {
    var allduaitem: LiveData<List<hduadetailsEnt>> = MutableLiveData()
    val duacat: MutableLiveData<List<hduanamesEnt>> = MutableLiveData()
    var duachapter: LiveData<List<hduanamesEnt>> = MutableLiveData()
    val util = Utils(application)
    private lateinit var  DuaItemRepository: DuaItemRepository

    val db = QuranAppDatabase.getInstance(application)!!.hDuaItemDao()



    fun DuaItembyId(cat: String): LiveData<List<hduadetailsEnt>> {
        DuaItemRepository = DuaItemRepository(db)
        viewModelScope.launch {
            allduaitem = DuaItemRepository.getDuaitems(cat)
        }


        return allduaitem
    }



}




/*
private val _allUsers = MutableLiveData<List<User>>()
private val allUsers: LiveData<List<User>> get() = _allUsers

fun fetchAllUsers(): LiveData<List<User>> {
    viewModelScope.launch {
        //delay is simulating network request delay
        delay(1000)
        //listOf is simulating usersRepository.getUsers()
        _allUsers.value = listOf(User("name1"), User("name2"), User("name3"))
    }
    return allUsers
}
 */

