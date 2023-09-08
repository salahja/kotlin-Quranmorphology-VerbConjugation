package sj.hisnul.fragments


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import database.kotlinpackage.DuaInfoRepository
import sj.hisnul.entity.hcategory

class CatwoModel(repository: DuaInfoRepository) : ViewModel() {
    val allcategory: LiveData<List<hcategory>> = repository.allcategory.asLiveData()
    // private val allUsers: LiveData<List<hcategory>> get() = duagrouptwo





    }

class CatwoModelFactory(private val repository: DuaInfoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatwoModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatwoModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
