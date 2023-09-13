package com.example.mushafconsolidated.fragments

import Utility.ArabicLiterals
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.preference.PreferenceManager
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import com.example.Constant
import com.example.justJava.TextBorderSpan
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO
import com.example.mushafconsolidated.Entities.NewKanaEntity
import com.example.mushafconsolidated.Entities.NewMudhafEntity
import com.example.mushafconsolidated.Entities.NewNasbEntity
import com.example.mushafconsolidated.Entities.NewShartEntity
import com.example.mushafconsolidated.Entities.SifaEntity
import com.example.mushafconsolidated.Entities.wbwentity
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.mushafconsolidated.model.Word
import com.example.utility.CorpusUtilityorig
import com.example.utility.QuranGrammarApplication


internal class SplitQuranVerses  // --Commented out by Inspection (26/04/22, 12:48 AM):private List<CorpusAyahWord> corpusayahWordArrayList;
{
    fun splitSingleVerse(quraverses: String): java.util.ArrayList<Word> {
        val ayahWordArrayList = java.util.ArrayList<Word>()
        val s = quraverses.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        for (i in s.indices) {
            val word = Word()
            word.wordsAr=(s[i])
            word.wordno=(i + 1)
            ayahWordArrayList.add(word)
        }
        return ayahWordArrayList
        //     return ayahWords;
    }
}
class ExpandableListData {
    private val chapterid: Int
    private val ayanumber: Int
    private var corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>? = null
    private var corpusSurahWordtwo: ArrayList<QuranCorpusWbw>? = null
    private var utils: Utils? = null
    private var whichwbw: String? = null
    private var dark: Boolean = false

    constructor(
        chapterid: Int,
        ayanumber: Int,
        corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>?,
        utils: Utils?
    ) {
        this.chapterid = chapterid
        this.ayanumber = ayanumber
        this.corpusSurahWord = corpusSurahWord
        this.utils = utils
    }

// --Commented out by Inspection START (11/09/23, 10:45 pm):
//    constructor(corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>) {
//        chapterid = corpusSurahWord.get(0).surah
//        ayanumber = corpusSurahWord.get(0).ayah
//    }
// --Commented out by Inspection STOP (11/09/23, 10:45 pm)

    val data: LinkedHashMap<String, List<SpannableString>>
        get() {
            val prefs: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
            val preferences: String? = prefs.getString("theme", "dark")
            dark = ((preferences == "dark") || (preferences == "blue") || (preferences == "green"))
            val whichtranslation: String? = prefs.getString("selecttranslation", "en_sahih")
            whichwbw = prefs.getString("wbw", "en_sahih")
            val expandableListDetail: LinkedHashMap<String, List<SpannableString >> =
                LinkedHashMap()
            val verse: MutableList<SpannableString > = ArrayList()
            val translation: MutableList<SpannableString > = ArrayList()
            verse.add(
                SpannableString .valueOf(
                    corpusSurahWord!![0].surah.toString() + ":" + corpusSurahWord!![0]
                        .surah + ":-" + corpusSurahWord!![0].qurantext
                )
            )
            if ((whichtranslation == "en_sahih")) {
                translation.add(
                    SpannableString .valueOf(
                        corpusSurahWord!![0].translation
                    )
                )
            } else {
                translation.add(
                    SpannableString .valueOf(
                        corpusSurahWord!![0].ur_junagarhi
                    )
                )
            }
            val shartarray: MutableList<SpannableString > = ArrayList()
            newSetShart(shartarray)
            val harfnasbarray: MutableList<SpannableString > = ArrayList()
            setNewNasb(harfnasbarray)
            val mausoofsifaarray: MutableList<SpannableString > = ArrayList()
            setMausoof(mausoofsifaarray)
            val mudhafarray: MutableList<SpannableString > = ArrayList()
            setMudhaf(mudhafarray)
            val kanaarray: MutableList<SpannableString > = ArrayList()
            newsetKana(kanaarray)
            val kana: List<SpannableString > = ArrayList()
            expandableListDetail["Verse"] = verse
            expandableListDetail["Translation"] = translation
            expandableListDetail["Conditional/جملة شرطية\""] = shartarray
            expandableListDetail["Accusative/ "] = harfnasbarray
            expandableListDetail["Verb kāna/كان واخواتها"] = kanaarray
            expandableListDetail["Adjectival Phrases/مرکب توصیفی"] = mausoofsifaarray
            expandableListDetail["Possessive/إضافَة"] = mudhafarray
            return expandableListDetail
        }

