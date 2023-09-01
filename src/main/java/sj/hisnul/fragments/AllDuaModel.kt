package sj.hisnul.fragments

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mushafconsolidated.Utils.Utils
import sj.hisnul.entity.hduanames

class AllDuaModel()  :ViewModel() {
    val alldua: MutableLiveData<List<hduanames>> = MutableLiveData()
    private val allUsers: LiveData<List<hduanames>> get() = alldua



    fun loadLists(context: Context?): LiveData<List<hduanames>> {
        val util = Utils(context)
        //delay is simulating network request delay
        //delay(1000)
        //listOf is simulating usersRepository.getUsers()
        alldua.value = util.getAllList() as List<hduanames>?
        return    alldua
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

