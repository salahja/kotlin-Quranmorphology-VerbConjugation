package com.example.compose


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sj.hisnul.entity.hduanames


class LemmaViewModel(application: Application,lemmarabic:String) : AndroidViewModel(application) {
    val alldua: MutableLiveData<List<hduanames>> = MutableLiveData()



    private lateinit var util: Utils
   // var lemmarabic: String = "حمد"
    var lemmarabic: String = lemmarabic
    private val _words = MutableLiveData(listOf<VerseOccuranceModel>())
    val words: MutableLiveData<List<VerseOccuranceModel>> get() = _words



    var counter=0;

    private var nounBreakup: ArrayList<NounCorpusBreakup>? = null
    private var verbBreakup: ArrayList<VerbCorpusBreakup>? = null
    init {
     
   
                util = Utils(application)
                //     nounBreakup = util.getNounBreakup(lemmarabic) as ArrayList<NounCorpusBreakup>?
                //    verbBreakup= util.getVerbBreakUp(lemmarabic) as ArrayList<VerbCorpusBreakup>?
                nounBreakup = lemmarabic.trim()
                    ?.let { util.getNounBreakup(it) } as ArrayList<NounCorpusBreakup>?

                verbBreakup = lemmarabic.trim()
                    ?.let { util.getVerbBreakUp(it) } as ArrayList<VerbCorpusBreakup>?



            //    getNounData()
                true
            
 
 


   //     getVerbData()
    }
    public fun loadLists(lemmarabic: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<VerseOccuranceModel>()



                val nouns: ArrayList<CorpusNounWbwOccurance> =             util.getNounOccuranceBreakVerses(lemmarabic)
                                as ArrayList<CorpusNounWbwOccurance>

                val verses: ArrayList<CorpusVerbWbwOccurance> =
                    util.getVerbOccuranceBreakVerses(lemmarabic)
                            as ArrayList<CorpusVerbWbwOccurance>

                    val lists :ArrayList<String> =  ArrayList<String>()

                 for(ver in nouns){
                     var sb = StringBuilder()
                     sb.append(ver.surah).append(":").append(ver.ayah)
                     sb.append(ver.qurantext)
                         lists.add(sb.toString())

                     testList += VerseOccuranceModel(lists)

                 }









                    for(ver in verses){
                        var sb = StringBuilder()
                        sb.append(ver.surah).append(":").append(ver.ayah)
                        sb.append(ver.qurantext)
                        lists.add(sb.toString())

                        testList += VerseOccuranceModel(lists)

                    }










                _words.value=testList


             //   _words.add(testList)
                /* repeat(20) { testList += VerseOccuranceModel(id = it, title = "Card $it") }
                 _cards.emit(testList)*/
            }
        }
    }




/*    private fun getFakeData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<VerseOccuranceModel>()
                for (noun in nounBreakup!!) {
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



                    testList += VerseOccuranceModel(id = noun.id, title = sb.toString(),vlist)
                    _cards.emit(testList)
                }

                *//* repeat(20) { testList += VerseOccuranceModel(id = it, title = "Card $it") }
                 _cards.emit(testList)*//*
            }
        }
    }*/


}
