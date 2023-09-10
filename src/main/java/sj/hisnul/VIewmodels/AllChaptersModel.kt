package sj.hisnul.VIewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Utils

class AllChaptersModel(application: Application)  :AndroidViewModel(application) {
    val allchapters: MutableLiveData<List<ChaptersAnaEntity>> = MutableLiveData()
     private val allUsers: LiveData<List<ChaptersAnaEntity>> get() = allchapters


    fun loadLists(): LiveData<List<ChaptersAnaEntity>> {
        val util = Utils(getApplication())
        //delay is simulating network request delay
        //delay(1000)
        //listOf is simulating usersRepository.getUsers()
        allchapters.value = util.getAllAnaChapters() as List<ChaptersAnaEntity>?
        return    allchapters
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

