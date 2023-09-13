package com.example.mushafconsolidated.Adaptersimport


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.utility.CorpusUtilityorig
import com.example.utility.QuranGrammarApplication
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import org.sj.conjugator.fragments.SarfSagheer


class newRootWordDisplayAdapter :RecyclerView.Adapter<newRootWordDisplayAdapter.ItemViewAdapter> {


    private lateinit var context: Context
    var mItemClickListener: OnItemClickListener? = null
    val alaheader: String = "اِسْم الآلَة"
    val zarfheader: String = "اِسْم الْظَرفْ"
    var verblist: ListView? = null
    var arabicFontsize: Int? = null
    var rootcolor: Int = 0
    var weaknesscolor: Int = 0
    var wazancolor: Int = 0
    var isSarfSagheerMazeed: Boolean = false
     var isviewtype =2

    private var isSarfSagheerThulahi: Boolean = false
    private var isverbconjugation: Boolean = false
    private var particples: Boolean = false
    private var isverb: Boolean = false
    private var isnoun: Boolean = false
    private lateinit var worddetails: HashMap<String, SpannableStringBuilder?>
    private lateinit var vbdetail: HashMap<String, String?>
    private lateinit var corpusexpand: ArrayList<QuranCorpusWbw>
    private lateinit var ismfaelmafool: ArrayList<ArrayList<*>>
    private var sarfsagheer: java.util.ArrayList<SarfSagheer>? = null
    private lateinit var spannable: SpannableStringBuilder
    private lateinit  var worddictorary: ArrayList<lughat>
    private lateinit var wazannumberslist: ArrayList<String> 
   
   private lateinit var sagheer: SarfSagheer
    private  var mafoolbihi: ArrayList<MafoolBihi>? =null
    private  var tameez: ArrayList<TameezEnt>? =null
    private lateinit var haliaSentence: ArrayList<HalEnt>

    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();
  //new  private lateinit var sarfsagheer: ArrayList<SarfSagheer>
    private  var mutlaq: ArrayList<MafoolMutlaqEnt>?   =null
    private  var liajlihi: ArrayList<LiajlihiEnt>? =null
    private lateinit var sharedPreferences: SharedPreferences
    constructor(
        context: Context,
        haliaSentence: ArrayList<HalEnt>?,
        tameez: ArrayList<TameezEnt>?,
        mafoolbihi: ArrayList<MafoolBihi>?,
        mutlaqword: ArrayList<MafoolMutlaqEnt>?,
        liajlihiEntArrayList: ArrayList<LiajlihiEnt>?,
        verb: Boolean,
        wazannumberslist: ArrayList<String>?,
        spannable: SpannableStringBuilder?,
        noun: Boolean,
        ismfaelmafool: ArrayList<ArrayList<*>>?,
        participles: Boolean,
        isverbconjugaton: Boolean,
        corpusSurahWord: ArrayList<QuranCorpusWbw>,
        wordbdetail: HashMap<String, SpannableStringBuilder?>?,
        vbdetail: HashMap<String, String?>?,
        mazeedSarfSagheer: Boolean,
        thulathiSarfSagheer: Boolean,
        sarfSagheerList: ArrayList<SarfSagheer>?,
    ) {
        this.context = context
        this.haliaSentence = haliaSentence!!
        this.tameez = tameez!!
        this.mafoolbihi = mafoolbihi!!
        mutlaq = mutlaqword!!
        liajlihi = liajlihiEntArrayList
        isverb = verb
        this.wazannumberslist = wazannumberslist!!
        this.spannable = spannable!!
        isnoun = noun
        if (ismfaelmafool != null) {
            this.ismfaelmafool = ismfaelmafool
        }
        particples = participles
        isverbconjugation = isverbconjugaton
        corpusexpand = corpusSurahWord!!
        worddetails = wordbdetail!!
        this.vbdetail = vbdetail!!
        isSarfSagheerMazeed = mazeedSarfSagheer
        isSarfSagheerThulahi = thulathiSarfSagheer
        sarfsagheer = sarfSagheerList
    }


