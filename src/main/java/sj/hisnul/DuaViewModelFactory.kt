package sj.hisnul

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import database.kotlinpackage.DuaInfoRepository



class DuaViewModelFactory(private val repository: DuaInfoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DuaViewModel::class.java)) {
              @Suppress("UNCHECKED_CAST")
               return DuaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}