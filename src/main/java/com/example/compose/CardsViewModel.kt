package com.example.compose


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardsViewModel(application: Application) : AndroidViewModel(application) {

    private var util: Utils
    var nounroot: String = "حمد"
    private val _cards = MutableStateFlow(listOf<ExpandableCardModel>())
    val cards: StateFlow<List<ExpandableCardModel>> get() = _cards

    private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList
    private var nounCorpusArrayList: ArrayList<NounCorpusBreakup>? = null

    init {
       util = Utils(application)
        nounCorpusArrayList = util.getNounBreakup(nounroot) as ArrayList<NounCorpusBreakup>?
        getFakeDatas()
    }
    private fun getFakeDatas() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<ExpandableCardModel>()
                for (noun in nounCorpusArrayList!!) {
                    var sb = StringBuilder()
                    sb.append(noun.araword).append("occurs").append(":").append(noun.count)
                        .append("as").append(noun.tag)
                    sb.append(noun.surah).append(":").append(noun.ayah)
                    val verses: ArrayList<CorpusNounWbwOccurance> =
                        util.getNounOccuranceBreakVerses(noun.lemma_a!!)
                                as ArrayList<CorpusNounWbwOccurance>
                    val lists :ArrayList<String> =  ArrayList<String>()

                 for(ver in verses){

                         lists.add(ver.qurantext.toString())



                 }





                    testList += ExpandableCardModel(id = noun.id, title = sb.toString(),lists)

                }
                _cards.emit(testList)
                /* repeat(20) { testList += ExpandableCardModel(id = it, title = "Card $it") }
                 _cards.emit(testList)*/
            }
        }
    }
/*    private fun getFakeData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<ExpandableCardModel>()
                for (noun in nounCorpusArrayList!!) {
                    var sb = StringBuilder()
                    sb.append(noun.araword).append("occurs").append(":").append(noun.count)
                        .append("as").append(noun.tag)
                    sb.append(noun.surah).append(":").append(noun.ayah)
                    val verses: ArrayList<CorpusNounWbwOccurance> =
                        util.getNounOccuranceBreakVerses(noun.lemma_a!!)
                         as ArrayList<CorpusNounWbwOccurance>
                    val vlist: ArrayList<String> =
                        ArrayList()

                    for(vers in verses){
                        vlist.add(vers.qurantext!!)

                    }



                    testList += ExpandableCardModel(id = noun.id, title = sb.toString(),vlist)
                    _cards.emit(testList)
                }

                *//* repeat(20) { testList += ExpandableCardModel(id = it, title = "Card $it") }
                 _cards.emit(testList)*//*
            }
        }
    }*/

    fun onCardArrowClicked(cardId: Int) {
        _expandedCardIdsList.value = _expandedCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }
}
