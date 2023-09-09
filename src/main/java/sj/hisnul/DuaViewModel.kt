package sj.hisnul

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import database.kotlinpackage.DuaInfoRepository
import sj.hisnul.entity.hcategoryEnt


class DuaViewModel  (private val repository: DuaInfoRepository)
    : ViewModel() {


// Using LiveData and caching what allWords returns has several benefits:
// - We can put an observer on the data (instead of polling for changes) and only update the
//   the UI when the data actually changes.
// - Repository is completely separated from the UI through the ViewModel.

    // Creates a LiveData that has values collected from the origin Flow.
    val allWords: LiveData<List<hcategoryEnt>> = repository.allcategorydha.asLiveData()


}

/**
 * Launching a new coroutine to insert the data in a non-blocking way
 */

/*

    (private val repository: MazeedInfoRepository)
 : ViewModel() {
    var state by    mutableStateOf(Homestate())
        private set

    init {
        getDuaItems()
    }
  private    fun getDuaItems(){
         viewModelScope.launch {
             repository.duanames.collectLatest {
                 state=state.copy(
                     duanames = it
                 )
             }
         }

     }
     fun filterbyid(catid : String){
        viewModelScope.launch {
            repository.getdunamesbyid(catid).collectLatest {
                state=state.copy(duaitem = it)
            }
        }
    }
}

data class Homestate(

    val duaitem: List<hduanames> = emptyList(),
    val duacate:List<hcategory> = emptyList(),
    val duanames: List<hduanames> =  emptyList()

)
*/


/*
     val duaitem=  DuaItem.getAllDuaItems()
     val duacategory=Duacategory.getCategory()
    val duanames=              Duanames.getAllduanames()
 */