    private fun setNewNasb(hasbarray: MutableList<SpannableString >) {
        val kanaSurahAyahnew: List<NewNasbEntity?>? =
            utils!!.getHarfNasbIndSurahAyahSnew(chapterid, ayanumber)
        val prefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        if (kanaSurahAyahnew != null) {
            for (nasbEntity: NewNasbEntity? in kanaSurahAyahnew) {
         /*       if (nasbEntity != null) {
                    if (nasbEntity.surah == 3 && nasbEntity.ayah == 118) {
                        //System.out.println("CHECK");
                    }
                }*/
                if (dark) {
                    Constant.harfinnaspanDark = ForegroundColorSpan(Color.GREEN)
                    Constant.harfismspanDark = ForegroundColorSpan(Constant.BCYAN)
                    Constant.harfkhabarspanDark = ForegroundColorSpan(Color.YELLOW)
                } else {
                    Constant.harfinnaspanDark = ForegroundColorSpan(Constant.KASHMIRIGREEN)
                    Constant.harfismspanDark = ForegroundColorSpan(Constant.prussianblue)
                    Constant.harfkhabarspanDark = ForegroundColorSpan(Constant.deepburnsienna)
                }
                var harfofverse: String
                var ismofverse: String
                var khabarofverse: String
                val indexstart: Int = nasbEntity!!.indexstart
                val indexend: Int = nasbEntity.indexend
                val ismstartindex: Int = nasbEntity.ismstart
                val ismendindex: Int = nasbEntity.ismend
                val khabarstartindex: Int = nasbEntity.khabarstart
                val khabarendindex: Int = nasbEntity.khabarend
                val quranverses: String? = corpusSurahWord!![0].qurantext

                harfofverse = quranverses!!.substring(indexstart, indexend)


                ismofverse = quranverses.substring(ismstartindex, ismendindex)


                khabarofverse = quranverses.substring(khabarstartindex, khabarendindex)

                val isharfb: Boolean = indexstart >= 0 && indexend > 0
                val isism: Boolean = ismstartindex >= 0 && ismendindex > 0
                val iskhabar: Boolean = khabarstartindex >= 0 && khabarendindex > 0
                val a: Boolean = isharfb && isism && iskhabar
                val d: Boolean = isharfb && iskhabar
                val b: Boolean = isharfb && isism
                val c: Boolean = isharfb
                val harfword: Int = nasbEntity.harfwordno
                val shartSword: Int = nasbEntity.ismstartwordno
                val shartEword: Int = nasbEntity.ismendwordno
                val jawbSword: Int = nasbEntity.khabarstartwordno
                val jawabEword: Int = nasbEntity.khabarendwordno
                val sb: StringBuilder  = StringBuilder ()
                val khabarsb: StringBuilder  = StringBuilder ()
                val tb = TextBorderSpan()
                val iscolored: Boolean = false
                val spannable: SpannableString  = SpannableString (quranverses)
                var harfspannble: SpannableString
                var harfismspannable: SpannableString
                var khabarofversespannable: SpannableString
                val connected: Int = nasbEntity.khabarstart - nasbEntity.indexend
                if (a) {
                    val isismkhabarconnected: Int = nasbEntity.khabarstart - nasbEntity.ismend
                    harfspannble = SpannableString (harfofverse)
                    harfismspannable = SpannableString (ismofverse)
                    khabarofversespannable = SpannableString (khabarofverse)
                    harfspannble.setSpan(
                        Constant.harfinnaspanDark,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    harfismspannable.setSpan(
                        Constant.harfismspanDark,
                        0,
                        ismofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    khabarofversespannable.setSpan(
                        Constant.harfkhabarspanDark,
                        0,
                        khabarofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    if (nasbEntity.ismstart > nasbEntity.khabarstart) {
                        val charSequence: CharSequence = TextUtils.concat(
                            harfspannble,
                            " ",
                            khabarofversespannable,
                            " ",
                            harfismspannable
                        )
                        hasbarray.add(SpannableString .valueOf(charSequence))
                    } else {
                        val charSequence: CharSequence = TextUtils.concat(
                            harfspannble,
                            " ",
                            harfismspannable,
                            " ",
                            khabarofversespannable
                        )
                        hasbarray.add(SpannableString .valueOf(charSequence))
                    }
                    if (isismkhabarconnected == 1) {
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah, harfword, jawabEword
                        )
                        if (list != null) {
                            for (w: wbwentity?  in list) {
                                String ()
                                var temp: StringBuilder = getSelectedTranslation(w!!)
                                sb.append(temp).append(" ")
                            }
                        }
                    } else {
                        val wbwayah:  List<wbwentity?>? = utils!!.getwbwQuranBySurahAyah(
                            corpusSurahWord!![0].surah, corpusSurahWord!![0].ayah
                        )
                        if (wbwayah != null) {
                            for (w: wbwentity?  in wbwayah) {
                                String ()
                                var temp: StringBuilder = getSelectedTranslation(w!!)
                                if (w.wordno == harfword) {
                                    sb.append(temp).append(" ")
                                } else if (w.wordno in shartSword..shartEword) {
                                    sb.append(temp).append(" ")
                                } else if (w.wordno in jawbSword..jawabEword) {
                                    //     sb. append("... ");
                                    khabarsb.append(temp).append(" ")
                                }
                            }
                        }
                        sb.append(".....")
                        sb.append(khabarsb)
                        hasbarray.add(SpannableString .valueOf(sb.toString()))
                    }
                    hasbarray.add(SpannableString .valueOf(sb.toString()))
                    //  CharSequence first = TextUtils.concat(harfspannble," ",shartofverse);
                } else if (d) {
                    harfspannble = SpannableString (harfofverse)
                    khabarofversespannable = SpannableString (khabarofverse)
                    harfspannble.setSpan(
                        Constant.harfshartspanDark,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    khabarofversespannable.setSpan(
                        Constant.jawabshartspanDark,
                        0,
                        khabarofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequence: CharSequence =
                        TextUtils.concat(harfspannble, " ", khabarofversespannable)
                    hasbarray.add(SpannableString .valueOf(charSequence))
                    //     String  sb = new String ();
                    val wordfrom: Int = nasbEntity.harfwordno
                    var wordto: Int
                    val split: Array<String> =
                        khabarofverse.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                    wordto = if (split.size == 1) {
                        nasbEntity.khabarstartwordno
                    } else {
                        nasbEntity.khabarendwordno
                    }
                    val isconnected: Int = nasbEntity.khabarstart - nasbEntity.indexend
                    if (isconnected == 1) {
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            wordfrom,
                            wordto
                        )
                        if (list != null) {
                            for (w: wbwentity? in list) {
                                String ()
                                var temp: StringBuilder = getSelectedTranslation(w!!)
                                sb.append(temp).append(" ")
                            }
                        }
                        hasbarray.add(SpannableString .valueOf(sb.toString()))
                    } else {
                        val wordfroms: Int = nasbEntity.harfwordno
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            wordfrom,
                            wordfroms
                        )
                        val from: Int = nasbEntity.khabarstartwordno
                        var to: Int = nasbEntity.khabarendwordno
                        if (to == 0) {
                            to = from
                        }
                        if (list != null) {
                            sb.append(list[0]!!.en).append(".......")
                        }
                        when (whichwbw) {
                            "en" -> if (list != null) {
                                sb.append(list[0]!!.en).append(".......")
                            }
                            "ur" -> sb.append(list?.get(0)!!.ur).append(".......")
                            "bn" -> sb.append(list?.get(0)!!.bn).append(".......")
                            "id" -> sb.append(list?.get(0)!!.id).append(".......")
                        }
                        val lists: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            from,
                            to
                        )
                        if (lists != null) {
                            for (w: wbwentity? in lists) {
                                String ()
                                var temp: StringBuilder = getSelectedTranslation(w!!)
                                sb.append(temp).append(" ")
                            }
                        }
                        hasbarray.add(SpannableString .valueOf(sb.toString()))
                    }
                } else if (b) {
                    Constant.harfshartspanDark = ForegroundColorSpan(Constant.GOLD)
                    Constant.shartspanDark = ForegroundColorSpan(Color.GREEN)
                    Constant.jawabshartspanDark = ForegroundColorSpan(Color.CYAN)
                    harfspannble = SpannableString (harfofverse)
                    harfismspannable = SpannableString (ismofverse)
                    harfspannble.setSpan(
                        Constant.harfshartspanDark,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    harfismspannable.setSpan(
                        Constant.shartspanDark,
                        0,
                        ismofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequences: CharSequence =
                        TextUtils.concat(harfspannble, " $harfismspannable")
                    hasbarray.add(SpannableString .valueOf(charSequences))
                    //    kanaarray.add(SpannableString .valueOf(charSequence));
                    val ssb: StringBuilder=StringBuilder()
                    // SpannableString  trstr = getFragmentTranslations(quranverses, sb, charSequence, false);
                    // kanaarray.add(trstr);
                    val ismconnected: Int = nasbEntity.ismstart - nasbEntity.indexend
                    val wordfrom: Int = nasbEntity.harfwordno
                    val wordto: Int = nasbEntity.ismendwordno
                    if (ismconnected == 1) {
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            wordfrom,
                            wordto
                        )
                        for (w: wbwentity? in list!!) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            sb.append(temp).append(" ")
                        }
                        hasbarray.add(SpannableString .valueOf(sb.toString()))
                    } else {
                        val wordfroms: Int = nasbEntity.harfwordno
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            wordfroms,
                            wordfroms
                        )
                        //    kanaarray.add(SpannableString .valueOf(list.get(0).en));
                        val from: Int = nasbEntity.harfwordno
                        val ismfrom: Int = nasbEntity.ismstartwordno
                        val ismto: Int = nasbEntity.ismendwordno
                        //     sb.append(list.get(0).en).append("----");
                        val harf: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            from,
                            from
                        )
                        val ism: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            ismfrom,
                            ismto
                        )
                        for (w: wbwentity? in harf!!) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            sb.append(temp).append(" ")
                        }
                        sb.append(".....")
                        for (w: wbwentity? in ism!!) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            sb.append(temp).append(" ")
                        }
                        hasbarray.add(SpannableString .valueOf(sb.toString()))
                    }
                } else if (c) {
                    harfspannble = SpannableString (harfofverse)
                    harfspannble.setSpan(
                        Constant.harfshartspanDark,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequence: CharSequence = TextUtils.concat(harfspannble)
                    hasbarray.add(SpannableString .valueOf(charSequence))
                    val wordfroms: Int = nasbEntity.harfwordno
                    val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                        corpusSurahWord!![0].surah,
                        corpusSurahWord!![0].ayah,
                        wordfroms,
                        wordfroms
                    )
                    val sbss: StringBuffer = StringBuffer()
                    if ((whichwbw == "en")) {
                        if (list != null) {
                            sbss.append(list[0]!!.en).append(".......")
                        }
                    } else if ((whichwbw == "ur")) {
                        if (list != null) {
                            sbss.append(list[0]!!.ur).append(".......")
                        }
                    } else if ((whichwbw == "bn")) {
                        if (list != null) {
                            sbss.append(list[0]!!.bn).append(".......")
                        }
                    } else if ((whichwbw == "id")) {
                        if (list != null) {
                            sbss.append(list[0]!!.id).append(".......")
                        }
                    }
                    hasbarray.add(SpannableString .valueOf(sbss))
                }
                // kanaarray.add(spannable);
            }
        }
    }

    private fun getSelectedTranslation(tr: wbwentity): StringBuilder {
        val sb: StringBuilder=StringBuilder()
        when (whichwbw) {
            "en" -> sb.append(tr.en)
            "ur" -> sb.append(tr.ur)
            "bn" -> sb.append(tr.bn)
            "id" -> sb.append(tr.id)
        }
        sb.append(" ")
        return sb
    }

    private fun newsetKana(kanaarray: MutableList<SpannableString >) {
        val kanaSurahAyahnew: List<NewKanaEntity?>? =
            utils!!.getKanaSurahAyahnew(chapterid, ayanumber)
        var harfkana: ForegroundColorSpan?
        var kanaism: ForegroundColorSpan?
        var kanakhbar: ForegroundColorSpan?
        if (dark) {
            harfkana = ForegroundColorSpan(Constant.GOLD)
            kanaism = ForegroundColorSpan(Constant.ORANGE400)
            kanakhbar = ForegroundColorSpan(Color.CYAN)
        } else {
            harfkana = ForegroundColorSpan(Constant.FORESTGREEN)
            kanaism = ForegroundColorSpan(Constant.KASHMIRIGREEN)
            kanakhbar = ForegroundColorSpan(Constant.WHOTPINK)
        }
        if (kanaSurahAyahnew != null) {
            for (kana: NewKanaEntity? in kanaSurahAyahnew) {

                var harfofverse: String
                var ismofverse: String
                var khabarofverse: String
                val start: Int = kana!!.indexstart
                val end: Int = kana.indexend
                val isstart: Int = kana.ismkanastart
                val issend: Int = kana.ismkanaend
                val khabarstart: Int = kana.khabarstart
                val khabarend: Int = kana.khabarend
                val quranverses: String? = corpusSurahWord!![0].qurantext
                harfofverse = quranverses!!.substring(start, end)
                ismofverse = if (issend > isstart) {
                    quranverses.substring(isstart, issend)
                } else {
                    ""
                }
                khabarofverse = quranverses.substring(khabarstart, khabarend)
                val isharfb: Boolean = start >= 0 && end > 0
                val isism: Boolean = isstart >= 0 && issend > 0
                val isjawab: Boolean = khabarstart >= 0 && khabarend > 0
                val a: Boolean = isharfb && isism && isjawab
                val d: Boolean = isharfb && isjawab
                val b: Boolean = isharfb && isism
                val c: Boolean = isharfb
                val harfword: Int = kana.harfwordno
                val ismSword: Int = kana.ismwordo
                val ismEword: Int = kana.ismendword
                val khabarSword: Int = kana.khabarstartwordno
                val habarEword: Int = kana.khabarendwordno
                val tb: TextBorderSpan = TextBorderSpan()
                val iscolored: Boolean = false
                val spannable: SpannableString  = SpannableString (quranverses)
                var harfspannble: SpannableString
                var harfismspannable: SpannableString
                var khabarofversespannable: SpannableString
                val connected: Int = kana.khabarstart - kana.indexend
                if (a) {
                    val isismkhabarconnected: Int = 0
                    harfspannble = SpannableString (harfofverse)
                    harfismspannable = SpannableString (ismofverse)
                    khabarofversespannable = SpannableString (khabarofverse)
                    harfspannble.setSpan(
                        harfkana,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    harfismspannable.setSpan(
                        kanaism,
                        0,
                        ismofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    khabarofversespannable.setSpan(
                        kanakhbar,
                        0,
                        khabarofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    if (kana.ismkanastart > kana.khabarstart) {
                        val charSequence: CharSequence = TextUtils.concat(
                            harfspannble,
                            " ",
                            khabarofversespannable,
                            " ",
                            harfismspannable
                        )
                        kanaarray.add(SpannableString .valueOf(charSequence))
                    } else {
                        val charSequence: CharSequence = TextUtils.concat(
                            harfspannble,
                            " ",
                            harfismspannable,
                            " ",
                            khabarofversespannable
                        )
                        kanaarray.add(SpannableString .valueOf(charSequence))
                    }
                    val sb: StringBuilder=StringBuilder()
                    val ismorkhabarsb: StringBuilder=StringBuilder()
                    if (isismkhabarconnected == 1) {
                        val wordfrom: Int = kana.harfwordno
                        val wordto: Int = kana.khabarendwordno
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            wordfrom,
                            wordto
                        )
                        for (w: wbwentity? in list!!) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            sb.append(temp).append(" ")
                        }
                    } else {
                        val from: Int = kana.harfwordno
                        val ismstartword: Int = kana.ismwordo
                        val khabarstartword: Int = kana.khabarstartwordno
                        val khabarendword: Int = kana.khabarendwordno
                        val ismendword: Int = kana.ismendword
                        val wbwayah: List<wbwentity?>? = utils!!.getwbwQuranBySurahAyah(
                            corpusSurahWord!![0].surah, corpusSurahWord!![0].ayah
                        )
                        for (w: wbwentity? in wbwayah!!) {
                            StringBuilder ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            if (w.wordno == harfword) {
                                sb.append(temp.append(" "))
                            } else if (w.wordno in ismSword..ismEword) {
                                sb.append(temp).append(" ")
                            } else if (w.wordno in khabarSword..habarEword) {
                                ismorkhabarsb.append(temp).append(" ")
                            }
                        }
                        sb.append("... ")
                        sb.append(ismorkhabarsb)
                        kanaarray.add(SpannableString .valueOf(sb.toString()))
                    }
                    //  CharSequence first = TextUtils.concat(harfspannble," ",shartofverse);
                } else if (d) {
                    if (dark) {
                        harfkana = ForegroundColorSpan(Constant.GOLD)
                        kanaism = ForegroundColorSpan(Constant.ORANGE400)
                        kanakhbar = ForegroundColorSpan(Color.CYAN)
                    } else {
                        harfkana = ForegroundColorSpan(Constant.FORESTGREEN)
                        kanaism = ForegroundColorSpan(Constant.KASHMIRIGREEN)
                        kanakhbar = ForegroundColorSpan(Constant.WHOTPINK)
                    }
                    harfspannble = SpannableString (harfofverse)
                    khabarofversespannable = SpannableString (khabarofverse)
                    harfspannble.setSpan(
                        harfkana,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    khabarofversespannable.setSpan(
                        kanakhbar,
                        0,
                        khabarofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequence: CharSequence =
                        TextUtils.concat(harfspannble, " ", khabarofversespannable)
                    kanaarray.add(SpannableString .valueOf(charSequence))
                    val sb: StringBuilder=StringBuilder()
                    val wordfrom: Int = kana.harfwordno
                    var wordto: Int
                    val split: Array<String> =
                        khabarofverse.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                    wordto = if (split.size == 1) {
                        kana.khabarstartwordno
                    } else {
                        kana.khabarendwordno
                    }
                    val isconnected: Int = kana.khabarstartwordno - kana.harfwordno
                    if (isconnected == 1) {
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            wordfrom,
                            wordto
                        )
                        for (w: wbwentity? in list!!) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            sb.append(temp).append(" ")
                        }
                        kanaarray.add(SpannableString .valueOf(sb.toString()))
                    } else {
                        val wordfroms: Int = kana.harfwordno
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            wordfrom,
                            wordfroms
                        )
                        val from: Int = kana.khabarstartwordno
                        var to: Int = kana.khabarendwordno
                        if (to == 0) {
                            to = from
                        }
                        if ((whichwbw == "en")) {
                            sb.append(list!![0]!!.en).append(".......")
                        } else if ((whichwbw == "ur")) {
                            sb.append(list!![0]!!.ur).append(".......")
                        } else if ((whichwbw == "bn")) {
                            sb.append(list!![0]!!.bn).append(".......")
                        } else if ((whichwbw == "id")) {
                            sb.append(list!![0]!!.id  ).append(".......")
                        }
                        //    sb.append(list).append("----");
                        val lists: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            from,
                            to
                        )
                        for (w: wbwentity?  in list!! ) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            sb.append(temp).append(" ")
                        }
                        kanaarray.add(SpannableString .valueOf(sb.toString()))
                    }
                } else if (b) {
                    if (dark) {
                        harfkana = ForegroundColorSpan(Constant.GOLD)
                        kanaism = ForegroundColorSpan(Constant.ORANGE400)
                        kanakhbar = ForegroundColorSpan(Color.CYAN)
                    } else {
                        harfkana = ForegroundColorSpan(Constant.FORESTGREEN)
                        kanaism = ForegroundColorSpan(Constant.KASHMIRIGREEN)
                        kanakhbar = ForegroundColorSpan(Constant.WHOTPINK)
                    }
                    harfspannble = SpannableString (harfofverse)
                    harfismspannable = SpannableString (ismofverse)
                    harfspannble.setSpan(
                        harfkana,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    harfismspannable.setSpan(
                        kanaism,
                        0,
                        ismofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequences: CharSequence =
                        TextUtils.concat(harfspannble, " $harfismspannable")
                    kanaarray.add(SpannableString .valueOf(charSequences))
                    //    kanaarray.add(SpannableString .valueOf(charSequence));
                    val sb: StringBuilder=StringBuilder()
                    val ismorkhabarsb: StringBuilder=StringBuilder()
                    // SpannableString  trstr = getFragmentTranslations(quranverses, sb, charSequence, false);
                    // kanaarray.add(trstr);
                    val ismconnected: Int = kana.ismkanastart - kana.indexend
                    val wordfrom: Int = kana.harfwordno
                    val wordto: Int = kana.ismwordo
                    if (ismconnected == 1) {
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            wordfrom,
                            wordto
                        )
                        for (w: wbwentity?  in list!!) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            sb.append(temp).append(" ")
                        }
                        kanaarray.add(SpannableString .valueOf(sb.toString()))
                    } else {
                        val wordfroms: Int = kana.harfwordno
                        //  List<wbwentity?>? list = utils.getwbwQuranbTranslation(corpusSurahWord.get(0).surah, corpusSurahWord.get(0).ayah, wordfroms, wordfroms);
                        val wbwayah: List<wbwentity?>? = utils!!.getwbwQuranBySurahAyah(
                            corpusSurahWord!![0].surah, corpusSurahWord!![0].ayah
                        )
                        for (w: wbwentity? in wbwayah!!) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            if (w.wordno == harfword) {
                                sb.append(temp).append(" ")
                            } else if (w.wordno in ismSword..ismEword) {
                                ismorkhabarsb.append(temp).append(" ")
                            }
                        }
                        sb.append(".....")
                        sb.append(ismorkhabarsb)
                        kanaarray.add(SpannableString .valueOf(sb.toString()))
                        //    List<wbwentity?>? harf = utils.getwbwQuranbTranslation(corpusSurahWord.get(0).surah, corpusSurahWord.get(0).ayah, harfword, harfword);
                        //   List<wbwentity?>? ism = utils.getwbwQuranbTranslation(corpusSurahWord.get(0).surah, corpusSurahWord.get(0).ayah, ismfrom, ismto);
                    }
                } else if (c) {
                    harfspannble = SpannableString (harfofverse)
                    harfspannble.setSpan(
                        harfkana,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequence: CharSequence = TextUtils.concat(harfspannble)
                    kanaarray.add(SpannableString .valueOf(charSequence))
                    val wordfroms: Int = kana.harfwordno
                    val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                        corpusSurahWord!![0].surah,
                        corpusSurahWord!![0].ayah,
                        wordfroms,
                        wordfroms
                    )
                    val sb: StringBuffer = StringBuffer()
                    when (whichwbw) {
                        "en" -> sb.append(list?.get(0)!!.en).append(".......")
                        "ur" -> sb.append(list?.get(0)!!.ur).append(".......")
                        "bn" -> sb.append(list?.get(0)!!.bn).append(".......")
                        "id" -> sb.append(list?.get(0)!!.id    ).append(".......")
                    }
                    kanaarray.add(SpannableString .valueOf(sb))
                }
                // kanaarray.add(spannable);
            }
        }
    }

    private fun setMausoof(mausoofsifaarray: MutableList<SpannableString >) {
        val sifabySurahAyah: List<SifaEntity?>? =
            utils!!.getSifabySurahAyah(chapterid, ayanumber)
        var mausuufo: ArrayList<wbwentity?>?
        var mausuuft: ArrayList<wbwentity?>?
        val prefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val preferences: String? = prefs.getString("theme", "dark")
        if (sifabySurahAyah != null) {
            for (shartEntity: SifaEntity? in sifabySurahAyah) {
                if (dark) {
                    Constant.sifaspansDark = BackgroundColorSpan(Constant.WBURNTUMBER)
                } else {
                    Constant.sifaspansDark = BackgroundColorSpan(Constant.CYANLIGHTEST)
                }

                //   sifaspansDark = new BackgroundColorSpan(WBURNTUMBER);
                val quranverses: String? = corpusSurahWord!![0].qurantext
                val spannable: SpannableString  = SpannableString (quranverses)
                try {
                    shartEntity?.let {
                        spannable.setSpan(
                            Constant.sifaspansDark,
                            it.startindex,
                            shartEntity.endindex,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                } catch (e: IndexOutOfBoundsException) {
                    if (shartEntity != null) {
                        println(
                            shartEntity.surah
                                .toString() + "  " + shartEntity.ayah + "  " + quranverses
                        )
                    }
                }
                val sequence: CharSequence? =
                    shartEntity?.let { spannable.subSequence(it.startindex, shartEntity.endindex) }
                mausoofsifaarray.add(sequence as SpannableString )
                val wordfrom: Int = shartEntity.wordno - 1
                val wordto: Int = shartEntity.wordno
                val strings: Array<String> =
                    sequence.toString().split("\\s".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                val ssb: StringBuilder=StringBuilder()
                val sb: StringBuilder=StringBuilder()
                val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                    corpusSurahWord!![0].surah,
                    corpusSurahWord!![0].ayah,
                    wordfrom,
                    wordto
                )
                for (w: wbwentity?  in list!!) {
                    String ()
                    var temp: StringBuilder = getSelectedTranslation(w!!)
                    ssb.append(temp).append(" ")
                }
                mausoofsifaarray.add(SpannableString .valueOf(ssb.toString()))
                //   SpannableString  trstr = getFragmentTranslations(quranverses, sb, sequence, true);
                //   mausoofsifaarray.add(trstr);
            }
        }
    }

    private fun setMudhaf(mudhafarray: MutableList<SpannableString >) {
        //   ArrayList<MudhafEntity> mudhafSurahAyah = utils.getMudhafSurahAyah(chapterid, ayanumber);
        val mudhafSurahAyah: List<NewMudhafEntity?>? =
            utils!!.getMudhafSurahAyahNew(chapterid, ayanumber)
        val index: Int = 0
        val prefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val preferences: String? = prefs.getString("theme", "dark")
        for (mudhafEntity: NewMudhafEntity? in mudhafSurahAyah!!) {
            if (dark) {
                Constant.mudhafspansDark = BackgroundColorSpan(Constant.MIDNIGHTBLUE)
            } else {
                Constant.mudhafspansDark = BackgroundColorSpan(Constant.GREENYELLOW)
            }
            val quranverses: String? = corpusSurahWord!![0].qurantext
            val spannable: SpannableString  = SpannableString (quranverses)
            if (mudhafEntity != null) {
                spannable.setSpan(
                    Constant.mudhafspansDark,
                    mudhafEntity.startindex,
                    mudhafEntity.endindex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            val sequence: CharSequence? =
                mudhafEntity?.let { spannable.subSequence(it.startindex, mudhafEntity.endindex) }
            mudhafarray.add(sequence as SpannableString )
            val sb: StringBuilder=StringBuilder()
            val wordfrom: Int = mudhafEntity.wordfrom
            val wordto: Int = mudhafEntity.wordto
            val strings: Array<String> =
                sequence.toString().split("\\s".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            val ssb: StringBuilder=StringBuilder()
            if (strings.size == 2) {
                val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                    corpusSurahWord!![0].surah,
                    corpusSurahWord!![0].ayah,
                    wordfrom,
                    wordto
                )
                for (w: wbwentity?  in list!!) {
                    String ()
                    var temp: StringBuilder = getSelectedTranslation(w!!)
                    ssb.append(temp).append(" ")
                }
                mudhafarray.add(SpannableString .valueOf(ssb.toString()))
            } else {
                val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                    corpusSurahWord!![0].surah,
                    corpusSurahWord!![0].ayah,
                    wordto,
                    wordto
                )
                if ((whichwbw == "en")) {
                    sb.append(list?.get(0)!!.en).append(".......")
                } else if ((whichwbw == "ur")) {
                    sb.append(list?.get(0)!!.ur).append(".......")
                } else if ((whichwbw == "bn")) {
                    sb.append(list?.get(0)!!.bn).append(".......")
                } else if ((whichwbw == "id")) {
                    sb.append(list?.get(0)!!.id).append(".......")
                }
                mudhafarray.add(SpannableString .valueOf(sb))
            }
            //   SpannableString  trstr = getFragmentTranslations(quranverses, sb, sequence, true);
            //  mudhafarray.add(trstr);
            // mudhafarray.add(spannable);
        }
    }

    private fun newSetShart(shartarray: MutableList<SpannableString >) {
        val quranverses: String? = corpusSurahWord!![0].qurantext
        val shart: List<NewShartEntity?>? = utils!!.getShartSurahAyahNew(chapterid, ayanumber)
        val kanaSurahAyahnew: List<NewNasbEntity?>? =
            utils!!.getHarfNasbIndSurahAyahSnew(chapterid, ayanumber)
        val prefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val preferences: String? = prefs.getString("theme", "dark")
        // String quranverses = corpusSurahWord.get(0).qurantext;
        val jumlashart: String = "جملة شرطية"
        var sb: StringBuilder
        val sbjawab: StringBuilder=StringBuilder()
        val sharedPreferences: SharedPreferences =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                QuranGrammarApplication.context!!
            )
        val quranFont: String? = sharedPreferences.getString("quranFont", "kitab.ttf")
        val mequran: Typeface =
            Typeface.createFromAsset(QuranGrammarApplication.context!!.assets, quranFont)
        if (shart != null) {
            for (shartEntity: NewShartEntity? in shart) {
                var harfofverse: String
                var shartofverse: String
                var jawabofverrse: String
                sb = StringBuilder ()
                val indexstart: Int = shartEntity!!.indexstart
                val indexend: Int = shartEntity.indexend
                val shartindexstart: Int = shartEntity.shartindexstart
                val shartindexend: Int = shartEntity.shartindexend
                val jawabstart: Int = shartEntity.jawabshartindexstart
                val jawabend: Int = shartEntity.jawabshartindexend
                val harfword: Int = shartEntity.harfwordno
                val shartSword: Int = shartEntity.shartstatwordno
                val shartEword: Int = shartEntity.shartendwordno
                val jawbSword: Int = shartEntity.jawabstartwordno
                val jawabEword: Int = shartEntity.jawabendwordno
                harfofverse = quranverses!!.substring(indexstart, indexend)
                shartofverse = quranverses.substring(shartindexstart, shartindexend)
                jawabofverrse = quranverses.substring(jawabstart, jawabend)
                val isharfb: Boolean = indexstart >= 0 && indexend > 0
                val isshart: Boolean = shartindexstart >= 0 && shartindexend > 0
                val isjawab: Boolean = jawabstart >= 0 && jawabend > 0
                val a: Boolean = isharfb && isshart && isjawab
                val b: Boolean = isharfb && isshart
                val c: Boolean = isharfb
                var harfspannble: SpannableString
                var shartspoannable: SpannableString
                var jawabshartspannable: SpannableString
                val spanhash: Map<String?, ForegroundColorSpan> =
                    CorpusUtilityorig.stringForegroundColorSpanMap
                if (dark) {
                    Constant.harfshartspanDark = ForegroundColorSpan(Constant.GOLD)
                    Constant.shartspanDark = ForegroundColorSpan(Constant.ORANGE400)
                    Constant.jawabshartspanDark = ForegroundColorSpan(Color.CYAN)
                } else {
                    Constant.harfshartspanDark = ForegroundColorSpan(Constant.FORESTGREEN)
                    Constant.shartspanDark = ForegroundColorSpan(Constant.KASHMIRIGREEN)
                    Constant.jawabshartspanDark = ForegroundColorSpan(Constant.WHOTPINK)
                }
                if (a) {
                    harfspannble = SpannableString (harfofverse)
                    shartspoannable = SpannableString (shartofverse)
                    jawabshartspannable = SpannableString (jawabofverrse)
                    if (dark) {
                        //    harfshartspanDark = new ForegroundColorSpan(MIDNIGHTBLUE);
                        Constant.shartspanDark = ForegroundColorSpan(Constant.ORANGE400)
                        Constant.jawabshartspanDark = ForegroundColorSpan(Color.CYAN)
                    } else {
                        //   harfshartspanDark = new ForegroundColorSpan(FORESTGREEN);
                        Constant.shartspanDark = ForegroundColorSpan(Constant.GREENDARK)
                        Constant.jawabshartspanDark = ForegroundColorSpan(Constant.WHOTPINK)
                    }
                    harfspannble.setSpan(
                        Constant.harfshartspanDark,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    shartspoannable.setSpan(
                        Constant.shartspanDark,
                        0,
                        shartofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    jawabshartspannable.setSpan(
                        Constant.jawabshartspanDark,
                        0,
                        jawabofverrse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequence: CharSequence =
                        TextUtils.concat(harfspannble, " ", shartspoannable, " ", jawabshartspannable)
                    val charsequenceshart: CharSequence =
                        TextUtils.concat(harfspannble, " ", shartspoannable)
                    val charsequencejawabshart: CharSequence = TextUtils.concat(jawabshartspannable)
                    val trstr: SpannableString  =
                        getFragmentTranslations(quranverses, charSequence)
                    shartarray.add(SpannableString .valueOf(charSequence))
                    val connected: Int = jawabstart - shartindexend
                    val trstr1: SpannableString ? = null
                    val trstr2: SpannableString ? = null
                    if (connected == 1) {
                        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah, harfword, jawabEword
                        )
                        for (w: wbwentity?  in list!!) {
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            sb.append(temp).append(" ")
                        }
                        //    trstr1 = getFragmentTranslations(quranverses, sb, charSequence, false);
                        shartarray.add(SpannableString .valueOf(sb.toString()))
                    } else {
                        val wbwayah: List<wbwentity?>? = utils!!.getwbwQuranBySurahAyah(
                            corpusSurahWord!![0].surah, corpusSurahWord!![0].ayah
                        )
                        if (wbwayah != null) {
                            for (w: wbwentity? in wbwayah) {
                                var temp: StringBuilder = getSelectedTranslation(w!!)
                                if (w.wordno == harfword) {
                                    sb.append(temp).append(" ")
                                } else if (w.wordno in shartSword..shartEword) {
                                    sb.append(temp).append(" ")
                                } else if (w.wordno in jawbSword..jawabEword) {
                                    //     sb. append("... ");
                                    sbjawab.append(temp).append(" ")
                                }
                            }
                        }
                        sb.append(".....")
                        sb.append(sbjawab)
                        shartarray.add(SpannableString .valueOf(sb.toString()))
                    }
                } else if (b) {
                    harfspannble = SpannableString (harfofverse)
                    shartspoannable = SpannableString (shartofverse)
                    harfspannble.setSpan(
                        Constant.harfshartspanDark,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    shartspoannable.setSpan(
                        Constant.shartspanDark,
                        0,
                        shartofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequence: CharSequence =
                        TextUtils.concat(harfspannble, " ", shartspoannable)
                    val trstr: SpannableString  =
                        getFragmentTranslations(quranverses, charSequence)
                    shartarray.add(SpannableString .valueOf(charSequence))
                    //    shartarray.add(trstr);
                    if ((shartindexstart - indexend) == 1) {
                        val harfnshart: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
                            corpusSurahWord!![0].surah,
                            corpusSurahWord!![0].ayah,
                            harfword,
                            shartEword
                        )
                        for (w: wbwentity? in harfnshart!!) {
                            String ()
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            getSelectedTranslation(w)
                            sb.append(temp).append(" ")
                        }
                    } else {
                        val wbwayah: List<wbwentity?>? = utils!!.getwbwQuranBySurahAyah(
                            corpusSurahWord!![0].surah, corpusSurahWord!![0].ayah
                        )
                        for (w: wbwentity? in wbwayah!!) {
                            var temp: StringBuilder = getSelectedTranslation(w!!)
                            if (w.wordno == harfword) {
                                sb.append(temp).append(" ")
                            } else if (w.wordno in shartSword..shartEword) {
                                sb.append(temp).append(" ")
                            }
                        }
                        sb.append(".....")
                        sb.append(sbjawab)
                        //   shartarray.add(SpannableString .valueOf(sb.toString()));


                        /*


                        List<wbwentity?>? harf = utils.getwbwQuranbTranslation(corpusSurahWord.get(0).surah, corpusSurahWord.get(0).ayah, harfword, harfword);
                        List<wbwentity?>? shartlist = utils.getwbwQuranbTranslation(corpusSurahWord.get(0).surah, corpusSurahWord.get(0).ayah, shartSword, shartEword);
                        for (wbwentity tr : harf) {

                            tr.en;
                            sb.append(tr.en).append(" ");


                        }
                        sb.append("....");
                        for (wbwentity tr : shartlist) {

                            tr.en;
                            sb.append(tr.en).append(" ");


                        }
     */
                    }
                    shartarray.add(SpannableString .valueOf(sb.toString()))
                } else if (c) {
                    harfspannble = SpannableString (harfofverse)
                    harfspannble.setSpan(
                        Constant.harfshartspanDark,
                        0,
                        harfofverse.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val charSequence: CharSequence = TextUtils.concat(harfspannble)
                    val trstr: SpannableString  =
                        getFragmentTranslations(quranverses, charSequence)
                    shartarray.add(SpannableString .valueOf(charSequence))
                    shartarray.add(trstr)
                }
            }
        }
    }


    private fun getFragmentTranslations(
        quranverses: String,
        charSequence: CharSequence
    ): SpannableString {
        //get the string firs wordno and last wordno

        val sb = StringBuilder()
        var firstwordindex: Int = 0
        var lastwordindex: Int = 0
        val versesSplitIntoWords: SplitQuranVerses = SplitQuranVerses()
        val words: ArrayList<Word> = versesSplitIntoWords.splitSingleVerse(quranverses)
        val trim: String = charSequence.toString().trim { it <= ' ' }
        val strings: Array<String> =
            trim.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val length: Int = strings.size
        val firstword: String = strings[0]
        val lastword: String = strings[length - 1]
        for (w: Word in words) {
            val wordsAr: String? = w.wordsAr
            if ((w.wordsAr == firstword)) {
                firstwordindex = w.wordno
            }
            if ((wordsAr == lastword)) {
                lastwordindex = w.wordno
                break
            }
        }
        //if the agove is false incase of the punctutaion marks,strip the punctuation and reloop
        if (lastwordindex == 0) {
            for (ww: Word in words) {
                var wwordsAr: String? = ww.wordsAr
                val sala: Int = wwordsAr!!.indexOf(ArabicLiterals.SALA)
                val qala: Int = wwordsAr.indexOf(ArabicLiterals.QALA)
                val smalllam: Int = wwordsAr.indexOf(ArabicLiterals.SMALLLAM)
                val smalljeem: Int = wwordsAr.indexOf(ArabicLiterals.SMALLJEEM) //small high geem
                val b: Boolean =
                    (sala != -1) || (qala != -1) || (smalljeem != -1) || (smalllam != -1)
                if (b) {
                    wwordsAr = wwordsAr.substring(0, wwordsAr.length - 1)
                }
                if ((wwordsAr == firstword)) {
                    firstwordindex = ww.wordno
                }
                if ((wwordsAr == lastword)) {
                    lastwordindex = ww.wordno
                    break
                }
            }
        }
        val list: List<wbwentity?>? = utils!!.getwbwQuranbTranslation(
            corpusSurahWord!![0].surah,
            corpusSurahWord!![0].ayah,
            firstwordindex,
            lastwordindex
        )
        val translation: String = ""
        for (tr: wbwentity? in list!!) {
            if (tr != null) {
                getSelectedTranslation(tr)
            }
            if (tr != null) {
                sb.append(tr.en).append(" ")
            }
        }
        return SpannableString(sb)
    }

    val kana: List<SpannableString >
        get() {
            val kanaarray: MutableList<SpannableString > = ArrayList()
            newsetKana(kanaarray)
            return kanaarray
        }
}