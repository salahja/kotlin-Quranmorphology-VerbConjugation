package com.example.JustJava


import android.content.Context
import android.text.SpannableString
import com.example.mushafconsolidated.Entities.CorpusExpandWbwPOJO
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils.Utils
import com.example.mushafconsolidated.model.CorpusAyahWord
import com.example.mushafconsolidated.model.CorpusWbwWord
import java.text.NumberFormat
import java.util.Locale

class WbwSurah(
    private val context: Context,
    private val surah: Int,
    corpusayahWordArrayList: ArrayList<CorpusAyahWord>?,
    ruku: LinkedHashMap<Int, ArrayList<CorpusWbwWord>>
) {
    private var ruku: LinkedHashMap<Int, ArrayList<CorpusWbwWord>> =
        LinkedHashMap<Int, ArrayList<CorpusWbwWord>>()
    var corpusayahWordArrayList: ArrayList<CorpusAyahWord> = ArrayList<CorpusAyahWord>()

    init {
        if (corpusayahWordArrayList != null) {
            this.corpusayahWordArrayList = corpusayahWordArrayList
        }
        this.ruku = ruku
    }//    final Object o6 = wbwa.get(verseglobal).get(0);

    //   Object o4 = wbw.get(verseglobal).getWord();
    //  word.setWordindex(getIndex(wbw.get(verseglobal).getQuranverses()));
    //    word.setQuranversestr(wbw.get(verseglobal).getQuranverses());
    //  ayahWord.setSpannableverse(SpannableStringBuilder.valueOf(wbw.get(verseglobal).getQuranverses()));
    //   word.setWordsAr(ayanno);
    //    versesnumbers = getVersescount();
    val wordbyword: Unit

    //  ArrayList<CorpusExpandWbwPOJO> wbw = utils.getCorpusWbwBySurahAyahtopic(2,1);


        //  ArrayList<MafoolBihi> mafoolbihiquran = utils.getMafoolbihiquran();
        get() {
            val utils = Utils(
                context
            )
            val versesnumbers: Int
            //    versesnumbers = getVersescount();
            val wbw: List<CorpusExpandWbwPOJO?>? = utils.getCorpusWbwBySurah(
                surah
            )

            //  ArrayList<CorpusExpandWbwPOJO> wbw = utils.getCorpusWbwBySurahAyahtopic(2,1);


            //  ArrayList<MafoolBihi> mafoolbihiquran = utils.getMafoolbihiquran();
            var verseglobal = 0
            var tempVerseWord: Int
            val verseexit = wbw!!.size
            var verseno = 0
            var surahid = 0
            val intArray = context.resources.getIntArray(R.array.versescount)
            versesnumbers = intArray[surah - 1]
            var wordArrayListpassage: ArrayList<CorpusWbwWord> = ArrayList<CorpusWbwWord>()
            for (indexv in 1..versesnumbers) {
                tempVerseWord = indexv
                val ayahWord = CorpusAyahWord()
                val wordArrayList: ArrayList<CorpusWbwWord> = ArrayList<CorpusWbwWord>()
                while (tempVerseWord == indexv) {
                    if (verseexit == verseglobal) {
                        break
                    }
                    while (verseglobal < wbw.size) {
                        val word = CorpusWbwWord()
                        tempVerseWord = wbw[verseglobal]!!.ayah
                        if (tempVerseWord != indexv) {
                            break
                        }
                        //    final Object o6 = wbwa.get(verseglobal).get(0);
                        val sb = StringBuilder()
                        sb.append(wbw[verseglobal]!!.araone).append(wbw[verseglobal]!!.aratwo)
                        val sequence: CharSequence = (
                            wbw[verseglobal]!!.araone + wbw[verseglobal]!!.aratwo +
                                    wbw[verseglobal]!!.arathree + wbw[verseglobal]!!.arafour
                        )
                        //   Object o4 = wbw.get(verseglobal).getWord();
                        val en: Any = wbw[verseglobal]!!.en
                        val bn: Any = wbw[verseglobal]!!.bn
                        val ind: Any = wbw[verseglobal]!!.`in`
                        val ur: String? = wbw[verseglobal]!!.ur
                        word.rootword=(wbw[verseglobal]!!.root_a)
                        word.surahId=(wbw[verseglobal]!!.surah)
                        word.verseId=(wbw[verseglobal]!!.ayah)
                        word.wordno=(wbw[verseglobal]!!.wordno)
                        word.wordcount=(wbw[verseglobal]!!.wordcount)
                        word.wordsAr=(sequence.toString())
                        //  word.setWordindex(getIndex=(wbw.get(verseglobal).getQuranverses()));
                        word.translateEn=(en.toString())
                        word.translateBn=(bn.toString())
                        word.translateIndo=(ind.toString())
                        word.translationUrdu=(ur)
                        word.araone=(wbw[verseglobal]!!.araone)
                        word.aratwo=(wbw[verseglobal]!!.aratwo)
                        word.arathree=(wbw[verseglobal]!!.arathree)
                        word.arafour=(wbw[verseglobal]!!.arafour)
                        word.arafive=(wbw[verseglobal]!!.arafive)
                        word.tagone=(wbw[verseglobal]!!.tagone)
                        word.tagtwo=(wbw[verseglobal]!!.tagtwo)
                        word.tagthree=(wbw[verseglobal]!!.tagthree)
                        word.tagfour=(wbw[verseglobal]!!.tagfour)
                        word.tagfive=(wbw[verseglobal]!!.tagfive)
                        word.passage_no=(wbw[verseglobal]!!.passage_no)
                        word.detailsone=(wbw[verseglobal]!!.detailsone)
                        word.detailstwo=(wbw[verseglobal]!!.detailstwo)
                        word.detailsthree=(wbw[verseglobal]!!.detailsthree)
                        word.detailsfour=(wbw[verseglobal]!!.detailsfour)
                        word.detailsfive=(wbw[verseglobal]!!.detailsfive)
                        word.corpusSpnnableQuranverse=(SpannableString.valueOf(wbw[verseglobal]!!.qurantext))
                        //    word.setQuranversestr(wbw.get(verseglobal).getQuranverses());
                        word.quranversestr=(wbw[verseglobal]!!.qurantext)
                        word.translations=(wbw[verseglobal]!!.translation)
                        word.surahId=(wbw[verseglobal]!!.surah)
                        word.verseId=(wbw[verseglobal]!!.ayah)
                        word.wordno=(wbw[verseglobal]!!.wordno)
                        word.wordcount=(wbw[verseglobal]!!.wordcount)
                        verseno = wbw[verseglobal]!!.ayah
                        surahid = wbw[verseglobal]!!.surah
                        //  ayahWord.setSpannableverse(SpannableStringBuilder.valueOf(wbw.get(verseglobal).getQuranverses()));
                        ayahWord.spannableverse=(SpannableString.valueOf(wbw[verseglobal]!!.qurantext))
                        ayahWord.passage_no=(wbw[verseglobal]!!.passage_no)
                        wordArrayList.add(word)
                        wordArrayListpassage.add(word)
                        verseglobal++
                    }
                }
                val words = CorpusWbwWord()
                val nf = NumberFormat.getInstance(Locale.forLanguageTag("AR"))
                val s = "\uFD3E" + nf.format(verseno.toLong()) + "\uFD3F"
                //   word.setWordsAr(ayanno);
                words.araone=(s)
                words.surahId=(surahid)
                words.verseId=(verseno)
                wordArrayListpassage.add(words)
                ayahWord.word=(wordArrayList)
                val asize = wordArrayList.size
                if (asize >= 1) {
                    val ispassage: Int = wordArrayList[asize - 1]!!.passage_no
                    if (ispassage != 0) {
                        ruku[ispassage] = wordArrayListpassage
                        wordArrayListpassage = ArrayList<CorpusWbwWord>()
                    }
                }
                corpusayahWordArrayList.add(ayahWord)
            }
        }
}