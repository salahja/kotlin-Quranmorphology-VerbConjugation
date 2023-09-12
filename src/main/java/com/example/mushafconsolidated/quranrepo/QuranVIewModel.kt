package com.example.mushafconsolidated.quranrepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.Entities.surahsummary
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.model.Juz
import com.example.utility.QuranGrammarApplication
import database.entity.kov
import kotlinx.coroutines.launch
import sj.hisnul.entity.hcategoryEnt

class QuranVIewModel(
    private val newrepository: QuranRepository = QuranGraph.repository
) :ViewModel(){


    var allquran: LiveData<List<QuranEntity>> = MutableLiveData()
    var sursumm: LiveData<List<surahsummary>> = MutableLiveData()
    var chapters: LiveData<List<ChaptersAnaEntity>> = MutableLiveData()
    var chapterslist: MutableLiveData<List<ChaptersAnaEntity>> = MutableLiveData()
    var juz: LiveData<List<Juz>> = MutableLiveData()
    val util = Utils(QuranGrammarApplication.context)
    var mafoolb: MutableLiveData<List<MafoolBihi>> = MutableLiveData()
    var haliya: MutableLiveData<List<HalEnt>> = MutableLiveData()
    var tameez: MutableLiveData<List<TameezEnt>> = MutableLiveData()
    var mutlaq: MutableLiveData<List<MafoolMutlaqEnt>> = MutableLiveData()
    var liajlihi: MutableLiveData<List<LiajlihiEnt>> = MutableLiveData()
    var badal: MutableLiveData<List<BadalErabNotesEnt>> = MutableLiveData()
    var bokmarks: LiveData<List<BookMarks>> = MutableLiveData()



    var duacategory: LiveData<List<hcategoryEnt>> = MutableLiveData()

    var kovs: LiveData<List<kov>> = MutableLiveData()


    val allchapters: MutableLiveData<List<ChaptersAnaEntity>> = MutableLiveData()
    fun getBookmarks(  ): LiveData<List<BookMarks>> {
        viewModelScope.launch {
            bokmarks=        newrepository.bookmarlist
        }
        return bokmarks
    }
    fun getBookmarksCollection(  ): LiveData<List<BookMarks>> {
        viewModelScope.launch {
            bokmarks=        newrepository.bookmarckcollection
        }
        return bokmarks
    }



    fun getHal(cid :  Int): MutableLiveData<List<HalEnt>> {
        viewModelScope.launch {
            haliya.value=        newrepository.gethalsurah(cid)
        }
        return haliya
    }

    fun gettameez(cid :  Int): MutableLiveData<List<TameezEnt>> {
        viewModelScope.launch {
            tameez.value=        newrepository.gettameezsurah(cid)
        }
        return tameez
    }

    fun getMutlaq(cid :  Int): MutableLiveData<List<MafoolMutlaqEnt>> {
        viewModelScope.launch {
            mutlaq.value=        newrepository.getmutlaqsura(cid)
        }
        return mutlaq
    }

    fun getLiajlihi(cid :  Int): MutableLiveData<List<LiajlihiEnt>> {
        viewModelScope.launch {
            liajlihi.value=        newrepository.getliajlihsura(cid)
        }
        return liajlihi
    }

    fun getbadal(cid :  Int): MutableLiveData<List<BadalErabNotesEnt>> {
        viewModelScope.launch {
            badal.value=        newrepository.getbadalnotes(cid)
        }
        return badal
    }


    fun getMaoolbysurah(cid :  Int): MutableLiveData<List<MafoolBihi>> {
        viewModelScope.launch {
            mafoolb.value=        newrepository.getMafoolbihiSurah(cid)
        }
        return mafoolb
    }




    //  private val allUsers: LiveData<List<ChaptersAnaEntity>> get() = allchapters


    fun loadListschapter(): MutableLiveData<List<ChaptersAnaEntity>> {

        //delay is simulating network request delay
        //delay(1000)
        //listOf is simulating usersRepository.getUsers()
        chapterslist.value= this.newrepository.chaptersmutable as List<ChaptersAnaEntity>?
        return    chapterslist
    }


    fun getAllChapters(): LiveData<List<ChaptersAnaEntity>> {


        viewModelScope.launch {
            chapters=        newrepository.chapters
        }


        return chapters
    }

    fun getVersesBySurahLive(cid :  Int): LiveData<List<QuranEntity>> {


        viewModelScope.launch {
            allquran=        newrepository.getsurahbychap(cid)
        }


        return allquran
    }
    fun getsurahayahVerses(cid :  Int,ayid : Int): LiveData<List<QuranEntity>> {


        viewModelScope.launch {
            allquran=        newrepository.getsurahbyayah(cid,ayid)
        }


        return allquran
    }

    fun getSurahSummary(cid :  Int): LiveData<List<surahsummary>> {


        viewModelScope.launch {
            sursumm=        newrepository.getSurahSummary(cid)
        }


        return sursumm
    }

    fun Insertbookmark(bookmar : BookMarks) = viewModelScope.launch {

        newrepository.insertlive(bookmar)
    }
    fun deletebookmark(bookmar : BookMarks) = viewModelScope.launch {

        newrepository.delete(bookmar)
    }

    fun deleteCollection(bookmar : String) = viewModelScope.launch {

        newrepository.deletecollection(bookmar)
    }




    /*    fun update(fav: Int, id: Int) = viewModelScope.launch {

            newrepository.updateFav(fav, id)
        }

        fun DuaItembyId(cat: String): LiveData<List<hduadetailsEnt>> {

            viewModelScope.launch {
                allduaitem = newrepository.getDuaitems(cat)
            }


            return allduaitem
        }*/



}

