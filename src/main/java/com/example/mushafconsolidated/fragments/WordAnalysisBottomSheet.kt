package com.example.mushafconsolidated.fragments


import Utility.ArabicLiterals
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.Constant.ACCUSATIVE
import com.example.Constant.AYAHNUMBER
import com.example.Constant.CONDITIONAL
import com.example.Constant.DEMONSTRATIVE
import com.example.Constant.IMPERATIVE
import com.example.Constant.INDICATIVE
import com.example.Constant.ISPARTICPLE
import com.example.Constant.NOUNCASE
import com.example.Constant.PREPOSITION
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.RELATIVE
import com.example.Constant.SHADDA
import com.example.Constant.SURAH_ID
import com.example.Constant.VERBMOOD
import com.example.Constant.VERBTYPE
import com.example.mushafconsolidated.Activity.WordOccuranceAct
import com.example.mushafconsolidated.Activityimport.LughatWordDetailsAct
import com.example.mushafconsolidated.Adaptersimport.RootWordDisplayAdapter
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO
import com.example.mushafconsolidated.Entities.NewKanaEntity
import com.example.mushafconsolidated.Entities.NewMudhafEntity
import com.example.mushafconsolidated.Entities.NewNasbEntity
import com.example.mushafconsolidated.Entities.NewShartEntity
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.SifaEntity
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.Entities.VerbWazan
import com.example.mushafconsolidated.Entities.wbwentity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.layout
import com.example.mushafconsolidated.Utils.Utils
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidatedimport.VerbFormsDialogFrag
import com.example.utility.CorpusUtilityorig.Companion.getSpancolor
import com.example.utility.QuranGrammarApplication
import com.google.android.material.button.MaterialButton
import java.util.Objects
import java.util.concurrent.Executors


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */
class WordAnalysisBottomSheet : DialogFragment() {
    //new   val sarfSagheerList = ArrayList<SarfSagheer>()




    val wazannumberslist = ArrayList<String>()
    protected var rwAdapter: RootWordDisplayAdapter? = null
    var chapterid = 0
    var ayanumber = 0
    var isMazeedSarfSagheer = false
    var isThulathiSarfSagheer = false
    var isIsverbconjugaton = false
    var isParticiples = false
    var isVerb = false
    var isNoun = false
    //new   lateinit var verbDictList: MutableList<MujarradVerbs>
    lateinit var expandableListTitle: List<String>
    lateinit var expandableListDetail: HashMap<String, List<SpannableString>>

    // --Commented out by Inspection (11/01/22, 8:24 AM):HashMap<Integer, HashMap<String, SpannableStringBuilder>> wordetailsall = new HashMap<>();
    // --Commented out by Inspection (11/01/22, 8:35 AM):HashMap<Integer, HashMap<String, String>> verbdetailsall = new HashMap<>();
    var vbdetail = HashMap<String, String?>()
    lateinit var wordbdetail: HashMap<String, SpannableStringBuilder?>
    var showGrammarFragments = false
    var isroot = false
    var isarabicword = false
    var quadrilateral = false
    var isnoun = false
    var ismujarradparticple = false
    var isimperative = false
    private var spannable: SpannableStringBuilder? = null
    private var themepreference: String? = null
    private var ismujarrad = false
    private var ismazeed = false
    private var isparticple = false
    private var isconjugation = false
    private var root: String? = null
    private lateinit var mujarradwazan: String
    private lateinit var mazeedwazan: String
    private lateinit var verbmood: String
    private var isHarf = false
    private var isrelative = false
    private var isdem = false
    private var isprep = false
    private var isharfnasb = false
    private var isShart = false
    lateinit var harfNasbAndZarf: String

    // --Commented out by Inspection (11/01/22, 8:25 AM):private ArrayList<NewCorpusExpandWbwPOJO> SencorpusSurahWord;
    private  lateinit var corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>

