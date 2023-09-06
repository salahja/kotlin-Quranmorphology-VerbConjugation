package sj.hisnul.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Utils.Utils
import kotlinx.coroutines.launch
import sj.hisnul.entity.hduanames

class AllDuaModel(application: Application)  :AndroidViewModel(application) {
    val alldua: MutableLiveData<List<hduanames>> = MutableLiveData()
    val duacat: MutableLiveData<List<hduanames>> = MutableLiveData()
    val duachapter: MutableLiveData<List<hduanames>> = MutableLiveData()
    val util = Utils(application)


    fun loadLists(): LiveData<List<hduanames>> {

        viewModelScope.launch {
            alldua.value = util.getAllList() as List<hduanames>?
        }


        return    alldua
    }

    fun Duacatnames(cat:String): LiveData<List<hduanames>> {

          viewModelScope.launch {
              duacat.value = util.getDuaCATNAMES(cat) as List<hduanames>?
        }


        return    duacat
    }

    fun Duadetailsbychaptr(chapter:Int): LiveData<List<hduanames>> {

        viewModelScope.launch {
            duachapter.value = util.getdualistbychapter(chapter) as List<hduanames>?
        }


        return    duachapter
    }





    fun update(fav:Int, id:Int ) = viewModelScope.launch {
        util.updateFav(fav,id)
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

