package com.example.mushafconsolidated.Adaptersimport


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
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
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.CorpusUtilityorig.Companion.stringForegroundColorSpanMap
import com.example.utility.QuranGrammarApplication
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
 

class RootWordDisplayAdaptercon(
    private var context: Context,
    private var haliaSentence: List<HalEnt?>?,
    private var tameez: ArrayList<TameezEnt>,
    private var mafoolbihi: ArrayList<MafoolBihi>,
    private var mutlaq: ArrayList<MafoolMutlaqEnt>,
    private var liajlihi: ArrayList<LiajlihiEnt>,
    private var isverb: Boolean,
    private var wazannumberslist: ArrayList<String>,
    private var spannable: SpannableStringBuilder?,
    private var isnoun: Boolean,

    private var particples: Boolean,
    private var isverbconjugation: Boolean,
    private var corpusexpand: ArrayList<NewCorpusExpandWbwPOJO>,
    private var worddetails: HashMap<String, SpannableStringBuilder?>,
    private var vbdetail: HashMap<String, String?>,
    var isSarfSagheerMazeed: Boolean,
    private var isSarfSagheerThulahi: Boolean,

    ) : RecyclerView.Adapter<RootWordDisplayAdaptercon.ItemViewAdapter>() {
    var mItemClickListener: OnItemClickListener? = null
    val alaheader: String = "اِسْم الآلَة"
    val zarfheader: String = "اِسْم الْظَرفْ"
    var verblist: ListView? = null
    var arabicFontsize: Int? = null
    var rootcolor: Int = 0
    var weaknesscolor: Int = 0
    var wazancolor: Int = 0

    private var ismfaelmafool: ArrayList<ArrayList<*>>?=null
 

    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();
    //new  private lateinit var sarfsagheer: ArrayList<SarfSagheer>
   
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RootWordDisplayAdaptercon.ItemViewAdapter {
        val view: View
        view = if (isverbconjugation) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.qaris_view_word_details, parent, false)
        } else if (particples) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.wordbottomsheetismfaelmafoolsktraditional, parent, false)
            //    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qaris_view_word_details, parent, false);
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.qaris_view_word_details, parent, false)
        }
        return ItemViewAdapter(view)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onBindViewHolder(
        holder: RootWordDisplayAdaptercon.ItemViewAdapter,
        position: Int
    ) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val quranFont = sharedPreferences.getString("quranFont", "kitab.ttf")
        val theme = sharedPreferences.getString("themePref", "dark")
        val width = sharedPreferences.getString("width", "compactWidth")
        arabicFontsize = if (width == "mediumWidth" || width == "expandedWidth") {
            sharedPreferences.getInt("pref_font_arabic_key", 20)
        } else {
            18
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
            if (worddetails["formnumber"] != null) {
                holder.mazeedmeaning.setText(worddetails["formnumber"])
                holder.mazeedmeaning.setVisibility(View.VISIBLE)
                holder.mazeedmeaning.setTextSize(arabicFontsize!!.toFloat())
            }
            verblist = ListView(context)
            if (wazannumberslist.size == 1) {
                holder.rdone.setText(wazannumberslist[position])
                holder.rdone.setVisibility(View.VISIBLE)
                holder.rdone.setChecked(true)
                holder.rdone.setTextSize(arabicFontsize!!.toFloat())
            }
            if (wazannumberslist.size == 2) {
                holder.rdone.setText(wazannumberslist[position])
                holder.rdone.setVisibility(View.VISIBLE)
                holder.rdone.setChecked(true)
                holder.rdtwo.setText(wazannumberslist[position + 1])
                holder.rdtwo.setVisibility(View.VISIBLE)
                holder.rdtwo.setTextSize(arabicFontsize!!.toFloat())
                holder.rdone.setTextSize(arabicFontsize!!.toFloat())
            }
            if (wazannumberslist.size == 3) {
                holder.rdone.setText(wazannumberslist[position])
                holder.rdone.setVisibility(View.VISIBLE)
                holder.rdone.setChecked(true)
                holder.rdtwo.setText(wazannumberslist[position + 1])
                holder.rdtwo.setVisibility(View.VISIBLE)
                holder.rdthree.setText(wazannumberslist[position + 2])
                holder.rdthree.setVisibility(View.VISIBLE)
                holder.rdthree.setTextSize(arabicFontsize!!.toFloat())
                holder.rdone.setTextSize(arabicFontsize!!.toFloat())
                holder.rdtwo.setTextSize(arabicFontsize!!.toFloat())
            }
            if (wazannumberslist.size == 4) {
                holder.rdone.setText(wazannumberslist[position])
                holder.rdone.setVisibility(View.VISIBLE)
                holder.rdone.setChecked(true)
                holder.rdtwo.setText(wazannumberslist[position + 1])
                holder.rdtwo.setVisibility(View.VISIBLE)
                holder.rdfour.setText(wazannumberslist[position + 3])
                holder.rdfour.setVisibility(View.VISIBLE)
                holder.rdfour.setTextSize(arabicFontsize!!.toFloat())
                holder.rdthree.setTextSize(arabicFontsize!!.toFloat())
                holder.rdone.setTextSize(arabicFontsize!!.toFloat())
                holder.rdtwo.setTextSize(arabicFontsize!!.toFloat())
                holder.rdfour.setTextSize(arabicFontsize!!.toFloat())
            }
        }
        val mequran = Typeface.createFromAsset(context.assets, quranFont)
        Log.d(
            RootWordDisplayAdaptercon.Companion.TAG,
            "onBindViewHolder: called"
        )
        if (theme == "dark" || theme == "blue" || theme == "green") {
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
            val spans = spannable!!.getSpans(
                0, spannable!!.length,
                Any::class.java
            )
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
         //   sagheer = sarfsagheer[position]
            holder.mazeedmeaning.setText(vbdetail["mazeed"])
            holder.mazeedmeaning.setText(vbdetail["formnumber"])
            //    holder.mazeedmeaning.setText(Html.fromHtml(vbdetail.get("mazeed")));
            holder.mazeedmeaning.setVisibility(View.VISIBLE)
            holder.mazeedmeaning.setTextSize(arabicFontsize!!.toFloat())
        }
        if (isnoun && !particples) {
            holder.verbconjugationbtn.setVisibility(View.GONE)
        }
        holder.translationView.setText(worddetails["translation"])


        //  String replace = word.toString().replace("\n", "<br/>").replace("\\n", "<br/>");
        holder.translationView.setTextSize(arabicFontsize!!.toFloat())
        //    holder.wordView.chipBackgroundColor = getColorStateList
        val mafoolbihiverb = SpannableStringBuilder()
        var objectpronoun = SpannableStringBuilder()
        val tameezwordspan = SpannableStringBuilder()
        val ajlihiwordspan = SpannableStringBuilder()
        val mutlaqwordspan = SpannableStringBuilder()
        if (!tameez.isEmpty()) {
            tameezwordspan.append("(").append("تمييز").append(")")
            tameezwordspan.append(tameez[0].word)
            val spanhash = stringForegroundColorSpanMap
            tameezwordspan.setSpan(
                spanhash["N"],
                0,
                tameezwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.tameeztv?.setText(tameezwordspan)
            holder.tameeztv?.setVisibility(View.VISIBLE)
        }
        if (worddetails["liajlihi"] != null) {
            ajlihiwordspan.append("(").append("مفعول لأجله").append(")")
            val spanhash = stringForegroundColorSpanMap
            ajlihiwordspan.append(liajlihi[0].word)
            ajlihiwordspan.setSpan(
                spanhash["N"],
                0,
                ajlihiwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.liajlihitv.setText(ajlihiwordspan)
            holder.liajlihitv.setVisibility(View.VISIBLE)
        }
        if (worddetails["mutlaqword"] != null) {
            mutlaqwordspan.append("(").append("مفعول المطلق").append(")")
            val spanhash = stringForegroundColorSpanMap
            mutlaqwordspan.append(mutlaq[0].word)
            mutlaqwordspan.setSpan(
                spanhash["N"],
                0,
                mutlaqwordspan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.mutlaqtv.setText(mutlaqwordspan)
            holder.mutlaqtv.setVisibility(View.VISIBLE)
        }
        var charSequence: CharSequence? = ""
        if (worddetails["mafoolbihi"] != null) {
            val spanhash = stringForegroundColorSpanMap
            //   mafoolbihiverb.append(mafoolbihi.get(0).getWord());
            val b = mafoolbihi[0].objectpronoun == null
            if (!b) {
                mafoolbihiverb.append(mafoolbihi[0].word)
                objectpronoun = SpannableStringBuilder.valueOf(mafoolbihi[0].objectpronoun)
                objectpronoun.append("(").append("مفعول به").append(")")
                mafoolbihiverb.setSpan(
                    spanhash["V"],
                    0,
                    mafoolbihiverb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                objectpronoun.setSpan(
                    spanhash["PRON"], 0, objectpronoun.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                charSequence = TextUtils.concat(mafoolbihiverb, " ", objectpronoun)
            } else {
                mafoolbihiverb.append(mafoolbihi[0].word)
                mafoolbihiverb.append("(").append("مفعول به").append(")")
                mafoolbihiverb.setSpan(
                    spanhash["N"],
                    0,
                    mafoolbihiverb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                charSequence = TextUtils.concat(mafoolbihiverb)
            }
        }
        if (!haliaSentence?.isEmpty()!!) {
            holder.haliaSentence.setText(haliaSentence!![0]!!.text)
            holder.haliaSentence.setVisibility(View.VISIBLE)
            holder.haliaSentence.setTextSize(arabicFontsize!!.toFloat())
            holder.haliaSentence.setTypeface(mequran)
            holder.haliaSentence.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        }
        if (mafoolbihiverb.length != 0) {
            holder.mafoolat.setText(charSequence)
            holder.mafoolat.setTextSize(arabicFontsize!!.toFloat())
            holder.mafoolat.setVisibility(View.VISIBLE)
            holder.mafoolat.setTypeface(mequran)
            holder.mafoolat.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        }
        if (mafoolbihiverb.length != 0) {
            holder.mafoolat.setText(charSequence)
            holder.mafoolat.setTextSize(arabicFontsize!!.toFloat())
            holder.mafoolat.setVisibility(View.VISIBLE)
            holder.mafoolat.setTypeface(mequran)
            holder.mafoolat.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        }
        val word = worddetails["word"]
        holder.wordView.setText(word)


        //    holder.wordView.setText(worddetails.get("word"));
        val vb = StringBuilder()
        val pron = StringBuilder()
        holder.lemma.setText(vbdetail["lemma"])
        //   holder.wordView.setTextSize(arabicFontsize);
        //   holder.lemma.setTextSize(arabicFontsize);
        worddetails["noun"]
        worddetails["PRON"]
        try {
       
        } catch (e: NullPointerException) {
            println("root word not found")
        }
        if (worddetails["noun"] != null) {
            holder.noundetails.setVisibility(View.VISIBLE)
            holder.noundetails.setText(worddetails["noun"])
            //     holder.noundetails.setTextSize(arabicFontsize);
        }
        if (worddetails["PRON"] != null) {
            holder.pronoundetails.setVisibility(View.VISIBLE)
            pron.append("Pronoun:")
            pron.append(worddetails["PRON"])
            holder.pronoundetails.setText(pron.toString())
            //  holder.pronoundetails.setTextSize(arabicFontsize);
        }
        vb.append("V-")
        if (vbdetail["thulathi"] != null) {
            vb.append(vbdetail["thulathi"])
        }
        if (vbdetail["png"] != null) {
            vb.append(vbdetail["png"])
        }
        if (vbdetail["tense"] != null) {
            vb.append(vbdetail["tense"])
        }
        if (vbdetail["voice"] != null) {
            vb.append(vbdetail["voice"])
        }
        if (vbdetail["mood"] != null) {
            vb.append(vbdetail["mood"])
        }
        if (vbdetail["verbmood"] != null) {
            holder.moodrules.setVisibility(View.VISIBLE)
            holder.moodrules.setText(vbdetail["verbmood"])
            //  holder.moodrules.setTextSize(arabicFontsize);
        }
        if (vb.length > 2) {
            holder.verbdetails.setVisibility(View.VISIBLE)
            holder.verbdetails.setText(vb.toString())
            //  holder.verbdetails.setTextSize(arabicFontsize);
        }
        holder.referenceView.setText(corpusexpand[0].surah.toString() + ":" + corpusexpand[0].ayah + ":" + corpusexpand[0].wordno)
        val worddetail = worddetails["worddetails"]
        //  holder.wdetailstv.setText(worddetail, TextView.BufferType.SPANNABLE);
        holder.wdetailstv.setText(worddetail)
        //   holder.referenceView.setTextSize(arabicFontsize);
        holder.wdetailstv.setTextSize(16f)
        if (worddetails["lemma"] != null || worddetails["lemma"]!!.length != 0) {
            holder.lemma.setVisibility(View.VISIBLE)
            holder.lemma.setText(RootWordDisplayAdaptercon.Companion.LEMMA + worddetails["lemma"])
            //        holder.lemma.setTextSize(arabicFontsize);
        }
        if (worddetails["root"] != null) {
            holder.rootView.setText(RootWordDisplayAdaptercon.Companion.ROOTWORDSTRING + worddetails["root"])
            //    holder.rootView.setTextSize(arabicFontsize);
        }
        if (vbdetail["root"] != null) {
            holder.rootView.setText(RootWordDisplayAdaptercon.Companion.ROOTWORDSTRING + vbdetail["root"])
            //    holder.rootView.setTextSize(arabicFontsize);
        }
       /* if (isSarfSagheerMazeed || isSarfSagheerThulahi) {
            val zarf = StringBuilder()
            val ismala = StringBuilder()
            holder.mamaroof.setText(sagheer.getMadhi())
            holder.mumaroof.setText(sagheer.getMudharay())
            holder.ismfail.setText(sagheer.getIsmfael())
            holder.mamajhool.setText(sagheer.getMadhimajhool())
            holder.mumajhool.setText(sagheer.getMudharaymajhool())
            holder.ismmafool.setText(sagheer.getIsmmafool())
            holder.amr.setText(sagheer.getAmrone())
            holder.nahiamr.setText(sagheer.getNahiamrone())
            holder.ismzarfheader.setText(zarfheader)
            holder.ismalaheader.setText(alaheader)
            ismala.append(sagheer.getIsmalaone() as CharSequence).append(", ")
                .append(sagheer.getIsmalatwo()).append(", ").append(sagheer.getIsmalathree())
            zarf.append(sagheer.getZarfone() as CharSequence).append(", ")
                .append(sagheer.getZarftwo()).append(", ").append(sagheer.getZarfthree())
            holder.ismzarf.setText(zarf)
            holder.ismala.setText(ismala)
            holder.weaknessname.setText(sagheer.getWeakness())
            holder.rootword.setText(sagheer.getVerbroot())
            holder.babdetails.setText(sagheer.getWazanname())
        }*/
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
            val language = sharedPreferences.getString("lan", "en")
            array = if (language == "en") {
                QuranGrammarApplication.context!!.getResources()
                    .getStringArray(R.array.enismfaelmafoolheadings)
            } else {
                QuranGrammarApplication.context!!.getResources()
                    .getStringArray(R.array.arismfaelmafoolheadings)
            }
            holder.apmas?.setText(array[0])
            holder.apfem?.setText(array[1])
            holder.ppmas?.setText(array[2])
            holder.ppfem?.setText(array[3])
            holder.apmas?.setTextSize(arabicFontsize!!.toFloat())
            holder.apfem?.setTextSize(arabicFontsize!!.toFloat())
            holder.apfem?.setGravity(View.TEXT_ALIGNMENT_CENTER)
            holder.ppmas?.setTextSize(arabicFontsize!!.toFloat())
            holder.ppfem?.setTextSize(arabicFontsize!!.toFloat())
        }
    }

    override fun getItemCount(): Int {
        return corpusexpand.size
    }

    private fun gcase(holder: RootWordDisplayAdaptercon.ItemViewAdapter) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        //  String theme = sharedPreferences.getString("theme", 1);
        val language = sharedPreferences.getString("lang", "en")
        //     String language = SharedPref.getLanguage();
        val isTraditional = true
        val array: Array<String>
        array =
            if (language == "en") QuranGrammarApplication.context!!.getResources()
                .getStringArray(R.array.encase) else {
                QuranGrammarApplication.context!!.getResources().getStringArray(R.array.arcase)
            }
        holder.nom?.setText(array[0])
        holder.acc?.setText(array[1])
        holder.gen?.setText(array[2])
        holder.nom1?.setText(array[0])
        holder.acc1?.setText(array[1])
        holder.gen1?.setText(array[2])
        holder.nom2?.setText(array[0])
        holder.acc2?.setText(array[1])
        holder.gen2?.setText(array[2])
        holder.nom3?.setText(array[0])
        holder.acc3?.setText(array[1])
        holder.gen3?.setText(array[2])
    }

    private fun ismfaelmafoolnumbers(holder: RootWordDisplayAdaptercon.ItemViewAdapter) {
        val language = sharedPreferences!!.getString("lan", "en")
        val isTraditional = true
        val array: Array<String>
        array = if (language == "en") context.resources.getStringArray(R.array.ennumbers) else {
            context.resources.getStringArray(R.array.arnumbers)
        }
        holder.sin1?.setText(array[0])
        holder.dual1?.setText(array[1])
        holder.plu1?.setText(array[2])
        holder.sin2?.setText(array[0])
        holder.dual2?.setText(array[1])
        holder.plu2?.setText(array[2])
        holder.sin3?.setText(array[0])
        holder.dual3?.setText(array[1])
        holder.plu3?.setText(array[2])
        holder.sin4?.setText(array[0])
        holder.dual4?.setText(array[1])
        holder.plu4?.setText(array[2])
    }

    private fun IsmFael(
        holder: RootWordDisplayAdaptercon.ItemViewAdapter,
        ismfaelmafoolarray: ArrayList<*>
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
        holder: RootWordDisplayAdaptercon.ItemViewAdapter,
        ismfaelmafoolarray: ArrayList<*>
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

    private fun IsmMafoolFem(holder: RootWordDisplayAdaptercon.ItemViewAdapter) {
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
        holder: RootWordDisplayAdaptercon.ItemViewAdapter,
        ismfaelmafoolarray: ArrayList<*>
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

    private fun SetTypeFace(holder: RootWordDisplayAdaptercon.ItemViewAdapter) {
        //  final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        //  Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());
        //  Typeface arabicTypeface = Typeface.createFromAsset(QuranGrammarApplication.context!!.getAssets(), "Taha.ttf");
        val sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val arabic_font_selection =
            sharedPreferences.getString("Arabic_Font_Selection", "me_quran.ttf")
        val arabicTypeface = Typeface.createFromAsset(
            context.assets,
            arabic_font_selection
        )

        //   String s = SharedPref.arabicFontSelection();
        val isTraditional = true
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

    private fun FontSIzeSelection(holder: RootWordDisplayAdaptercon.ItemViewAdapter) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
        val isTraditional = true
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

    private fun Fonttypeface(holder: RootWordDisplayAdaptercon.ItemViewAdapter) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val quranFont = sharedPreferences.getString("quranFont", "kitab.ttf")
        val typeface = Typeface.createFromAsset(context.assets, quranFont)
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
            holder.babdetails.setTypeface(typeface)
            holder.babdetails.setTextColor(wazancolor)
            holder.weaknesstype.setTypeface(typeface)
            holder.weaknesstype.setTextColor(weaknesscolor)
            holder.weaknessname.setTypeface(typeface)
            holder.weaknessname.setTextColor(rootcolor)
        }
    }

    private fun FontSizeSelection(holder: RootWordDisplayAdaptercon.ItemViewAdapter) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val fontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
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
            holder.babdetails.setTextColor(Color.YELLOW)
            holder.weaknesstype?.setTextSize(fontsize.toFloat())
            holder.weaknesstype.setTextColor(Color.BLUE)
            holder.weaknessname?.setTextSize(fontsize.toFloat())
            holder.weaknessname.setTextColor(Color.GREEN)
        }
    }

    fun setRootWordsAndMeanings(
        haliaSentence: ArrayList<HalEnt>,
        tameez: ArrayList<TameezEnt>,
        mafoolbihi: ArrayList<MafoolBihi>,
        mutlaq: ArrayList<MafoolMutlaqEnt>,
        liajlihi: ArrayList<LiajlihiEnt>,
        verb: Boolean,
        wazannumberslist: ArrayList<String>,
        spannableStringBuilder: SpannableStringBuilder?,
        noun: Boolean,
        ismfaelmafool: ArrayList<ArrayList<*>>,
        participles: Boolean,
        isverbconjugation: Boolean,
        corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>,
        wordbdetail: HashMap<String, SpannableStringBuilder?>,
        vbdetail: HashMap<String, String?>,
        isSarfSagheer: Boolean,
        isSarfSagheerThulahi: Boolean,
      //  sarfsagheer: ArrayList<SarfSagheer>,
        context: Context
    ) {
        this.haliaSentence = haliaSentence
        this.tameez = tameez
        this.mafoolbihi = mafoolbihi
        isverb = verb
        this.wazannumberslist = wazannumberslist
        spannable = spannableStringBuilder
        isnoun = noun
        this.ismfaelmafool = ismfaelmafool
        particples = participles
        this.isverbconjugation = isverbconjugation
        corpusexpand = corpusSurahWord
        worddetails = wordbdetail
        this.vbdetail = vbdetail
        isSarfSagheerMazeed = isSarfSagheer
       // this.sarfsagheer = sarfsagheer
        this.isSarfSagheerThulahi = isSarfSagheerThulahi
        this.mutlaq = mutlaq
        this.liajlihi = liajlihi
        this.context = context
    }

    inner class ItemViewAdapter(view: View) : RecyclerView.ViewHolder(view),
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
        val wordDictionary: TextView
        val moodrules: TextView
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
        val rootword: TextView
        val wazan: TextView
        val ismzarfheader: TextView
        val ismalaheader: TextView
        val masdaro: TextView
        val masdart: TextView
        val babdetails: TextView
        val weaknessname: TextView
        val weaknesstype: TextView
        val mafoolat: TextView
        val liajlihitv: TextView
        val mutlaqtv: TextView
        val sheet: View
        val wordView: Chip
        val haliaSentence: TextView
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
        val ifverborpart: MaterialCardView

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
         //   expandable = view.findViewById(R.id.wazanlist)
            //   list=view.findViewById(R.id.verbrootlist);
         //   verb = view.findViewById(R.id.verb)
//            triroot = view.findViewById(R.id.triroot)
//            paradigm = view.findViewById(R.id.paradigm)
         //   rootdetails = view.findViewById(R.id.rootdetails)
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
                ifverborpart.visibility = View.VISIBLE
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
        private const val TAG = "VerseDisplayAdapter"
        private const val ROOTWORDSTRING = "Root Word:-"
        private const val LEMMA = "Lemma/Derivative-"
    }
}