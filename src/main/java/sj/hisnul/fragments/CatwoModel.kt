package sj.hisnul.fragments


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Utils.Utils
import kotlinx.coroutines.launch
import sj.hisnul.entity.hcategory

class CatwoModel(application: Application) : AndroidViewModel(application) {
    val duagrouptwo: MutableLiveData<List<hcategory>> = MutableLiveData()
    // private val allUsers: LiveData<List<hcategory>> get() = duagrouptwo

    val util = Utils(application)

    fun loadLists(): LiveData<List<hcategory>> {
        viewModelScope.launch {

            duagrouptwo.value = util.hcategory

        }
        return duagrouptwo
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

