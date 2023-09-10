package sj.hisnul.VIewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import database.kotlinpackage.DuaInfoRepository
import sj.hisnul.entity.hduanamesEnt

    class NewAllDuaModel(repository: DuaInfoRepository) : ViewModel() {
        private val repository: DuaInfoRepository=repository
/*        private val _readAllData = Flow<List<hduanames>>()
        val readAllData: Flow<List<hduanames>> = _readAllData
     */
        //   val alldua:  MutableLiveData<List<hduanames>> = MutableLiveData()
        val duacat: MutableLiveData<List<hduanamesEnt>> = MutableLiveData()
        val duachapter: MutableLiveData<List<hduanamesEnt>> = MutableLiveData()


       // val alldua: LiveData<List<hduanames>> = repository.alldunames.asLiveData()
    //    val duabycatid: LiveData<List<hduanames>> = repository.getCatIdDuas(cat : String)
       // val duabychapid: LiveData<List<hduanames>> = repository.duaDetailsbyChapId(chapterId = Int).asLiveData()






/*

        fun duabycatid(userId: String) {
            viewModelScope.launch(Dispatchers.IO) {
                _readAllData.postValue(repository.getCatIdDuas(userId = userId))
            }
        }




        fun duabychapid(userId: String) {
            viewModelScope.launch(Dispatchers.IO) {
                _readAllData.postValue(repository.getCatIdDuas(userId = userId))
            }
        }
*/










    }
