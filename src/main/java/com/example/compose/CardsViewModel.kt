package com.example.compose


import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CardsViewModel(mApplication: Application, mParam: String) : ViewModel() {

    private lateinit var util: Utils
   // var rootword: String = "حمد"
    var rootword: String = mParam
    private val _cards = MutableStateFlow(listOf<ExpandableCardModel>())
    val cards: StateFlow<List<ExpandableCardModel>> get() = _cards

    private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    var counter=0;
    val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList
    private var nounCorpusArrayList: ArrayList<NounCorpusBreakup>? = null
    private var verbCorpusArray: ArrayList<VerbCorpusBreakup>? = null
    init {
        viewModelScope.launch {
            val result= withContext(Dispatchers.IO) {
                util = Utils(mApplication)
                //     nounCorpusArrayList = util.getNounBreakup(rootword) as ArrayList<NounCorpusBreakup>?
                //    verbCorpusArray= util.getVerbBreakUp(rootword) as ArrayList<VerbCorpusBreakup>?
                nounCorpusArrayList = rootword.trim()
                    ?.let { util.getNounBreakup(it) } as ArrayList<NounCorpusBreakup>?
                verbCorpusArray = rootword.trim()
                    ?.let { util.getVerbBreakUp(it) } as ArrayList<VerbCorpusBreakup>?



                getNounData()
                true
            }
            Log.d("result","$result")
        }


   //     getVerbData()
    }
    private fun getNounData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<ExpandableCardModel>()
                for (noun in nounCorpusArrayList!!) {
                    var sb = StringBuilder()
                    sb.append(noun.araword).append("occurs").append(":").append(noun.count).append(" ").append("Times as").append(" ")
                            if(noun.tag.equals("N")){
                                sb.append("Noun")
                            } else if(noun.tag.equals("ADJ")){
                                sb.append("Adjective")
                            } else if (noun.tag.equals("null")){
                                 if(noun.propone.equals("ACT")){
                                     sb.append("Active Participles")
                                 } else {
                                     sb.append("Passive Participles")
                                 }

                            }

                    sb.append("  ").append(noun.surah).append(":").append(noun.ayah)
                    val verses: ArrayList<CorpusNounWbwOccurance> =
                        util.getNounOccuranceBreakVerses(noun.lemma_a!!)
                                as ArrayList<CorpusNounWbwOccurance>
                    val lists :ArrayList<String> =  ArrayList<String>()

                 for(ver in verses){
                     var sb = StringBuilder()
                     sb.append(ver.surah).append(":").append(ver.ayah)
                     sb.append(ver.qurantext)
                         lists.add(sb.toString())



                 }





                    testList += ExpandableCardModel(id = counter++, title = sb.toString(),lists)

                }

                for (noun in verbCorpusArray!!) {
                    var sb = StringBuilder()
                    sb.append(noun.root_a).append("occurs").append(":").append(noun.count).append(" ").append("Times as").append(" ")
                    if(noun.tag.equals("V")){
                        sb.append("Verb")
                    } else{
                        sb.append("Verb")

                    }

                    sb.append("  ").append(noun.chapterno).append(":").append(noun.verseno)
                    val verses: ArrayList<CorpusVerbWbwOccurance> =
                        util.getVerbOccuranceBreakVerses(noun.lemma_a!!)
                                as ArrayList<CorpusVerbWbwOccurance>
                    val lists :ArrayList<String> =  ArrayList<String>()

                    for(ver in verses){
                        var sb = StringBuilder()
                        sb.append(ver.surah).append(":").append(ver.ayah)
                        sb.append(ver.qurantext)
                        lists.add(sb.toString())



                    }





                    testList += ExpandableCardModel(id = counter++, title = sb.toString(),lists)

                }





                _cards.emit(testList)
                /* repeat(20) { testList += ExpandableCardModel(id = it, title = "Card $it") }
                 _cards.emit(testList)*/
            }
        }
    }

    private fun getVerbData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<ExpandableCardModel>()
                for (noun in verbCorpusArray!!) {
                    var sb = StringBuilder()
                    sb.append(noun.root_a).append("occurs").append(":").append(noun.count).append(" ").append("Times as").append(" ")
                    if(noun.tag.equals("V")){
                        sb.append("Verb")
                    } else{
                        sb.append("Verb")

                    }

                    sb.append("  ").append(noun.chapterno).append(":").append(noun.verseno)
                    val verses: ArrayList<CorpusVerbWbwOccurance> =
                        util.getVerbOccuranceBreakVerses(noun.lemma_a!!)
                                as ArrayList<CorpusVerbWbwOccurance>
                    val lists :ArrayList<String> =  ArrayList<String>()

                    for(ver in verses){
                        var sb = StringBuilder()
                        sb.append(ver.surah).append(":").append(ver.ayah)
                        sb.append(ver.qurantext)
                        lists.add(sb.toString())



                    }





                    testList += ExpandableCardModel(id = counter++, title = sb.toString(),lists)

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