    public override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): newRootWordDisplayAdapter.ItemViewAdapter {
        val view: View
        if (isverbconjugation) {
          isviewtype=1
            view = LayoutInflater.from(parent.context!!)
                .inflate(R.layout.qaris_view_word_details, parent, false)
        } else if (particples) {
            isviewtype=2
            view = LayoutInflater.from(parent.context!!)
                .inflate(R.layout.wordbottomsheetismfaelmafoolsktraditional, parent, false)
            //    view = LayoutInflater.from(parent.context!!).inflate(R.layout.qaris_view_word_details, parent, false);
        } else {
            isviewtype=1
            view = LayoutInflater.from(parent.context!!)
                .inflate(R.layout.qaris_view_word_details, parent, false)
        }
        return ItemViewAdapter(view,viewType)
    }
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onBindViewHolder(
        holder: newRootWordDisplayAdapter.ItemViewAdapter,
        position: Int,
    ) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val quranFont: String? = sharedPreferences.getString("quranFont", "kitab.ttf")
        val theme: String? = sharedPreferences.getString("themePref", "dark")
        val width: String? = sharedPreferences.getString("width", "compactWidth")
        if ((width == "mediumWidth") || (width == "expandedWidth")) {
            arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
        } else {
            arabicFontsize = 18
        }
        /*      if (theme.equals("dark") || theme.equals("blue")  ||theme.equals("green")) {
            //    holder.darkThemeBacground.setBackground(context.getResources().getDrawable(R.drawable.activatedbackgroundblack));
            holder.darkThemeBacground.setCardBackgroundColor(context.getResources().getColor(R.color.odd_item_bg_black));

        } else if (theme.equals("blue")) {
            holder.darkThemeBacground.setCardBackgroundColor(context.getResources().getColor(R.color.background_color_light_darkBlue));

        }*/if (!particples && !isnoun && !isverb) {
            holder.nounoccurancebtn.setVisibility(View.GONE)
            //  holder.verbOccurancebtn.setVisibility(GONE);
            holder.verbconjugationbtn.setVisibility(View.GONE)
            holder.verbdetails.setVisibility(View.GONE)
            holder.noundetails.setVisibility(View.GONE)
        }
        if (particples || isverb) {
            holder.verbconjugationbtn.setVisibility(View.VISIBLE)
            if (!(worddetails!!.get("formnumber") == null)) {
                holder.mazeedmeaning.setText(worddetails!!.get("formnumber"))
                holder.mazeedmeaning.setVisibility(View.VISIBLE)
                holder.mazeedmeaning.setTextSize(arabicFontsize!!.toFloat())
            }
            verblist = ListView(context)
            if (wazannumberslist!!.size == 1) {
                holder.rdone.setText(wazannumberslist!!.get(position))
                holder.rdone.setVisibility(View.VISIBLE)
                holder.rdone.setChecked(true)
                holder.rdone.setTextSize(arabicFontsize!!.toFloat())
            }
            if (wazannumberslist!!.size == 2) {
                holder.rdone.setText(wazannumberslist!!.get(position))
                holder.rdone.setVisibility(View.VISIBLE)
                holder.rdone.setChecked(true)
                holder.rdtwo.setText(wazannumberslist!!.get(position + 1))
                holder.rdtwo.setVisibility(View.VISIBLE)
                holder.rdtwo.setTextSize(arabicFontsize!!.toFloat())
                holder.rdone.setTextSize(arabicFontsize!!.toFloat())
            }
            if (wazannumberslist!!.size == 3) {
                holder.rdone.setText(wazannumberslist!!.get(position))
                holder.rdone.setVisibility(View.VISIBLE)
                holder.rdone.setChecked(true)
                holder.rdtwo.setText(wazannumberslist!!.get(position + 1))
                holder.rdtwo.setVisibility(View.VISIBLE)
                holder.rdthree.setText(wazannumberslist!!.get(position + 2))
                holder.rdthree.setVisibility(View.VISIBLE)
                holder.rdthree.setTextSize(arabicFontsize!!.toFloat())
                holder.rdone.setTextSize(arabicFontsize!!.toFloat())
                holder.rdtwo.setTextSize(arabicFontsize!!.toFloat())
            }
            if (wazannumberslist!!.size == 4) {
                holder.rdone.setText(wazannumberslist!!.get(position))
                holder.rdone.setVisibility(View.VISIBLE)
                holder.rdone.setChecked(true)
                holder.rdtwo.setText(wazannumberslist!!.get(position + 1))
                holder.rdtwo.setVisibility(View.VISIBLE)
                holder.rdfour.setText(wazannumberslist!!.get(position + 3))
                holder.rdfour.setVisibility(View.VISIBLE)
                holder.rdfour.setTextSize(arabicFontsize!!.toFloat())
                holder.rdthree.setTextSize(arabicFontsize!!.toFloat())
                holder.rdone.setTextSize(arabicFontsize!!.toFloat())
                holder.rdtwo.setTextSize(arabicFontsize!!.toFloat())
                holder.rdfour.setTextSize(arabicFontsize!!.toFloat())
            }
        }
        val mequran: Typeface = Typeface.createFromAsset(context.getAssets(), quranFont)
        Log.d(newRootWordDisplayAdapter.Companion.TAG, "onBindViewHolder: called")
        if ((theme == "dark") || (theme == "blue") || (theme == "green")) {
            rootcolor = Constant.BYELLOW
            weaknesscolor = Constant.BCYAN
            wazancolor = Constant.BCYAN
        } else {
            rootcolor = Constant.WBURNTUMBER
            weaknesscolor = Constant.KASHMIRIGREEN
            wazancolor = Constant.WMIDNIHTBLUE
        }
        holder.quranverseShart.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        holder.spannableverse.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        //   holder.verblist.setText(Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY));
        if (null != spannable) {
            val spans: Array<Any> = spannable!!.getSpans(0, spannable!!.length, Any::class.java)
            if (spans.size > 0) {
                holder.spannableverse.setText(spannable)
                holder.spannableverse.setTypeface(mequran)
                holder.spannableverse.setTextSize(arabicFontsize!!.toFloat())
            } else if (spannable != null) {
                holder.spannableverse.setText(spannable)
                holder.spannableverse.setTypeface(mequran)
                holder.spannableverse.setTextSize(arabicFontsize!!.toFloat())
            }
        }
        val ismfaelmafoolarray: ArrayList<*> = ArrayList<Any?>()
        if (isSarfSagheerMazeed || isSarfSagheerThulahi) {
            sagheer = sarfsagheer!!.get(position)
            holder.mazeedmeaning.setText(vbdetail!!.get("mazeed"))
            holder.mazeedmeaning.setText(vbdetail!!.get("formnumber"))
            //    holder.mazeedmeaning.setText(Html.fromHtml(vbdetail.get("mazeed")));
            holder.mazeedmeaning.setVisibility(View.VISIBLE)
            holder.mazeedmeaning.setTextSize(arabicFontsize!!.toFloat())
        }
        if (isnoun && !particples) {
            holder.verbconjugationbtn.setVisibility(View.GONE)
        }
        holder.translationView.setText(worddetails!!.get("translation"))


        //  String replace = word.toString().replace("\n", "<br/>").replace("\\n", "<br/>");
        holder.translationView.setTextSize(arabicFontsize!!.toFloat())
        //    holder.wordView.chipBackgroundColor = getColorStateList
     

        //    holder.wordView.chipBackgroundColor = getColorStateList
        val mafoolbihiverb = SpannableStringBuilder()
        var objectpronoun: SpannableStringBuilder? = SpannableStringBuilder()
        val tameezwordspan = SpannableStringBuilder()
        val ajlihiwordspan = SpannableStringBuilder()
        val mutlaqwordspan = SpannableStringBuilder()
        
        
        if (!tameez!!.isEmpty()) {
            tameezwordspan.append("(").append("تمييز").append(")")
            tameezwordspan.append(tameez!!.get(0)!!.word)
            val spanhash: Map<String?, ForegroundColorSpan> =
                CorpusUtilityorig.stringForegroundColorSpanMap
            tameezwordspan.setSpan(
                spanhash.get("N"),
                0,
                tameezwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.tameeztv?.setText(tameezwordspan)
            holder.tameeztv?.setVisibility(View.VISIBLE)
        }
        if (worddetails!!.get("liajlihi") != null) {
            ajlihiwordspan.append("(").append("مفعول لأجله").append(")")
            val spanhash: Map<String?, ForegroundColorSpan> =
                CorpusUtilityorig.stringForegroundColorSpanMap
            ajlihiwordspan.append(liajlihi!!.get(0)!!.word)
            ajlihiwordspan.setSpan(
                spanhash.get("N"),
                0,
                ajlihiwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.liajlihitv!!.setText(ajlihiwordspan)
            holder.liajlihitv!!.setVisibility(View.VISIBLE)
        }
        if (worddetails!!.get("mutlaqword") != null) {
            mutlaqwordspan.append("(").append("مفعول المطلق").append(")")
            val spanhash: Map<String?, ForegroundColorSpan> =
                CorpusUtilityorig.stringForegroundColorSpanMap
            mutlaqwordspan.append(mutlaq!!.get(0)!!.word)
            mutlaqwordspan.setSpan(
                spanhash.get("N"),
                0,
                mutlaqwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.mutlaqtv!!.setText(mutlaqwordspan)
            holder.mutlaqtv!!.setVisibility(View.VISIBLE)
        }
        var charSequence: CharSequence? = ""
        if (worddetails!!.get("mafoolbihi") != null) {
            val spanhash: Map<String?, ForegroundColorSpan> =
                CorpusUtilityorig.stringForegroundColorSpanMap
            //   mafoolbihiverb.append(mafoolbihi.get(0).getWord());
            val b: Boolean = mafoolbihi!!.get(0)!!.objectpronoun == null
            if (!b) {
                mafoolbihiverb.append(mafoolbihi!!.get(0)!!.word)
                objectpronoun =
                    SpannableStringBuilder .valueOf(mafoolbihi!!.get(0)!!.objectpronoun)
                objectpronoun.append("(").append("مفعول به").append(")")
                mafoolbihiverb.setSpan(
                    spanhash.get("V"),
                    0,
                    mafoolbihiverb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                objectpronoun.setSpan(
                    spanhash.get("PRON"), 0, objectpronoun.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                charSequence = TextUtils.concat(mafoolbihiverb, " ", objectpronoun)
            } else {
                mafoolbihiverb.append(mafoolbihi!!.get(0)!!.word)
                mafoolbihiverb.append("(").append("مفعول به").append(")")
                mafoolbihiverb.setSpan(
                    spanhash.get("N"),
                    0,
                    mafoolbihiverb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                charSequence = TextUtils.concat(mafoolbihiverb)
            }
        }
        if (!haliaSentence!!.isEmpty()) {
            holder.haliaSentence!!.setText(haliaSentence!!.get(0).text)
            holder.haliaSentence!!.setVisibility(View.VISIBLE)
            holder.haliaSentence!!.setTextSize(arabicFontsize!!.toFloat())
            holder.haliaSentence!!.setTypeface(mequran)
            holder.haliaSentence!!.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        }
        if (mafoolbihiverb.length != 0) {
            holder.mafoolat!!.setText(charSequence)
            holder.mafoolat!!.setTextSize(arabicFontsize!!.toFloat())
            holder.mafoolat!!.setVisibility(View.VISIBLE)
            holder.mafoolat!!.setTypeface(mequran)
            holder.mafoolat!!.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        }
        if (mafoolbihiverb.length != 0) {
            holder.mafoolat!!.setText(charSequence)
            holder.mafoolat!!.setTextSize(arabicFontsize!!.toFloat())
            holder.mafoolat!!.setVisibility(View.VISIBLE)
            holder.mafoolat!!.setTypeface(mequran)
            holder.mafoolat!!.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        }
        val word: SpannableStringBuilder? = worddetails!!.get("word")
        holder.wordView.setText(word)


        //    holder.wordView.setText(worddetails.get("word"));
        val vb: StringBuilder  = StringBuilder ()
        val pron: StringBuilder  = StringBuilder ()
        holder.lemma.setText(vbdetail!!.get("lemma"))
        //   holder.wordView.setTextSize(arabicFontsize);
        //   holder.lemma.setTextSize(arabicFontsize);
        worddetails!!.get("noun")
        worddetails!!.get("PRON")
        try {

        } catch (e: NullPointerException) {
            println("root word not found")
        }
        if (worddetails!!.get("noun") != null) {
            holder.noundetails.setVisibility(View.VISIBLE)
            holder.noundetails.setText(worddetails!!.get("noun"))
            //     holder.noundetails.setTextSize(arabicFontsize);
        }
        if (worddetails!!.get("PRON") != null) {
            holder.pronoundetails.setVisibility(View.VISIBLE)
            pron.append("Pronoun:")
            pron.append(worddetails!!.get("PRON"))
            holder.pronoundetails.setText(pron.toString())
            //  holder.pronoundetails.setTextSize(arabicFontsize);
        }
        vb.append("V-")
        if (vbdetail!!.get("thulathi") != null) {
            vb.append(vbdetail!!.get("thulathi"))
        }
        if (vbdetail!!.get("png") != null) {
            vb.append(vbdetail!!.get("png"))
        }
        if (vbdetail!!.get("tense") != null) {
            vb.append(vbdetail!!.get("tense"))
        }
        if (vbdetail!!.get("voice") != null) {
            vb.append(vbdetail!!.get("voice"))
        }
        if (vbdetail!!.get("mood") != null) {
            vb.append(vbdetail!!.get("mood"))
        }
        if (vbdetail!!.get("verbmood") != null) {
            holder.moodrules?.setVisibility(View.VISIBLE)
            holder.moodrules?.setText(vbdetail!!.get("verbmood"))
            //  holder.moodrules.setTextSize(arabicFontsize);
        }
        if (vb.length > 2) {
            holder.verbdetails.setVisibility(View.VISIBLE)
            holder.verbdetails.setText(vb.toString())
            //  holder.verbdetails.setTextSize(arabicFontsize);
        }
        holder.referenceView.setText(
            corpusexpand!!.get(0).corpus.surah.toString() + ":" + corpusexpand!!.get(0).corpus
                .ayah + ":" + corpusexpand!!.get(0).corpus.wordno
        )
        val worddetail: SpannableStringBuilder? = worddetails!!.get("worddetails")
        //  holder.wdetailstv.setText(worddetail, TextView.BufferType.SPANNABLE);
        holder.wdetailstv.setText(worddetail)
        //   holder.referenceView.setTextSize(arabicFontsize);
        holder.wdetailstv.setTextSize(16f)
        if (worddetails!!.get("lemma") != null || worddetails!!.get("lemma")!!.length != 0) {
            holder.lemma.setVisibility(View.VISIBLE)
            holder.lemma.setText(newRootWordDisplayAdapter.Companion.LEMMA + worddetails!!.get("lemma"))
            //        holder.lemma.setTextSize(arabicFontsize);
        }
        if (worddetails!!.get("root") != null) {
            holder.rootView.setText(
                newRootWordDisplayAdapter.Companion.ROOTWORDSTRING + worddetails!!.get(
                    "root"
                )
            )
            //    holder.rootView.setTextSize(arabicFontsize);
        }
        if (vbdetail!!.get("root") != null) {
            holder.rootView.setText(
                newRootWordDisplayAdapter.Companion.ROOTWORDSTRING + vbdetail!!.get(
                    "root"
                )
            )
            //    holder.rootView.setTextSize(arabicFontsize);
        }
       if (isSarfSagheerMazeed || isSarfSagheerThulahi) {
            val zarf: StringBuilder  = StringBuilder ()
            val ismala: StringBuilder  = StringBuilder ()
           holder.mamaroof?.setText(sagheer.madhi)
            holder.mumaroof?.setText(sagheer!!.mudharay)
            holder.ismfail?.setText(sagheer!!.ismfael)
            holder.mamajhool?.setText(sagheer!!.madhimajhool)
            holder.mumajhool?.setText(sagheer!!.mudharaymajhool)
            holder.ismmafool?.setText(sagheer!!.ismmafool)
            holder.amr?.setText(sagheer!!.amrone)
            holder.nahiamr?.setText(sagheer!!.nahiamrone)
            holder.ismzarfheader?.setText(zarfheader)
            holder.ismalaheader?.setText(alaheader)
            ismala.append(sagheer!!.ismalaone as CharSequence?).append(", ").append(
                sagheer!!.ismalatwo
            ).append(", ").append(sagheer!!.ismalathree)
            zarf.append(sagheer!!.zarfone as CharSequence?).append(", ")
                .append(sagheer!!.zarftwo).append(", ").append(
                sagheer!!.zarfthree
            )
            holder.ismzarf?.setText(zarf)
            holder.ismala?.setText(ismala)
            holder.weaknessname?.setText(sagheer!!.weakness)
            holder.rootword?.setText(sagheer!!.verbroot)
            holder.babdetails?.setText(sagheer!!.wazanname)
        }
        FontSizeSelection(holder)
        Fonttypeface(holder)
        //   VerbHeader(holder);
        if (particples) {
            SetTypeFace(holder)
            IsmFael(holder, ismfaelmafoolarray)
            IsmFaelFem(holder, ismfaelmafoolarray)
            IsmMafool(holder, ismfaelmafoolarray)
            IsmMafoolFem(holder)
            gcase(holder)
            ismfaelmafoolnumbers(holder)
            FontSIzeSelection(holder)
            val array: Array<String>
            val language: String? = sharedPreferences.getString("lan", "en")
            if ((language == "en")) {
                array = QuranGrammarApplication.context!!.getResources()
                    .getStringArray(R.array.enismfaelmafoolheadings)
            } else {
                array = QuranGrammarApplication.context!!.getResources()
                    .getStringArray(R.array.arismfaelmafoolheadings)
            }
            holder.apmas?.setText(array.get(0))
            holder.apfem?.setText(array.get(1))
            holder.ppmas?.setText(array.get(2))
            holder.ppfem?.setText(array.get(3))
            holder.apmas?.setTextSize(arabicFontsize!!.toFloat())
            holder.apfem?.setTextSize(arabicFontsize!!.toFloat())
            holder.apfem?.setGravity(View.TEXT_ALIGNMENT_CENTER)
            holder.ppmas?.setTextSize(arabicFontsize!!.toFloat())
            holder.ppfem?.setTextSize(arabicFontsize!!.toFloat())
        }
    }

    public override fun getItemCount(): Int {
        return corpusexpand!!.size
    }

    private fun gcase(holder: newRootWordDisplayAdapter.ItemViewAdapter) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        //  String theme = sharedPreferences.getString("theme", 1);
        val language: String? = sharedPreferences.getString("lang", "en")
        //     String language = SharedPref.getLanguage();
        val isTraditional: Boolean = true
        val array: Array<String>
        if ((language == "en")) array = QuranGrammarApplication.context!!.getResources()
            .getStringArray(R.array.encase) else {
            array =
                QuranGrammarApplication.context!!.getResources().getStringArray(R.array.arcase)
        }
        holder.nom?.setText(array.get(0))
        holder.acc?.setText(array.get(1))
        holder.gen?.setText(array.get(2))
        holder.nom1?.setText(array.get(0))
        holder.acc1?.setText(array.get(1))
        holder.gen1?.setText(array.get(2))
        holder.nom2?.setText(array.get(0))
        holder.acc2?.setText(array.get(1))
        holder.gen2?.setText(array.get(2))
        holder.nom3?.setText(array.get(0))
        holder.acc3?.setText(array.get(1))
        holder.gen3?.setText(array.get(2))
    }

    private fun ismfaelmafoolnumbers(holder: newRootWordDisplayAdapter.ItemViewAdapter) {
        val language: String? = sharedPreferences!!.getString("lan", "en")
        val isTraditional: Boolean = true
        val array: Array<String>
        if ((language == "en")) array =
            context.getResources().getStringArray(R.array.ennumbers) else {
            array = context.getResources().getStringArray(R.array.arnumbers)
        }
        holder.sin1?.setText(array.get(0))
        holder.dual1?.setText(array.get(1))
        holder.plu1?.setText(array.get(2))
        holder.sin2?.setText(array.get(0))
        holder.dual2?.setText(array.get(1))
        holder.plu2?.setText(array.get(2))
        holder.sin3?.setText(array.get(0))
        holder.dual3?.setText(array.get(1))
        holder.plu3?.setText(array.get(2))
        holder.sin4?.setText(array.get(0))
        holder.dual4?.setText(array.get(1))
        holder.plu4?.setText(array.get(2))
    }

    private fun IsmFael(
        holder: newRootWordDisplayAdapter.ItemViewAdapter,
        ismfaelmafoolarray: ArrayList<*>,
    ) {
        val iisone: String = ismfaelmafool!!.get(0).get(0).toString() //isone);
        val iistwo: String = ismfaelmafool!!.get(0).get(2).toString() //istwo);
        val iisthree: String = ismfaelmafool!!.get(0).get(4).toString() //isthree);
        val iisfour: String = ismfaelmafool!!.get(0).get(6).toString() //isfour);
        val iisfive: String = ismfaelmafool!!.get(0).get(8).toString() //isfive);
        val iissix: String = ismfaelmafool!!.get(0).get(10).toString() //issix);
        val iisseven: String = ismfaelmafool!!.get(0).get(12).toString() //isseven);
        val iiseight: String = ismfaelmafool!!.get(0).get(14).toString() //iseight);
        val iisnine: String = ismfaelmafool!!.get(0).get(16).toString() //isnine);
        FontSIzeSelection(holder)
        SetTypeFace(holder)
        holder.isone?.setText(iisone)
        holder.istwo?.setText(iistwo)
        holder.isthree?.setText(iisthree)
        holder.isfour?.setText(iisfour)
        holder.isfive?.setText(iisfive)
        holder.issix?.setText(iissix)
        holder.isseven?.setText(iisseven)
        holder.iseight?.setText(iiseight)
        holder.isnine?.setText(iisnine)
    }

    private fun IsmFaelFem(
        holder: newRootWordDisplayAdapter.ItemViewAdapter,
        ismfaelmafoolarray: ArrayList<*>,
    ) {
        val iisone: String = ismfaelmafool!!.get(1).get(1).toString() //isone);
        val iistwo: String = ismfaelmafool!!.get(1).get(3).toString() //istwo);
        val iisthree: String = ismfaelmafool!!.get(1).get(5).toString() //isthree);
        val iisfour: String = ismfaelmafool!!.get(1).get(7).toString() //isfour);
        val iisfive: String = ismfaelmafool!!.get(1).get(9).toString() //isfive);
        val iissix: String = ismfaelmafool!!.get(1).get(11).toString() //issix);
        val iisseven: String = ismfaelmafool!!.get(1).get(13).toString() //isseven);
        val iiseight: String = ismfaelmafool!!.get(1).get(15).toString() //iseight);
        val iisnine: String = ismfaelmafool!!.get(1).get(17).toString() //isnine);
        FontSIzeSelection(holder)
        SetTypeFace(holder)
        holder.ismfemone?.setText(iisone)
        holder.ismfemtwo?.setText(iistwo)
        holder.ismfemthree?.setText(iisthree)
        holder.ismfemfour?.setText(iisfour)
        holder.ismfemfive?.setText(iisfive)
        holder.ismfemsix?.setText(iissix)
        holder.ismfemseven?.setText(iisseven)
        holder.ismfemeight?.setText(iiseight)
        holder.ismfemnine?.setText(iisnine)
    }

    private fun IsmMafoolFem(holder: newRootWordDisplayAdapter.ItemViewAdapter) {
        val smafone: String = ismfaelmafool!!.get(1).get(1).toString()
        val smaftwo: String = ismfaelmafool!!.get(1).get(3).toString() //imaftwo);
        val smafthree: String = ismfaelmafool!!.get(1).get(5).toString() //imafthree);
        val smaffour: String = ismfaelmafool!!.get(1).get(7).toString() //imaffour);
        val smaffive: String = ismfaelmafool!!.get(1).get(9).toString() //imaffive);
        val smafsix: String = ismfaelmafool!!.get(1).get(11).toString() //imafseven);
        val smafseven: String = ismfaelmafool!!.get(1).get(13).toString() //imafseven);
        val smafeight: String = ismfaelmafool!!.get(1).get(15).toString() //imafeight);
        val smafnine: String = ismfaelmafool!!.get(1).get(17).toString() //imafnine);
        FontSIzeSelection(holder)
        SetTypeFace(holder)
        holder.imafoolfemone?.setText(smafone)
        holder.imafoolfemtwo?.setText(smaftwo)
        holder.imafoolfemthree?.setText(smafthree)
        holder.imafoolfemfour?.setText(smaffour)
        holder.imafoolfemfive?.setText(smaffive)
        holder.imafoolfemsix?.setText(smafsix)
        holder.imafoolfemseven?.setText(smafseven)
        holder.imafoolfemeight?.setText(smafeight)
        holder.imafoolfemnine?.setText(smafnine)
    }

    private fun IsmMafool(
        holder: newRootWordDisplayAdapter.ItemViewAdapter,
        ismfaelmafoolarray: ArrayList<*>,
    ) {
        val smafone: String = ismfaelmafool!!.get(0).get(0).toString()
        val smaftwo: String = ismfaelmafool!!.get(0).get(2).toString() //imaftwo);
        val smafthree: String = ismfaelmafool!!.get(0).get(4).toString() //imafthree);
        val smaffour: String = ismfaelmafool!!.get(0).get(6).toString() //imaffour);
        val smaffive: String = ismfaelmafool!!.get(0).get(8).toString() //imaffive);
        val smafsix: String = ismfaelmafool!!.get(0).get(10).toString() //imafseven);
        val smafseven: String = ismfaelmafool!!.get(0).get(12).toString() //imafseven);
        val smafeight: String = ismfaelmafool!!.get(0).get(14).toString() //imafeight);
        val smafnine: String = ismfaelmafool!!.get(0).get(16).toString() //imafnine);
        FontSIzeSelection(holder)
        SetTypeFace(holder)
        holder.imafone?.setText(smafone)
        holder.imaftwo?.setText(smaftwo)
        holder.imafthree?.setText(smafthree)
        holder.imaffour?.setText(smaffour)
        holder.imaffive?.setText(smaffive)
        holder.imafsix?.setText(smafsix)
        holder.imafseven?.setText(smafseven)
        holder.imafeight?.setText(smafeight)
        holder.imafnine?.setText(smafnine)
    }

    private fun SetTypeFace(holder: newRootWordDisplayAdapter.ItemViewAdapter) {
        //  final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        //  Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());
        //  Typeface arabicTypeface = Typeface.createFromAsset(QuranGrammarApplication.context!!.getAssets(), "Taha.ttf");
        val sharedPreferences: SharedPreferences =
            android.preference.PreferenceManager.getDefaultSharedPreferences(
                context
            )
        val arabic_font_selection: String? =
            sharedPreferences.getString("Arabic_Font_Selection", "me_quran.ttf")
        val arabicTypeface: Typeface = Typeface.createFromAsset(
            context.getAssets(),
            arabic_font_selection
        )

        //   String s = SharedPref.arabicFontSelection();
        val isTraditional: Boolean = true
        holder.nom?.setTypeface(arabicTypeface) // (array[0]);
        holder.acc?.setTypeface(arabicTypeface) // (array[1]);
        holder.gen?.setTypeface(arabicTypeface) // (array[2]);
        holder.nom1?.setTypeface(arabicTypeface) // (array[0]);
        holder.acc1?.setTypeface(arabicTypeface) // (array[1]);
        holder.gen1?.setTypeface(arabicTypeface) // (array[2]);
        holder.nom2?.setTypeface(arabicTypeface) // (array[0]);
        holder.acc2?.setTypeface(arabicTypeface) // (array[1]);
        holder.gen2?.setTypeface(arabicTypeface) // (array[2]);
        holder.nom3?.setTypeface(arabicTypeface) // (array[0]);
        holder.acc3?.setTypeface(arabicTypeface) // (array[1]);
        holder.gen3?.setTypeface(arabicTypeface) // (array[2]);
        holder.sin1?.setTypeface(arabicTypeface) //(array[0]);
        holder.dual1?.setTypeface(arabicTypeface) //(array[1]);
        holder.plu1?.setTypeface(arabicTypeface) //(array[2]);
        holder.sin2?.setTypeface(arabicTypeface) //(array[0]);
        holder.dual2?.setTypeface(arabicTypeface) //(array[1]);
        holder.plu2?.setTypeface(arabicTypeface) //(array[2]);
        holder.sin3?.setTypeface(arabicTypeface) //(array[0]);
        holder.dual3?.setTypeface(arabicTypeface) //(array[1]);
        holder.plu3?.setTypeface(arabicTypeface) //(array[2]);
        holder.sin4?.setTypeface(arabicTypeface) //(array[0]);
        holder.dual4?.setTypeface(arabicTypeface) //(array[1]);
        holder.plu4?.setTypeface(arabicTypeface) //(array[2]);
        holder.imafone?.setTypeface(arabicTypeface) //;//smafone);
        holder.imaftwo?.setTypeface(arabicTypeface) //;//smaftwo);
        holder.imafthree?.setTypeface(arabicTypeface) //;//smafthree);
        holder.imaffour?.setTypeface(arabicTypeface) //;//smaffour);
        holder.imaffive?.setTypeface(arabicTypeface) //;//smaffive);
        holder.imafsix?.setTypeface(arabicTypeface) //;//smafsix);
        holder.imafseven?.setTypeface(arabicTypeface) //;//smafseven);
        holder.imafeight?.setTypeface(arabicTypeface) //;//smafeight);
        holder.imafnine?.setTypeface(arabicTypeface) //;//smafnine);
        //
        holder.imafoolfemone?.setTypeface(arabicTypeface) //;//smafone);
        holder.imafoolfemtwo?.setTypeface(arabicTypeface) //;//smaftwo);
        holder.imafoolfemthree?.setTypeface(arabicTypeface) //;//smafthree);
        holder.imafoolfemfour?.setTypeface(arabicTypeface) //;//smaffour);
        holder.imafoolfemfive?.setTypeface(arabicTypeface) //;//smaffive);
        holder.imafoolfemsix?.setTypeface(arabicTypeface) //;//smafsix);
        holder.imafoolfemseven?.setTypeface(arabicTypeface) //;//smafseven);
        holder.imafoolfemeight?.setTypeface(arabicTypeface) //;//smafeight);
        holder.imafoolfemnine?.setTypeface(arabicTypeface) //;//smafnine);
        //
        holder.ismfemone?.setTypeface(arabicTypeface) //;//iismfemone);
        holder.ismfemtwo?.setTypeface(arabicTypeface) //;//iismfemtwo);
        holder.ismfemthree?.setTypeface(arabicTypeface) //;//iismfemthree);
        holder.ismfemfour?.setTypeface(arabicTypeface) //;//iismfemfour);
        holder.ismfemfive?.setTypeface(arabicTypeface) //;//iismfemfive);
        holder.ismfemsix?.setTypeface(arabicTypeface) //;//iismfemsix);
        holder.ismfemseven?.setTypeface(arabicTypeface) //;//iismfemseven);
        holder.ismfemeight?.setTypeface(arabicTypeface) //;//iismfemeight);
        holder.ismfemnine?.setTypeface(arabicTypeface) //;//iismfemnine);
        holder.isone?.setTypeface(arabicTypeface) //;//iisone);
        holder.istwo?.setTypeface(arabicTypeface) //;//iistwo);
        holder.isthree?.setTypeface(arabicTypeface) //;//iisthree);
        holder.isfour?.setTypeface(arabicTypeface) //;//iisfour);
        holder.isfive?.setTypeface(arabicTypeface) //;//iisfive);
        holder.issix?.setTypeface(arabicTypeface) //;//iissix);
        holder.isseven?.setTypeface(arabicTypeface) //;//iisseven);
        holder.iseight?.setTypeface(arabicTypeface) //;//iiseight);
        holder.isnine?.setTypeface(arabicTypeface) //;//iisnine);
    }

    private fun FontSIzeSelection(holder: newRootWordDisplayAdapter.ItemViewAdapter) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val arabicFontsize: Int = sharedPreferences.getInt("pref_font_arabic_key", 20)
        val isTraditional: Boolean = true
        holder.nom?.setTextSize(arabicFontsize.toFloat()) //(array[0]);
        holder.acc?.setTextSize(arabicFontsize.toFloat()) //(array[1]);
        holder.gen?.setTextSize(arabicFontsize.toFloat()) //(array[2]);
        holder.nom1?.setTextSize(arabicFontsize.toFloat()) //(array[0]);
        holder.acc1?.setTextSize(arabicFontsize.toFloat()) //(array[1]);
        holder.gen1?.setTextSize(arabicFontsize.toFloat()) //(array[2]);
        holder.nom2?.setTextSize(arabicFontsize.toFloat()) //(array[0]);
        holder.acc2?.setTextSize(arabicFontsize.toFloat()) //(array[1]);
        holder.gen2?.setTextSize(arabicFontsize.toFloat()) //(array[2]);
        holder.nom3?.setTextSize(arabicFontsize.toFloat()) //(array[0]);
        holder.acc3?.setTextSize(arabicFontsize.toFloat()) //(array[1]);
        holder.gen3?.setTextSize(arabicFontsize.toFloat()) //(array[2]);
        holder.sin1?.setTextSize(arabicFontsize.toFloat()) //(array[0]);
        holder.dual1?.setTextSize(arabicFontsize.toFloat()) //(array[1]);
        holder.plu1?.setTextSize(arabicFontsize.toFloat()) //(array[2]);
        holder.sin2?.setTextSize(arabicFontsize.toFloat()) //(array[0]);
        holder.dual2?.setTextSize(arabicFontsize.toFloat()) //(array[1]);
        holder.plu2?.setTextSize(arabicFontsize.toFloat()) //(array[2]);
        holder.sin3?.setTextSize(arabicFontsize.toFloat()) //(array[0]);
        holder.dual3?.setTextSize(arabicFontsize.toFloat()) //(array[1]);
        holder.plu3?.setTextSize(arabicFontsize.toFloat()) //(array[2]);
        holder.sin4?.setTextSize(arabicFontsize.toFloat()) //(array[0]);
        holder.dual4?.setTextSize(arabicFontsize.toFloat()) //(array[1]);
        holder.plu4?.setTextSize(arabicFontsize.toFloat()) //(array[2]);
        holder.imafone?.setTextSize(arabicFontsize.toFloat()) //smafone);
        holder.imaftwo?.setTextSize(arabicFontsize.toFloat()) //smaftwo);
        holder.imafthree?.setTextSize(arabicFontsize.toFloat()) //smafthree);
        holder.imaffour?.setTextSize(arabicFontsize.toFloat()) //smaffour);
        holder.imaffive?.setTextSize(arabicFontsize.toFloat()) //smaffive);
        holder.imafsix?.setTextSize(arabicFontsize.toFloat()) //smafsix);
        holder.imafseven?.setTextSize(arabicFontsize.toFloat()) //smafseven);
        holder.imafeight?.setTextSize(arabicFontsize.toFloat()) //smafeight);
        holder.imafnine?.setTextSize(arabicFontsize.toFloat()) //smafnine);
        //
        holder.imafoolfemone?.setTextSize(arabicFontsize.toFloat()) //smafone);
        holder.imafoolfemtwo?.setTextSize(arabicFontsize.toFloat()) //smaftwo);
        holder.imafoolfemthree?.setTextSize(arabicFontsize.toFloat()) //smafthree);
        holder.imafoolfemfour?.setTextSize(arabicFontsize.toFloat()) //smaffour);
        holder.imafoolfemfive?.setTextSize(arabicFontsize.toFloat()) //smaffive);
        holder.imafoolfemsix?.setTextSize(arabicFontsize.toFloat()) //smafsix);
        holder.imafoolfemseven?.setTextSize(arabicFontsize.toFloat()) //smafseven);
        holder.imafoolfemeight?.setTextSize(arabicFontsize.toFloat()) //smafeight);
        holder.imafoolfemnine?.setTextSize(arabicFontsize.toFloat()) //smafnine);
        //
        holder.ismfemone?.setTextSize(arabicFontsize.toFloat()) //iismfemone);
        holder.ismfemtwo?.setTextSize(arabicFontsize.toFloat()) //iismfemtwo);
        holder.ismfemthree?.setTextSize(arabicFontsize.toFloat()) //iismfemthree);
        holder.ismfemfour?.setTextSize(arabicFontsize.toFloat()) //iismfemfour);
        holder.ismfemfive?.setTextSize(arabicFontsize.toFloat()) //iismfemfive);
        holder.ismfemsix?.setTextSize(arabicFontsize.toFloat()) //iismfemsix);
        holder.ismfemseven?.setTextSize(arabicFontsize.toFloat()) //iismfemseven);
        holder.ismfemeight?.setTextSize(arabicFontsize.toFloat()) //iismfemeight);
        holder.ismfemnine?.setTextSize(arabicFontsize.toFloat()) //iismfemnine);
        holder.isone?.setTextSize(arabicFontsize.toFloat()) //iisone);
        holder.istwo?.setTextSize(arabicFontsize.toFloat()) //iistwo);
        holder.isthree?.setTextSize(arabicFontsize.toFloat()) //iisthree);
        holder.isfour?.setTextSize(arabicFontsize.toFloat()) //iisfour);
        holder.isfive?.setTextSize(arabicFontsize.toFloat()) //iisfive);
        holder.issix?.setTextSize(arabicFontsize.toFloat()) //iissix);
        holder.isseven?.setTextSize(arabicFontsize.toFloat()) //iisseven);
        holder.iseight?.setTextSize(arabicFontsize.toFloat()) //iiseight);
        holder.isnine?.setTextSize(arabicFontsize.toFloat()) //iisnine);
    }

    private fun Fonttypeface(holder: newRootWordDisplayAdapter.ItemViewAdapter) {
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val quranFont: String? = sharedPreferences.getString("quranFont", "kitab.ttf")
        val typeface: Typeface = Typeface.createFromAsset(
            context.getAssets(), quranFont
        )
        if (isverbconjugation) {
            holder.mamaroof?.setTypeface(typeface)
            holder.mumaroof?.setTypeface(typeface)
            holder.masdaro?.setTypeface(typeface)
            holder.masdart?.setTypeface(typeface)
            holder.ismfail?.setTypeface(typeface)
            holder.mamajhool?.setTypeface(typeface)
            holder.mumajhool?.setTypeface(typeface)
            holder.ismmafool?.setTypeface(typeface)
            holder.amr?.setTypeface(typeface)
            holder.nahiamr?.setTypeface(typeface)
            holder.babdetails?.setTypeface(typeface)
            holder.babdetails!!.setTextColor(wazancolor)
            holder.weaknesstype?.setTypeface(typeface)
            holder.weaknesstype!!.setTextColor(weaknesscolor)
            holder.weaknessname?.setTypeface(typeface)
            holder.weaknessname!!.setTextColor(rootcolor)
        }
    }

    private fun FontSizeSelection(holder: newRootWordDisplayAdapter.ItemViewAdapter) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val fontsize: Int = sharedPreferences.getInt("pref_font_arabic_key", 20)
        if (isverbconjugation) {
            holder.mamaroof?.setTextSize(fontsize.toFloat())
            holder.mumaroof?.setTextSize(fontsize.toFloat())
            holder.masdaro?.setTextSize(fontsize.toFloat())
            holder.masdart?.setTextSize(fontsize.toFloat())
            holder.ismfail?.setTextSize(fontsize.toFloat())
            holder.mamajhool?.setTextSize(fontsize.toFloat())
            holder.mumajhool?.setTextSize(fontsize.toFloat())
            holder.ismmafool?.setTextSize(fontsize.toFloat())
            holder.amr?.setTextSize(fontsize.toFloat())
            holder.nahiamr?.setTextSize(fontsize.toFloat())
            holder.babdetails?.setTextSize(fontsize.toFloat())
            holder.babdetails!!.setTextColor(Color.YELLOW)
            holder.weaknesstype?.setTextSize(fontsize.toFloat())
            holder.weaknesstype!!.setTextColor(Color.BLUE)
            holder.weaknessname?.setTextSize(fontsize.toFloat())
            holder.weaknessname!!.setTextColor(Color.GREEN)
        }
    }


    inner class ItemViewAdapter(view: View, viewType: Int) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val amr: TextView?
        val nahiamr: TextView?
        val ismfail: TextView?
        val mumaroof: TextView?
        val mamaroof: TextView?
        val ismala: TextView?
        val ismmafool: TextView?
        val mumajhool: TextView?
        val mamajhool: TextView?
        val ismzarf: TextView?

        //ISMFAEL
        val isone: TextView?
        val istwo: TextView?
        val isthree: TextView?
        val isfour: TextView?
        val isfive: TextView?
        val issix: TextView?
        val isseven: TextView?
        val iseight: TextView?
        val isnine: TextView?
        val ismfemone: TextView?
        val ismfemtwo: TextView?
        val ismfemthree: TextView?
        val ismfemfour: TextView?
        val ismfemfive: TextView?
        val ismfemsix: TextView?
        val ismfemseven: TextView?
        val ismfemeight: TextView?
        val ismfemnine: TextView?
        val imafone: TextView?
        val imaftwo: TextView?
        val imafthree: TextView?
        val imaffour: TextView?
        val imaffive: TextView?
        val imafsix: TextView?
        val imafseven: TextView?
        val imafeight: TextView?
        val imafnine: TextView?
        val imafoolfemone: TextView?
        val imafoolfemtwo: TextView?
        val imafoolfemthree: TextView?
        val imafoolfemfour: TextView?
        val imafoolfemfive: TextView?
        val imafoolfemsix: TextView?
        val imafoolfemseven: TextView?
        val imafoolfemeight: TextView?
        val imafoolfemnine: TextView?
        val mifalone: TextView?
        val mifaltwo: TextView?
        val mifalthree: TextView?
        val mifalfour: TextView?
        val mifalfive: TextView?
        val mifalsix: TextView?
        val mifalseven: TextView?
        val mifaleight: TextView?
        val mifalnine: TextView?
        val mifalatunone: TextView?
        val mifalatuntwo: TextView?
        val mifalatunthree: TextView?
        val mifalatunfour: TextView?
        val mifalatunfive: TextView?
        val mifalatunsix: TextView?
        val mifalatunseven: TextView?
        val mifalatuneight: TextView?
        val mifalatunnine: TextView?
        val mifaalone: TextView?
        val mifaaltwo: TextView?
        val mifaalthree: TextView?
        val mifaalfour: TextView?
        val mifaalfive: TextView?
        val mifaalsix: TextView?
        val mifaalseven: TextView?
        val mifaaleight: TextView?
        val mifaalnine: TextView?
        val mafalunone: TextView?
        val mafaluntwo: TextView?
        val mafalunthree: TextView?
        val mafalunfour: TextView?
        val mafalunfive: TextView?
        val mafalunsix: TextView?
        val mafalunseven: TextView?
        val mafaluneight: TextView?
        val mafalunnine: TextView?
        val sin1: TextView?
        val dual1: TextView?
        val plu1: TextView?
        val sin2: TextView?
        val dual2: TextView?
        val plu2: TextView?
        val sin3: TextView?
        val dual3: TextView?
        val plu3: TextView?
        val sin4: TextView?
        val dual4: TextView?
        val plu4: TextView?
        val nom: TextView?
        val acc: TextView?
        val gen: TextView?
        val nom1: TextView?
        val acc1: TextView?
        val gen1: TextView?
        val nom2: TextView?
        val acc2: TextView?
        val gen2: TextView?
        val nom3: TextView?
        val acc3: TextView?
        val gen3: TextView?
        var wordDictionary: TextView?=null
        var moodrules: TextView?=null
        //  val triroot: Chip
        // // val paradigm: Chip
        //  val rootdetails: Chip
        //val verb: Chip
        val referenceView: TextView
        val wdetailstv: TextView
        val lemma: TextView
        val verbdetails: TextView
        val noundetails: TextView
        val pronoundetails: TextView
        val translationView: TextView
        val mazeedmeaning: TextView
        val rootView: TextView
        val quranverseShart: TextView
        val spannableverse: TextView
        var rootword: TextView?=null
        var wazan: TextView?=null
        var ismzarfheader: TextView?=null
        var ismalaheader: TextView?=null
        var masdaro: TextView?=null
        var masdart: TextView?=null
        var babdetails: TextView?=null
        var weaknessname: TextView?=null
        var weaknesstype: TextView?=null
        var mafoolat: TextView?=null
        var liajlihitv: TextView?=null
        var mutlaqtv: TextView?=null
        val sheet: View
        val wordView: Chip
        var haliaSentence: TextView?=null
        val darkThemeBacground: MaterialCardView

        //  ListView list;
        val radioGroup: RadioGroup
        val rdone: RadioButton
        val rdtwo: RadioButton
        val rdthree: RadioButton
        val rdfour: RadioButton
        val dismissview: ImageView
        val apmas: TextView?
        val apfem: TextView?
        val ppmas: TextView?
        val ppfem: TextView?
        val verbconjugationbtn: TextView
        val nounoccurancebtn: TextView
        val tameeztv: TextView?
        //val expandable: ConstraintLayout
        var ifverborpart: MaterialCardView?=null

        init {

            ifverborpart = view.findViewById(R.id.ifverborpart)
            moodrules = itemView.findViewById(R.id.moodrules)
            mazeedmeaning = itemView.findViewById(R.id.mazeedmeaning)
            darkThemeBacground = itemView.findViewById(R.id.jumptoverse)
            rdone = view.findViewById(R.id.rdone)
            rdtwo = view.findViewById(R.id.rdtwo)
            rdthree = view.findViewById(R.id.rdthree)
            rdfour = view.findViewById(R.id.rdfour)
            radioGroup = view.findViewById(R.id.rdgroup)

            wordDictionary = view.findViewById(R.id.wordDictionary)
            spannableverse = view.findViewById(R.id.spannableverse)
            quranverseShart = view.findViewById(R.id.quranverseShart)
            verbconjugationbtn = view.findViewById(R.id.verbconjugationbtn)
            //   verbOccurancebtn = view.findViewById(R.id.verboccurance);
            nounoccurancebtn = view.findViewById(R.id.wordoccurance)
           rootword = view.findViewById(R.id.weaknesstype)
            ismzarfheader = view.findViewById(R.id.ismzarfheader)
            pronoundetails = view.findViewById(R.id.pronoundetails)
            noundetails = view.findViewById(R.id.noundetails)
            sheet = view.findViewById(R.id.sheet)
            wdetailstv = view.findViewById(R.id.wordDetails)
            lemma = view.findViewById(R.id.lemma)
            verbdetails = view.findViewById(R.id.verbdetails)
            dismissview = view.findViewById(R.id.dismissView)
            referenceView = view.findViewById(R.id.referenceView)
            liajlihitv = view.findViewById(R.id.liajlihi)
            mutlaqtv = view.findViewById(R.id.mutlaq)
            tameeztv = view.findViewById(R.id.tameez)
            mafoolat = view.findViewById(R.id.mafoolat)
            haliaSentence = view.findViewById(R.id.haliya)
            wordView = view.findViewById(R.id.wordView)
            translationView = view.findViewById(R.id.translationView)
            rootView = view.findViewById(R.id.rootView)
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
            ismzarf = view.findViewById(R.id.zarftable)
            babdetails = view.findViewById(R.id.babno)
            weaknesstype = view.findViewById(R.id.weaknesstype)
            weaknessname = view.findViewById(R.id.weknessname)
            spannableverse.setOnClickListener(this)
            wordView.setOnClickListener(this)
            if (isverbconjugation || particples) {
                ifverborpart!!.visibility = View.VISIBLE
                verbconjugationbtn.setOnClickListener(this)
                //     verbOccurancebtn.setOnClickListener(this);
                nounoccurancebtn.setOnClickListener(this)
                mazeedmeaning.setOnClickListener(this)
            } else if (isnoun) {
                //  verbOccurancebtn.setEnabled(false);
                verbconjugationbtn.setOnClickListener(this)
                //          verbOccurancebtn.setOnClickListener(this);
                nounoccurancebtn.setOnClickListener(this)
            }
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


        public override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, getLayoutPosition())
            }
        }
    }
    companion object {
        private val TAG: String = "VerseDisplayAdapter"
        private val ROOTWORDSTRING: String = "Root Word:-"
        private val LEMMA: String = "Lemma/Derivative-"
    }
}