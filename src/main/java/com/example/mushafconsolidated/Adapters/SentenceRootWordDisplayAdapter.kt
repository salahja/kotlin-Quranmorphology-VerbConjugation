package com.example.mushafconsolidated.Adapters

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.TextUtils.TruncateAt
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO
import com.example.mushafconsolidated.R

import com.google.android.material.chip.Chip
import org.sj.conjugator.interfaces.OnItemClickListener

class SentenceRootWordDisplayAdapter(private var context: Context) :
    RecyclerView.Adapter<SentenceRootWordDisplayAdapter.ItemViewAdapter>() {
    var mItemClickListener: OnItemClickListener? = null
    var ismalatitle = "( الآلَة:)"
    var alaheader = "اِسْم الآلَة"
    var zarfheader = "اِسْم الْظَرفْ"
    var rootcolor = 0
    var weaknesscolor = 0
    var wazancolor = 0
    var ismujarrad = false
    var ismazeed = false
    var isparticple = false
    var isconjugation = false
    var isSarfSagheerMazeed = false
    private val worddetails: HashMap<String, SpannableStringBuilder>? = null
    private val vbdetail: HashMap<String, String>? = null
    private var corpusexpand: ArrayList<NewCorpusExpandWbwPOJO>? = null
    private val isSarfSagheerThulahi = false
    private val isverbconjugation = false
    private val particples = false
    private val ismfaelmafool: ArrayList<ArrayList<*>>? = null
    private val isnoun = false
    private var spannalbeShart: SpannableStringBuilder? = null
    private var spannableHarf: SpannableStringBuilder? = null
    private var worddetailsmap: HashMap<Int, HashMap<String, SpannableStringBuilder?>>? = null
    private var verbdetailsmap: HashMap<Int, HashMap<String, String>?>? = null
    private var spannable: SpannableStringBuilder? = null

    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();
    private val sarfsagheer: ArrayList<ArrayList<*>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        val view: View
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sentence_word_details, parent, false)
        return ItemViewAdapter(view)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val quranFont = sharedPreferences.getString("quranFont", "kitab.ttf")
        val appliedFont = Typeface.createFromAsset(
            context.assets, quranFont
        )
        val theme = sharedPreferences.getString("themePref", "dark")
        Log.d(TAG, "onBindViewHolder: called")
        if (theme == "dark") {
            rootcolor = Constant.BCYAN
            weaknesscolor = Constant.BYELLOW
            wazancolor = Constant.BBLUE
        } else {
            rootcolor = Constant.WBURNTUMBER
            weaknesscolor = Constant.GOLD
            wazancolor = Constant.WMIDNIHTBLUE
        }
        val wordno = worddetailsmap!![position + 1]!!["wordno"]
        val wordposition = wordno.toString().toInt()
        if (verbdetailsmap!![wordposition] != null) {
            ismujarrad = verbdetailsmap!![wordposition]!!["wazan"] != "null"
            ismazeed = verbdetailsmap!![wordposition]!!["form"] != "null"
        }
        isparticple = worddetailsmap!![wordposition]!!["PART"] != null
        isconjugation = ismujarrad || ismazeed || isparticple
        //  holder.wordView.setText(worddetailsmap.get(wordposition).get("word"));
        val word = worddetailsmap!![wordposition]!!["word"].toString()
        holder.wordView.text = word
        println(worddetailsmap!![wordposition]!!["word"])
        holder.lemma.text = LEMMA + worddetailsmap!![wordposition]!!["lemma"]
        holder.wdetailstv.setText(
            worddetailsmap!![wordposition]!!["worddetails"],
            TextView.BufferType.SPANNABLE
        )
        if (worddetailsmap!![wordposition]!!["root"] != null) {
            holder.rootView.text = ROOTWORDSTRING + worddetailsmap!![wordposition]!!["root"]
        }
        if (worddetailsmap!![wordposition]!!["wordtranslation"] != null) {
            holder.translationView.text = worddetailsmap!![wordposition]!!["wordtranslation"]
        }
        //   holder.wordView.setText(worddetailsmap.get(wordposition).get("word"));
        if (worddetailsmap!![wordposition]!!["PRON"] != null) {
            holder.pronoundetails.text = worddetailsmap!![wordposition]!!["PRON"]
        }
        if (isconjugation) {
            holder.verbconjugationbtn.visibility = View.VISIBLE
            holder.wordoccurancebtn.visibility = View.VISIBLE
        } else if (worddetailsmap!![wordposition]!!["noun"] != null) {
            holder.noun.text = worddetailsmap!![wordposition]!!["noun"]
            holder.wordoccurancebtn.visibility = View.VISIBLE
            holder.verbconjugationbtn.visibility = View.GONE
        } else {
            holder.verbconjugationbtn.visibility = View.GONE
            holder.wordoccurancebtn.visibility = View.GONE
        }
        val wordd: Int? = null
        val verb: Int? = null
        var verbwordno: String? = ""
        try {
            verbwordno = verbdetailsmap!![wordposition]!!["wordno"]
        } catch (e: NullPointerException) {
        }
        //   wordd = Integer.valueOf(verbwordno);
        //    verb = Integer.valueOf(String.valueOf(worddetailswordno));
        val vb = StringBuilder()
        vb.append("V-")
        try {
            if (worddetailsmap!![wordposition]!!["thulathi"] != null) {
                vb.append(worddetailsmap!![wordposition]!!["thulathi"])
            }
        } catch (e: NullPointerException) {
        }
        try {
            if (worddetailsmap!![wordposition]!!["png"] != null) {
                vb.append(worddetailsmap!![wordposition]!!["png"])
            }
        } catch (e: NullPointerException) {
        }
        try {
            if (worddetailsmap!![wordposition]!!["tense"] != null) {
                vb.append(worddetailsmap!![wordposition]!!["tense"])
            }
        } catch (e: NullPointerException) {
        }
        try {
            if (worddetailsmap!![wordposition]!!["voice"] != null) {
                vb.append(worddetailsmap!![wordposition]!!["voice"])
            }
        } catch (e: NullPointerException) {
        }
        try {
            if (worddetailsmap!![wordposition]!!["mood"] != null) {
                vb.append(worddetailsmap!![wordposition]!!["mood"])
            }
        } catch (e: NullPointerException) {
        }
        if (vb.length > 2) {
            holder.verbdetails.visibility = View.VISIBLE
            holder.verbdetails.text = vb.toString()
        }
        //  wordbdetail.put("surahid", SpannableStringBuilder.valueOf(String.valueOf(corpusSurahWord.get(0).getSurah())));
        //   wordbdetail.put("ayahid", SpannableStringBuilder.valueOf(String.valueOf(corpusSurahWord.get(0).getAyah())));
        //   wordbdetail.put("wordno", SpannableStringBuilder.valueOf(String.valueOf(corpusSurahWord.get(0).getWordno())));
        holder.referenceView.text =
            worddetailsmap!![wordposition]!!["surahid"].toString() + ":" + worddetailsmap!![wordposition]!!["ayahid"] + ":" + worddetailsmap!![wordposition]!!["wordno"]
        val sarfsagheerlen = 0
        val length = 0
        val toArray = arrayOfNulls<Any>(0)
        val mazeedArray = arrayOfNulls<Any>(0)
        holder.quranverseShart.ellipsize = TruncateAt.MARQUEE
        holder.spannableverse.ellipsize = TruncateAt.MARQUEE
        if (null != spannalbeShart) {
            val spans = spannalbeShart!!.getSpans(
                0, spannalbeShart!!.length,
                Any::class.java
            )
            if (spans.size > 0) {
                holder.quranverseShart.text = spannalbeShart
                holder.quranverseShart.setTypeface(appliedFont)
            }
        }
        if (null != spannableHarf) {
            val spans = spannableHarf!!.getSpans(
                0, spannableHarf!!.length,
                Any::class.java
            )
            if (spans.size > 0) {
                holder.spannableverse.text = spannableHarf
                holder.spannableverse.setTypeface(appliedFont)
            }
        }
        if (null != spannable) {
            val spans = spannable!!.getSpans(
                0, spannable!!.length,
                Any::class.java
            )
            if (spans.size > 0) {
                holder.spannableverse.setText(spannable, TextView.BufferType.SPANNABLE)
                //   holder.spannableverse.setText(spannable);
                holder.spannableverse.setTypeface(appliedFont)
            }
        }
    }

    override fun getItemCount(): Int {
        return worddetailsmap!!.size
    }

    fun setRootWordsAndMeanings(
        sencorpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>?,
        spannableShart: SpannableStringBuilder?,
        spannableHarf: SpannableStringBuilder?,
        spannable: SpannableStringBuilder?,
        worddetailsmap: HashMap<Int, HashMap<String, SpannableStringBuilder?>>?,
        verbdetailsmap: HashMap<Int, HashMap<String, String>?>?,
        activity: FragmentActivity
    ) {
        this.worddetailsmap = worddetailsmap
        this.verbdetailsmap = verbdetailsmap
        context = activity
        spannalbeShart = spannableShart
        this.spannableHarf = spannableHarf
        corpusexpand = sencorpusSurahWord
        this.spannable = spannable
    }

    override fun getItemId(position: Int): Long {
        val wordno1 = worddetailsmap!![position]!!["wordno"]
        val map = worddetailsmap!![position]!!
        val wordno = map["wordno"]
        //   return SpannableStringBuilder;
        return wordno1.toString().toInt().toLong()
    }

    inner class ItemViewAdapter(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val amr: TextView
        val nahiamr: TextView
        val ismfail: TextView
        val mumaroof: TextView
        val mamaroof: TextView
        val ismala: TextView
        val ismmafool: TextView
        val mumajhool: TextView
        val mamajhool: TextView
        val ismzarf: TextView

        //ISMFAEL
        val isone: TextView
        val istwo: TextView
        val isthree: TextView
        val isfour: TextView
        val isfive: TextView
        val issix: TextView
        val isseven: TextView
        val iseight: TextView
        val isnine: TextView
        val ismfemone: TextView
        val ismfemtwo: TextView
        val ismfemthree: TextView
        val ismfemfour: TextView
        val ismfemfive: TextView
        val ismfemsix: TextView
        val ismfemseven: TextView
        val ismfemeight: TextView
        val ismfemnine: TextView
        val imafone: TextView
        val imaftwo: TextView
        val imafthree: TextView
        val imaffour: TextView
        val imaffive: TextView
        val imafsix: TextView
        val imafseven: TextView
        val imafeight: TextView
        val imafnine: TextView
        val imafoolfemone: TextView
        val imafoolfemtwo: TextView
        val imafoolfemthree: TextView
        val imafoolfemfour: TextView
        val imafoolfemfive: TextView
        val imafoolfemsix: TextView
        val imafoolfemseven: TextView
        val imafoolfemeight: TextView
        val imafoolfemnine: TextView
        val mifalone: TextView
        val mifaltwo: TextView
        val mifalthree: TextView
        val mifalfour: TextView
        val mifalfive: TextView
        val mifalsix: TextView
        val mifalseven: TextView
        val mifaleight: TextView
        val mifalnine: TextView
        val mifalatunone: TextView
        val mifalatuntwo: TextView
        val mifalatunthree: TextView
        val mifalatunfour: TextView
        val mifalatunfive: TextView
        val mifalatunsix: TextView
        val mifalatunseven: TextView
        val mifalatuneight: TextView
        val mifalatunnine: TextView
        val mifaalone: TextView
        val mifaaltwo: TextView
        val mifaalthree: TextView
        val mifaalfour: TextView
        val mifaalfive: TextView
        val mifaalsix: TextView
        val mifaalseven: TextView
        val mifaaleight: TextView
        val mifaalnine: TextView
        val mafalunone: TextView
        val mafaluntwo: TextView
        val mafalunthree: TextView
        val mafalunfour: TextView
        val mafalunfive: TextView
        val mafalunsix: TextView
        val mafalunseven: TextView
        val mafaluneight: TextView
        val mafalunnine: TextView
        val sin1: TextView
        val dual1: TextView
        val plu1: TextView
        val sin2: TextView
        val dual2: TextView
        val plu2: TextView
        val sin3: TextView
        val dual3: TextView
        val plu3: TextView
        val sin4: TextView
        val dual4: TextView
        val plu4: TextView
        val nom: TextView
        val acc: TextView
        val gen: TextView
        val nom1: TextView
        val acc1: TextView
        val gen1: TextView
        val nom2: TextView
        val acc2: TextView
        val gen2: TextView
        val nom3: TextView
        val acc3: TextView
        val gen3: TextView
        val referenceView: TextView
        val wdetailstv: TextView
        val lemma: TextView
        val verbdetails: TextView
        val noun: TextView
        val pronoundetails: TextView
        val translationView: TextView
        val rootView: TextView
        val quranverseShart: TextView
        val spannableverse: TextView
        val babname: TextView
        val rootword: TextView
        val wazan: TextView
        val ismzarfheader: TextView
        val ismalaheader: TextView
        val masdaro: TextView
        val masdart: TextView
        val babno: TextView
        val babdetails: TextView
        val weaknessname: TextView
        val weaknesstype: TextView
        val sheet: View
        var wordView: Chip
        var dismissview: ImageView
        var apmas: TextView
        var apfem: TextView
        var ppmas: TextView
        var ppfem: TextView
        var verbconjugationbtn: TextView
        var verbOccurancebtn: TextView
        var wordoccurancebtn: TextView

        init {
            spannableverse = view.findViewById(R.id.spannableverse)
            quranverseShart = view.findViewById(R.id.quranverseShart)
            verbconjugationbtn = view.findViewById(R.id.verbconjugationbtn)
            verbOccurancebtn = view.findViewById(R.id.verboccurance)
            wordoccurancebtn = view.findViewById(R.id.wordoccurance)
            babname = view.findViewById(R.id.babno)
            rootword = view.findViewById(R.id.weaknesstype)
            ismzarfheader = view.findViewById(R.id.ismzarfheader)
            pronoundetails = view.findViewById(R.id.pronoundetails)
            noun = view.findViewById(R.id.noundetails)
            sheet = view.findViewById(R.id.sheet)
            wdetailstv = view.findViewById(R.id.wordDetails)
            lemma = view.findViewById(R.id.lemma)
            verbdetails = view.findViewById(R.id.verbdetails)
            dismissview = view.findViewById(R.id.dismissView)
            referenceView = view.findViewById(R.id.referenceView)
            wordView = view.findViewById(R.id.wordView)
            translationView = view.findViewById(R.id.translationView)
            rootView = view.findViewById(R.id.rootView)
            //   if(!particples) {
            //      dismissview.setOnClickListener(this);
            //  }
            //     view.setOnClickListener(this); // current clickListerner
            ismalaheader = view.findViewById(R.id.ismalaheader)
            ismala = view.findViewById(R.id.ismaalatable)
            wazan = view.findViewById(R.id.wazan)
            ismfail = view.findViewById(R.id.ismfail)
            masdaro = view.findViewById(R.id.masdar)
            mumaroof = view.findViewById(R.id.mumaroof)
            mamaroof = view.findViewById(R.id.mamaroof)
            ismmafool = view.findViewById(R.id.ismmafool)
            masdart = view.findViewById(R.id.masdar2)
            mumajhool = view.findViewById(R.id.mumajhool)
            mamajhool = view.findViewById(R.id.mamajhool)
            amr = view.findViewById(R.id.amr)
            nahiamr = view.findViewById(R.id.nahiamr)
            babno = view.findViewById(R.id.babno)
            ismzarf = view.findViewById(R.id.zarftable)
            babdetails = view.findViewById(R.id.babno)
            weaknesstype = view.findViewById(R.id.weaknesstype)
            weaknessname = view.findViewById(R.id.weknessname)
            var listcollapse: ImageView
            spannableverse.setOnClickListener(this)
            //  view.setOnClickListener(this);
            wordView.setOnClickListener(this)
            verbconjugationbtn.setOnClickListener(this)
            wordoccurancebtn.setOnClickListener(this)
            //  verbOccurancebtn.setOnClickListener(this);
            wordoccurancebtn.setOnClickListener(this)
            sin4 = view.findViewById(R.id.singular4)
            dual4 = view.findViewById(R.id.dual4)
            plu4 = view.findViewById(R.id.plural4)
            //    }
            nom = view.findViewById(R.id.nominative)
            acc = view.findViewById(R.id.accusative)
            gen = view.findViewById(R.id.genitive)
            nom1 = view.findViewById(R.id.nominative1)
            acc1 = view.findViewById(R.id.accusative1)
            gen1 = view.findViewById(R.id.genitive1)
            nom2 = view.findViewById(R.id.nominative2)
            acc2 = view.findViewById(R.id.accusative2)
            gen2 = view.findViewById(R.id.genitive2)
            nom3 = view.findViewById(R.id.nominative3)
            acc3 = view.findViewById(R.id.accusative3)
            gen3 = view.findViewById(R.id.genitive3)
            sin1 = view.findViewById(R.id.singular1)
            dual1 = view.findViewById(R.id.dual1)
            plu1 = view.findViewById(R.id.plural1)
            sin2 = view.findViewById(R.id.singular2)
            dual2 = view.findViewById(R.id.dual2)
            plu2 = view.findViewById(R.id.plural2)
            sin3 = view.findViewById(R.id.singular3)
            dual3 = view.findViewById(R.id.dual3)
            plu3 = view.findViewById(R.id.plural3)
            apmas = view.findViewById(R.id.apmas)
            apfem = view.findViewById(R.id.apfem)
            ppmas = view.findViewById(R.id.ppmas)
            ppfem = view.findViewById(R.id.ppfem)
            ismfemone = view.findViewById(R.id.ismfemone)
            if (particples) {
                ismfemone.setText(R.string.faelmazi)
            }
            ismfemtwo = view.findViewById(R.id.ismfemtwo)
            ismfemthree = view.findViewById(R.id.ismfemthree)
            ismfemfour = view.findViewById(R.id.ismfemfour)
            ismfemfive = view.findViewById(R.id.ismfemfive)
            ismfemsix = view.findViewById(R.id.ismfemsix)
            ismfemseven = view.findViewById(R.id.ismfemseven)
            ismfemeight = view.findViewById(R.id.ismfemeight)
            ismfemnine = view.findViewById(R.id.ismfemnine)
            //
            isone = view.findViewById(R.id.isone)
            istwo = view.findViewById(R.id.istwo)
            isthree = view.findViewById(R.id.isthree)
            isfour = view.findViewById(R.id.isfour)
            isfive = view.findViewById(R.id.isfive)
            issix = view.findViewById(R.id.issix)
            isseven = view.findViewById(R.id.isseven)
            iseight = view.findViewById(R.id.iseight)
            isnine = view.findViewById(R.id.isnine)
            //ismmafoolmasculine
            imafone = view.findViewById(R.id.imafone)
            imaftwo = view.findViewById(R.id.imaftwo)
            imafthree = view.findViewById(R.id.imafthree)
            imaffour = view.findViewById(R.id.imaffour)
            imaffive = view.findViewById(R.id.imaffive)
            imafsix = view.findViewById(R.id.imafsix)
            imafseven = view.findViewById(R.id.imafseven)
            imafeight = view.findViewById(R.id.imafeight)
            imafnine = view.findViewById(R.id.imafnine)
            //ismmafoolfeb
            imafoolfemone = view.findViewById(R.id.imafoolfemone)
            imafoolfemtwo = view.findViewById(R.id.imafoolfemtwo)
            imafoolfemthree = view.findViewById(R.id.imafoolfemthree)
            imafoolfemfour = view.findViewById(R.id.imafoolfemfour)
            imafoolfemfive = view.findViewById(R.id.imafoolfemfive)
            imafoolfemsix = view.findViewById(R.id.imafoolfemsix)
            imafoolfemseven = view.findViewById(R.id.imafoolfemseven)
            imafoolfemeight = view.findViewById(R.id.imafoolfemeight)
            imafoolfemnine = view.findViewById(R.id.imafoolfemnine)
            mifalone = view.findViewById(R.id.mifalone)
            mifaltwo = view.findViewById(R.id.mifaltwo)
            mifalthree = view.findViewById(R.id.mifalthree)
            mifalfour = view.findViewById(R.id.mifalfour)
            mifalfive = view.findViewById(R.id.mifalfive)
            mifalsix = view.findViewById(R.id.mifalsix)
            mifalseven = view.findViewById(R.id.mifalseven)
            mifaleight = view.findViewById(R.id.mifaleight)
            mifalnine = view.findViewById(R.id.mifalnine)
            mifalatunone = view.findViewById(R.id.mifalatunone)
            mifalatuntwo = view.findViewById(R.id.mifalatuntwo)
            mifalatunthree = view.findViewById(R.id.mifalatunthree)
            mifalatunfour = view.findViewById(R.id.mifalatunfour)
            mifalatunfive = view.findViewById(R.id.mifalatunfive)
            mifalatunsix = view.findViewById(R.id.mifalatunsix)
            mifalatunseven = view.findViewById(R.id.mifalatunseven)
            mifalatuneight = view.findViewById(R.id.mifalatuneight)
            mifalatunnine = view.findViewById(R.id.mifalatunnine)
            mifaalone = view.findViewById(R.id.mifaalone)
            mifaaltwo = view.findViewById(R.id.mifaaltwo)
            mifaalthree = view.findViewById(R.id.mifaalthree)
            mifaalfour = view.findViewById(R.id.mifaalfour)
            mifaalfive = view.findViewById(R.id.mifaalfive)
            mifaalsix = view.findViewById(R.id.mifaalsix)
            mifaalseven = view.findViewById(R.id.mifaalseven)
            mifaaleight = view.findViewById(R.id.mifaaleight)
            mifaalnine = view.findViewById(R.id.mifaalnine)
            mafalunone = view.findViewById(R.id.mafalunone)
            mafaluntwo = view.findViewById(R.id.mafaluntwo)
            mafalunthree = view.findViewById(R.id.mafalunthree)
            mafalunfour = view.findViewById(R.id.mafalunfour)
            mafalunfive = view.findViewById(R.id.mafalunfive)
            mafalunsix = view.findViewById(R.id.mafalunsix)
            mafalunseven = view.findViewById(R.id.mafalunseven)
            mafaluneight = view.findViewById(R.id.mafaluneight)
            mafalunnine = view.findViewById(R.id.mafalunnine)
        }

        override fun onClick(v: View) {
            mItemClickListener?.onItemClick(v, layoutPosition)
        }
    }

    companion object {
        private const val TAG = "VerseDisplayAdapter"
        private const val ROOTWORDSTRING = "Root Word:-"
        private const val LEMMA = "Lemma/Derivative-"
    }
}