    // --Commented out by Inspection (11/01/22, 8:24 AM):private View selectedview;
    private lateinit var dialog: AlertDialog
    private  var ismfaelmafool: ArrayList<ArrayList<*>>? =null
    private lateinit var vb: VerbWazan
    private lateinit var arabicword: VerbWazan
    private val dark = false

    // --Commented out by Inspection (11/01/22, 8:26 AM):private SentenceRootWordDisplayAdapter sentenceRootWordDisplayAdapter;



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout.root_dialog_fragment, container, false)
        val prefs =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        themepreference = prefs.getString("theme", "dark")
        val recyclerView = view.findViewById<RecyclerView>(R.id.wordByWordRecyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        val bundle = this.requireArguments()
        val stringArray = bundle.getStringArray(ARG_OPTIONS_DATA)
        chapterid = stringArray!![0].toInt()
        val shared =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        showGrammarFragments = shared.getBoolean("fragments", false)
        ayanumber = stringArray[1].toInt()
        val wbwtranslation = stringArray[2]
        val wordno = stringArray[3].toInt()
        if (stringArray.size > 4) { //ignore if the call is from wordoccurance
            storepreferences(chapterid, ayanumber, stringArray[4])
        }
        val dark =
            themepreference == "dark" || themepreference == "blue" || themepreference == "green"
        val utils = Utils(activity)
        val ex = Executors.newSingleThreadExecutor()
        val builder = AlertDialog.Builder(requireActivity())
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(layout.layout_loading_dialog)
        dialog = builder.create()
        rwAdapter = context?.let { RootWordDisplayAdapter(it) }
        ex.execute {
            Objects.requireNonNull(requireActivity())
                .runOnUiThread { dialog!!.show() }
            val HaliaSentence: List<HalEnt?>? =
                utils.getHaliaErab(chapterid, ayanumber)
            val tameez: List<TameezEnt?>? =
                utils.getTameezWord(chapterid, ayanumber, wordno)
            val mafoolbihi: List<MafoolBihi?>? =
                utils.getMafoolbihiword(chapterid, ayanumber, wordno)
            val mutlaqword: List<MafoolMutlaqEnt?>? =
                utils.getMafoolMutlaqword(chapterid, ayanumber, wordno)
            val tameezWord: List<TameezEnt?>? =
                utils.getTameezWord(chapterid, ayanumber, wordno)
            val liajlihiEntArrayList: List<LiajlihiEnt?>? =
                utils.getMafoolLiajlihi(chapterid, ayanumber, wordno)
            corpusSurahWord = utils.getCorpusWbwBySurahAyahWordid(chapterid, ayanumber, wordno) as ArrayList<NewCorpusExpandWbwPOJO>
            val corpusNounWord: List<NounCorpus?>? =
                utils.getQuranNouns(chapterid, ayanumber, wordno)
            val verbCorpusRootWord: List<VerbCorpus?>? =
                utils.getQuranRoot(chapterid, ayanumber, wordno)
            val am = QuranMorphologyDetails(
                corpusSurahWord,
                corpusNounWord as ArrayList<NounCorpus>?,
                verbCorpusRootWord as ArrayList<VerbCorpus>?,
                context
            )
            vb = VerbWazan()
            wordbdetail = am.wordDetails
            if (verbCorpusRootWord != null) {
                if (verbCorpusRootWord.isNotEmpty() && verbCorpusRootWord?.get(0)?.tag.equals("V")) {
                    vbdetail = am.verbDetails
                    isVerb = true
                }
            }

            //if any true..good for verb conjugation
            if (!vbdetail.isEmpty() && Objects.requireNonNull(vbdetail["tense"])
                !!.contains("Imperative")
            ) {
                isimperative = true
            }






            if (tameezWord != null) {
                if (!tameezWord.isEmpty()) {
                    wordbdetail!!["tameez"] = SpannableStringBuilder.valueOf("yes")
                }
            }
            if (liajlihiEntArrayList != null) {
                if (!liajlihiEntArrayList.isEmpty()) {
                    wordbdetail!!["liajlihi"] = SpannableStringBuilder.valueOf("yes")
                }
            }
            if (mafoolbihi != null) {
                if (!mafoolbihi.isEmpty()) {
                    wordbdetail!!["mafoolbihi"] = SpannableStringBuilder.valueOf("yes")
                }
            }
            if (mutlaqword != null) {
                if (!mutlaqword.isEmpty()) {
                    wordbdetail!!["mutlaqword"] = SpannableStringBuilder.valueOf("yes")
                }
            }
            isarabicword = wordbdetail!!["arabicword"] != null
            ismujarrad = vbdetail["wazan"] != null
            ismazeed = vbdetail["form"] != null
            isparticple = wordbdetail!!["particple"] != null
            isconjugation = ismujarrad || ismazeed || isparticple
            isroot = wordbdetail!!["root"] != null
            val iscond = wordbdetail!!["cond"] != null
            isrelative = wordbdetail!!["relative"] != null
            isharfnasb = wordbdetail!!["harfnasb"] != null
            isprep = wordbdetail!!["prep"] != null
            isdem = wordbdetail!!["dem"] != null
            isShart = wordbdetail!!["cond"] != null
            isHarf = iscond == isrelative == isharfnasb == isprep == isdem


            if (isroot) {
                root = vbdetail["root"]
                vb.root = wordbdetail["root"].toString()
            }



            isnoun = wordbdetail!!["noun"] != null
            if (isparticple) {
                if (Objects.requireNonNull(wordbdetail!!["form"])
                        .toString() == "I"
                ) {
                    ismujarradparticple = true
                    ismujarrad = true
                } else {
                    ismujarradparticple = false
                    ismazeed = true
                }
            }
            //       root = vbdetail.get("root");
            if (ismujarrad && !isparticple) {
                mujarradwazan = vbdetail["wazan"].toString()
                verbmood = vbdetail["verbmood"].toString()
            } else if (ismazeed && !isparticple) {
                mazeedwazan = vbdetail["form"].toString()
                verbmood = vbdetail["verbmood"].toString()
            } else if (ismujarrad) { //when mujarrad particple use N=nasara bab
                mujarradwazan = "N"
            } else if (ismazeed) { //when mazeed get the form
                mazeedwazan = wordbdetail!!["form"].toString()
            }
            // vb.root=String.valueOf(wordbdetail.get("root")));
            if (iscond || isdem || isharfnasb || isprep || isrelative) {
                isroot=false
                isThulathiSarfSagheer = false
                isMazeedSarfSagheer = false
                isIsverbconjugaton=true
            }

            if (corpusNounWord != null) {
                corpusNounWord.size
            }
            try {
                //   if (corpusNounWord.get(0).getTag().equals("ACC") || corpusNounWord.get(0).equals("T") || corpusNounWord.get(0).equals("LOC")) {
                //    vb = new VerbWazan();
                if (corpusNounWord != null) {
                    if (corpusNounWord.size > 0 && (corpusNounWord[0]?.tag
                            .equals("COND") || corpusNounWord[0]?.tag
                            .equals("T") || corpusNounWord[0]?.tag.equals("LOC"))
                    ) {
                        if (corpusNounWord[0]?.tag.equals("COND")) {
                            harfNasbAndZarf = "ACC"
                        } else if (corpusNounWord[0]?.tag.equals("T")) {
                            harfNasbAndZarf = "T"
                        }
                        if (corpusNounWord[0]?.tag.equals("LOC")) {
                            harfNasbAndZarf = "LOC"
                        }
                        isNoun = true
                        isParticiples = false
                    } else if (corpusNounWord.size > 0 && (corpusNounWord[0]?.tag
                            .equals("ACC") || corpusNounWord[0]?.tag
                            .equals("T") || corpusNounWord[0]?.tag.equals("LOC"))
                    ) {
                        if (corpusNounWord[0]?.tag.equals("ACC")) {
                            harfNasbAndZarf = "ACC"
                        } else if (corpusNounWord[0]?.tag.equals("T")) {
                            harfNasbAndZarf = "T"
                        }
                        if (corpusNounWord[0]?.tag.equals("LOC")) {
                            harfNasbAndZarf = "LOC"
                        }
                        isNoun = true
                        isParticiples = false
                    } else if (wordbdetail!!["root"] != null && Objects.requireNonNull<SpannableStringBuilder?>(
                            wordbdetail!!["root"]
                        ).length == 4
                    ) {
                        isMazeedSarfSagheer = false //form 10 and 11 return 0 to be done
                        isThulathiSarfSagheer = false
                        isIsverbconjugaton=false
                        isroot=true
                        quadrilateral=true
                        vb.root=vbdetail["root"]
                    } else if (wordbdetail!!["root"] != null && wordbdetail!!["noun"] != null) {
                        vb.root=wordbdetail!!["root"].toString()
                        isroot=true
                        isNoun = true
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                println(e.message)
            }
            if (wbwtranslation == null) {
                val allwords: ArrayList<wbwentity> =
                    utils.getwbwTranslatonbywordNew(chapterid, ayanumber, wordno) as ArrayList<wbwentity>
                wordbdetail!!["translation"] = SpannableStringBuilder.valueOf(allwords[0].en)
            } else {
                wordbdetail!!["translation"] = SpannableStringBuilder.valueOf(wbwtranslation)
            }
            if (!corpusSurahWord!!.isEmpty()) {
                val quranverses: String? = corpusSurahWord!![0].qurantext
                spannable = SpannableStringBuilder(quranverses)
                SetShart(utils)
                setHarfNasb(utils)
                SetMausoofSifa(utils, corpusSurahWord)
                SetMudhaf(utils)
                SetKana(utils)
            }
            requireActivity().runOnUiThread {
                ex.shutdown()
                dialog!!.dismiss()
                if (showGrammarFragments) {
                    val grammarFragmentsListAdapter: GrammarFragmentsListAdapter
                    grammarFragmentsListAdapter = GrammarFragmentsListAdapter(
                        requireContext(),
                        expandableListTitle!!, expandableListDetail!!
                    )
                } else {


                    /*        rwAdapter = new RootWordDisplayAdapter(getContext(),HaliaSentence, tameez, mafoolbihi,mutlaqword,liajlihiEntArrayList, isVerb(), wazannumberslist, spannable,
                            isNoun(), ismfaelmafool, isParticiples(),
                            isIsverbconjugaton(), corpusSurahWord, wordbdetail, vbdetail,
                            isMazeedSarfSagheer(), isThulathiSarfSagheer(), sarfSagheerList);*/rwAdapter!!.setRootWordsAndMeanings(
                        HaliaSentence,
                        tameez as ArrayList<TameezEnt?>?,
                        mafoolbihi as ArrayList<MafoolBihi?>?,
                        mutlaqword as ArrayList<MafoolMutlaqEnt?>?,
                        liajlihiEntArrayList as ArrayList<LiajlihiEnt?>?,
                        isVerb,
                        wazannumberslist,
                        spannable,
                        isNoun,
                        ismfaelmafool,
                        isParticiples,
                        isIsverbconjugaton,
                        corpusSurahWord,
                        wordbdetail,
                        vbdetail,
                        isMazeedSarfSagheer,
                        isThulathiSarfSagheer,

                        requireActivity()
                    )

           /*         rwAdapter = RootWordDisplayAdaptercon(
                        requireContext(),
                        HaliaSentence,
                        tameez as ArrayList<TameezEnt>,
                        mafoolbihi as ArrayList<MafoolBihi>,
                        mutlaqword as ArrayList<MafoolMutlaqEnt>,
                        liajlihiEntArrayList as ArrayList<LiajlihiEnt>,
                        isVerb,
                        wazannumberslist,
                        spannable,
                        isNoun,

                        isParticiples,
                        isIsverbconjugaton,
                        corpusSurahWord,
                        wordbdetail,
                        vbdetail,
                        isMazeedSarfSagheer,
                        isThulathiSarfSagheer

                    )
*/



                    recyclerView.adapter = rwAdapter
                }
            }
        }
        return view
    }


    private fun storepreferences(chapterid: Int, ayanumber: Int, s: String) {
        var pref =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        pref = requireContext().getSharedPreferences("lastread", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(Constant.SURAH_ID, chapterid)
        editor.putInt(Constant.AYAH_ID, ayanumber)
        editor.putString(Constant.SURAH_ARABIC_NAME, s)
        editor.apply()
        editor.apply()
    }

    protected fun setHarfNasb(utils: Utils) {
        val harfnasb: List<NewNasbEntity?>? =
            utils.getHarfNasbIndSurahAyahSnew(chapterid, ayanumber)
        if (harfnasb != null) {
            for (nasb in harfnasb) {
                if (dark) {
                    Constant.harfinnaspanDark = ForegroundColorSpan(Color.GREEN)
                    Constant.harfismspanDark = ForegroundColorSpan(Constant.BCYAN)
                    Constant.harfkhabarspanDark = ForegroundColorSpan(Color.YELLOW)
                } else {
                    Constant.harfinnaspanDark = ForegroundColorSpan(Constant.WHOTPINK)
                    Constant.harfismspanDark = ForegroundColorSpan(Constant.prussianblue)
                    Constant.harfkhabarspanDark = ForegroundColorSpan(Constant.DeepPink)
                }
                val start: Int = nasb!!.indexstart
                val end: Int = nasb.indexend
                val ismstart: Int = nasb.ismstart
                val ismend: Int = nasb.ismend
                val khabarstart: Int = nasb.khabarstart
                val khabarend: Int = nasb.khabarend
                spannable!!.setSpan(
                    Constant.harfinnaspanDark,
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannable!!.setSpan(
                    Constant.harfismspanDark,
                    ismstart,
                    ismend,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannable!!.setSpan(
                    Constant.harfkhabarspanDark,
                    khabarstart,
                    khabarend,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }

    protected fun SetMudhaf(utils: Utils) {
        val mudhafSurahAyah: List<NewMudhafEntity?>? =
            utils.getMudhafSurahAyahNew(chapterid, ayanumber)
        if (mudhafSurahAyah != null) {
            for (mudhafEntity in mudhafSurahAyah) {
                Constant.mudhafspansDark = getSpancolor(true)
                spannable!!.setSpan(
                    Constant.mudhafspansDark,
                    mudhafEntity!!.startindex,
                    mudhafEntity.endindex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }

    protected fun SetMausoofSifa(
        utils: Utils,
        corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>?
    ) {
        val sifabySurahAyah: ArrayList<SifaEntity> = utils.getSifabySurahAyah(chapterid, ayanumber) as ArrayList<SifaEntity>
        val quranverses: String? = corpusSurahWord!![0].qurantext
        for (shartEntity in sifabySurahAyah) {
            Constant.sifaspansDark = getSpancolor(false)
            try {
                spannable!!.setSpan(
                    Constant.sifaspansDark,
                    shartEntity.startindex,
                    shartEntity.endindex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } catch (e: IndexOutOfBoundsException) {
                println(("  " + shartEntity.surah + shartEntity.ayah).toString() + "  " + quranverses)
            }
        }
    }

    protected fun SetShart(utils: Utils) {
        val shart: List<NewShartEntity?>? = utils.getShartSurahAyahNew(chapterid, ayanumber)
        //  this.spannable = new SpannableStringBuilder(quranverses);
        if (shart != null) {
            for (shartEntity in shart) {
                if (dark) {
                    Constant.harfshartspanDark = ForegroundColorSpan(Constant.GOLD)
                    Constant.shartspanDark = ForegroundColorSpan(Constant.ORANGE400)
                    Constant.jawabshartspanDark = ForegroundColorSpan(Color.CYAN)
                } else {
                    Constant.harfshartspanDark = ForegroundColorSpan(Constant.FORESTGREEN)
                    Constant.shartspanDark = ForegroundColorSpan(Constant.GREENDARK)
                    Constant.jawabshartspanDark = ForegroundColorSpan(Constant.WHOTPINK)
                }
                spannable!!.setSpan(
                    Constant.harfshartspanDark,
                    shartEntity!!.indexstart,
                    shartEntity.indexend,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannable!!.setSpan(
                    Constant.shartspanDark,
                    shartEntity.shartindexstart,
                    shartEntity.shartindexend,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannable!!.setSpan(
                    Constant.jawabshartspanDark,
                    shartEntity.jawabshartindexstart,
                    shartEntity.jawabshartindexend,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }

    protected fun SetKana(utils: Utils) {
        val kana: ArrayList<NewKanaEntity> = utils.getKanaSurahAyahnew(chapterid, ayanumber) as ArrayList<NewKanaEntity>
        //  this.spannable = new SpannableStringBuilder(quranverses);
        val harfkana: ForegroundColorSpan
        val kanaism: ForegroundColorSpan
        val kanakhbar: ForegroundColorSpan
        if (dark) {
            harfkana = ForegroundColorSpan(Constant.GOLD)
            kanaism = ForegroundColorSpan(Constant.ORANGE400)
            kanakhbar = ForegroundColorSpan(Color.CYAN)
        } else {
            harfkana = ForegroundColorSpan(Constant.FORESTGREEN)
            kanaism = ForegroundColorSpan(Constant.KASHMIRIGREEN)
            kanakhbar = ForegroundColorSpan(Constant.WHOTPINK)
        }
        for (kanaEntity in kana) {
            spannable!!.setSpan(
                harfkana,
                kanaEntity.indexstart,
                kanaEntity.indexend,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            try {
                spannable!!.setSpan(
                    kanaism,
                    kanaEntity.ismkanastart,
                    kanaEntity.ismkanaend,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } catch (indexOutOfBoundsException: IndexOutOfBoundsException) {
                println(indexOutOfBoundsException.message)
            }
            spannable!!.setSpan(
                kanakhbar,
                kanaEntity.khabarstart,
                kanaEntity.khabarend,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rwAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val text: CharSequence
                val text2: CharSequence? = null
                val viewVerbConjugation = v?.findViewById<View>(R.id.verbconjugationbtn)
                val verboccuranceid = v?.findViewById<View>(R.id.verboccurance)
                val nouns = v?.findViewById<View>(R.id.wordoccurance)
                val verse = v?.findViewById<View>(R.id.spannableverse)
                val wordview = v?.findViewById<View>(R.id.wordView)
                val formview = v?.findViewById<View>(R.id.mazeedmeaning)
                if (formview != null) {
                    val item = VerbFormsDialogFrag()
                    //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                    val fragmentManager = requireActivity().supportFragmentManager
                    var vbform: String?
                    if (!vbdetail.isEmpty()) {
                        vbform = vbdetail["formnumber"]
                    } else {
                        vbform = wordbdetail["formnumber"].toString()
                        vbform = vbform.replace("\\(".toRegex(), "").replace("\\)".toRegex(), "")
                    }
                    if (null != vbform) {
                        val data = arrayOf<String?>(vbform)
                        VerbFormsDialogFrag.newInstance(data).show(
                            Objects.requireNonNull(requireActivity()).supportFragmentManager,
                            TAG
                        )
                    }
                } else if (wordview != null) {
                    val dataBundle = Bundle()
                    if (isHarf) {
                        if (isharfnasb) {
                            dataBundle.putBoolean(ACCUSATIVE, true)
                        } else if (isprep) {
                            dataBundle.putBoolean(PREPOSITION, true)
                        } else if (isrelative) {
                            dataBundle.putBoolean(RELATIVE, true)
                        } else if (isdem) {
                            dataBundle.putBoolean(DEMONSTRATIVE, true)
                        } else if (isShart) {
                            dataBundle.putBoolean(CONDITIONAL, true)
                        }
                        dataBundle.putString("arabicword", wordbdetail["arabicword"].toString())
                        dataBundle.putString(VERBMOOD, "")
                        dataBundle.putString(QURAN_VERB_WAZAN, "")
                        dataBundle.putString(QURAN_VERB_ROOT, "")
                        dataBundle.putString(VERBTYPE, "")
                        val intent = Intent(activity, LughatWordDetailsAct::class.java)
                        intent.putExtras(dataBundle)
                        startActivity(intent)
                    } else if (quadrilateral) {
                        dataBundle.putString(QURAN_VERB_ROOT, vb.root)
                        dataBundle.putString(QURAN_VERB_WAZAN, " ")
                        dataBundle.putString("arabicword", "")
                    } else if (isarabicword && !isroot) {
                        dataBundle.putString("arabicword", wordbdetail["arabicword"].toString())
                        dataBundle.putString(QURAN_VERB_WAZAN, " ")
                        dataBundle.putString(QURAN_VERB_ROOT, " ")
                    } else if (isroot) {
                        dataBundle.putString("arabicword", "")
                        dataBundle.putString(VERBMOOD, vbdetail["verbmood"])
                        if (!vbdetail.isEmpty()) {
                            dataBundle.putString(QURAN_VERB_WAZAN, vb.wazan)
                            dataBundle.putString(QURAN_VERB_ROOT, vb.root)
                        } else if (isParticiples) {
                            dataBundle.putString(VERBMOOD, INDICATIVE)
                            dataBundle.putString(QURAN_VERB_WAZAN, vb.wazan)
                            dataBundle.putString(QURAN_VERB_ROOT, vb.root)
                            dataBundle.putBoolean(ISPARTICPLE, true)
                            if (isNoun) {
                                dataBundle.putString(NOUNCASE, wordbdetail["nouncase"].toString())
                            }
                        } else {
                            if (isNoun) {
                                dataBundle.putString(NOUNCASE, wordbdetail["nouncase"].toString())
                            }
                            dataBundle.putString(QURAN_VERB_ROOT, vb.root)
                            dataBundle.putString(QURAN_VERB_WAZAN, " ")
                        }
                    } else {
                        //   dataBundle.putString("arabicword", String.valueOf(wordbdetail.get("arabicword")));
                        var arabicword = wordbdetail["arabicword"].toString()
                        val inkasra = arabicword.indexOf(ArabicLiterals.Kasra)
                        val intshadda = arabicword.indexOf(SHADDA)
                        val intfatha = arabicword.indexOf(ArabicLiterals.Fatha)
                        val intdamma = arabicword.indexOf(ArabicLiterals.Damma)
                        if (inkasra != -1) {
                            arabicword = arabicword.replace(ArabicLiterals.Kasra.toRegex(), "")
                        }
                        if (intshadda != -1) {
                            arabicword = arabicword.replace(SHADDA.toRegex(), "")
                        }
                        if (intfatha != -1) {
                            arabicword = arabicword.replace(ArabicLiterals.Fatha.toRegex(), "")
                        }
                        if (intdamma != -1) {
                            arabicword = arabicword.replace(ArabicLiterals.Damma.toRegex(), "")
                        }
                        dataBundle.putString("arabicword", arabicword)
                        dataBundle.putString(VERBMOOD, "")
                        dataBundle.putString(QURAN_VERB_WAZAN, "")
                        dataBundle.putString(QURAN_VERB_ROOT, "")
                        dataBundle.putString(VERBTYPE, "")
                        //    Intent intent = new Intent(getActivity(), WordDictionaryAct.class);
                        val intent = Intent(activity, LughatWordDetailsAct::class.java)
                        intent.putExtras(dataBundle)
                        startActivity(intent)
                    }
                    if (isroot || isarabicword) {
                        try {
                            if (ismujarrad) {
                                dataBundle.putString(VERBTYPE, "mujarrad")
                            } else if (ismazeed) {
                                dataBundle.putString(VERBTYPE, "mazeed")
                            } else {
                                dataBundle.putString(VERBTYPE, "")
                            }
                            if (isimperative) {
                                dataBundle.putBoolean(IMPERATIVE, true)
                            }
                            //     Intent intent = new Intent(getActivity(), WordDictionaryAct.class);
                            val intent = Intent(activity, LughatWordDetailsAct::class.java)
                            intent.putExtras(dataBundle)
                            startActivity(intent)
                        } catch (e: NullPointerException) {
                            println("null pointer")
                        }
                    } else {
                        Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show()
                    }
                } else if (verse != null) {
                    val item = GrammerFragmentsBottomSheet()
                    //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                    val fragmentManager = requireActivity().supportFragmentManager
                    val dataBundle = Bundle()
                    dataBundle.putInt(SURAH_ID, chapterid)
                    dataBundle.putInt(AYAHNUMBER, ayanumber)
                    item.arguments = dataBundle
                    val data = arrayOf(chapterid.toString(), ayanumber.toString())
                    GrammerFragmentsBottomSheet.newInstance(data)
                        .show(Objects.requireNonNull(requireActivity()).supportFragmentManager, TAG)
                } else if (nouns != null) {
                    val bundle = Bundle()
                    //   Intent intent = new Intent(getActivity(), NounOccuranceAsynKAct.class);
                    val intent = Intent(activity, WordOccuranceAct::class.java)
                    try {
                        if (vb.root != null) {
                            bundle.putString(QURAN_VERB_ROOT, vb.root)
                        } else if (harfNasbAndZarf != null) {
                            bundle.putString(QURAN_VERB_ROOT, harfNasbAndZarf)
                        }
                    } catch (e1: NullPointerException) {
                        bundle.putString(QURAN_VERB_ROOT, harfNasbAndZarf)
                        e1.printStackTrace()
                    }
                    intent.putExtras(bundle)
                    //   intent.putExtra(QURAN_VERB_ROOT,vb.getRoot());
                    startActivity(intent)
                } else if (viewVerbConjugation != null) {
                    text = (viewVerbConjugation as MaterialButton).text
                    if (text.toString() == "Click for Verb Conjugation") {
                        if (isroot && isconjugation) {
                            val dataBundle = Bundle()
                            //      ArrayList arrayList = ThulathiMazeedConjugatonList.get(position);
                            //   arrayList.get(0).ge
                            if (ismujarrad) {
                                dataBundle.putString(VERBTYPE, "mujarrad")
                            } else if (ismazeed) {
                                dataBundle.putString(VERBTYPE, "mazeed")
                            }
                            if (vbdetail.isEmpty()) {
                                dataBundle.putString(VERBMOOD, "Indicative")
                            } else {
                                dataBundle.putString(VERBMOOD, vbdetail["verbmood"])
                            }
                            dataBundle.putString(QURAN_VERB_WAZAN, vb.wazan)
                            dataBundle.putString(QURAN_VERB_ROOT, vb.root)
                      /*      val intent = Intent(activity, ConjugatorTabsActivity::class.java)
                            intent.putExtras(dataBundle)
                            startActivity(intent)*/
                        }
                    }
                } else if (verboccuranceid != null) {
                    //      text2 = ((MaterialButton) verboccuranceid).getText();
                    if (vb != null) {
                        val bundle = Bundle()
                        val intent = Intent(activity, WordOccuranceAct::class.java)
                        bundle.putString(QURAN_VERB_ROOT, vb.root)
                        intent.putExtras(bundle)
                        //   intent.putExtra(QURAN_VERB_ROOT,vb.getRoot());
                        startActivity(intent)
                    }
                }
            }


        })


    }

    companion object {
        const val TAG = "bottom"

        // TODO: Customize parameter argument names
        private const val ARG_OPTIONS_DATA = "options_data"

        // TODO: Customize parameters
        fun newInstance(data: Array<String?>?): WordAnalysisBottomSheet {
            val fragment = WordAnalysisBottomSheet()
            val args = Bundle()
            args.putStringArray(ARG_OPTIONS_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}