package sj.hisnul.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.QuranAppDatabase
import com.example.mushafconsolidated.Repository
import com.example.mushafconsolidated.Utils

import kotlinx.coroutines.launch

import sj.hisnul.entity.hduanamesEnt

class AllDuaModel(application: Application)  :AndroidViewModel(application) {
    var alldua: LiveData<List<hduanamesEnt>> = MutableLiveData()
    val duacat: MutableLiveData<List<hduanamesEnt>> = MutableLiveData()
    var duachapter: LiveData<List<hduanamesEnt>> = MutableLiveData()
    val util = Utils(application)
    private lateinit var repository: Repository

    val db = QuranAppDatabase.getInstance(application)!!.gethDuaNamesDao()

    fun loadLists(): LiveData<List<hduanamesEnt>> {

        repository = Repository(db)
        viewModelScope.launch {
            alldua = repository.getDuanames()
        }


        return alldua
    }

    fun Duacatnames(cat: String): LiveData<List<hduanamesEnt>> {

        viewModelScope.launch {
            duacat.value = util.getDuaCATNAMES(cat) as List<hduanamesEnt>?
        }


        return duacat
    }

    fun Duadetailsbychapter(chapter: Int): LiveData<List<hduanamesEnt>> {

        repository = Repository(db)
        viewModelScope.launch {
            duachapter = repository.getdualistbychapter(chapter)
        }

        return duachapter
    }

    fun getBookmarked(chapter: Int): LiveData<List<hduanamesEnt>> {

        repository = Repository(db)
        viewModelScope.launch {
            duachapter = repository.getBookmarked(chapter)
        }

        return duachapter
    }




    fun update(fav: Int, id: Int) = viewModelScope.launch {
        repository = Repository(db)
        repository.updateFav(fav, id)
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

