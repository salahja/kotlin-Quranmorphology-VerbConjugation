package com.example.roots


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.SpannableString
import android.view.View
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.Constant
import com.example.Constant.ARABICWORD
import com.example.Constant.AYAH_ID
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.SARFKABEER
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.Constant.VERBMOOD
import com.example.Constant.VERBTYPE
import com.example.Constant.WORDDETAILS
import com.example.Constant.WORDMEANING
import com.example.mushafconsolidated.Activity.LughatWordDetailsAct
import com.example.mushafconsolidated.Activity.TopicDetailAct
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.RootVerbDetails
import com.example.mushafconsolidated.Entities.RootWordDetails
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Entities.VerbWazan
import com.example.mushafconsolidated.Entities.hanslexicon
import com.example.mushafconsolidated.Entities.lanelexicon
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.Entities.qurandictionary
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils.Utils
import com.example.mushafconsolidated.databinding.ActivityRootBreakupBinding
import com.example.utility.QuranGrammarApplication
import com.google.android.material.chip.Chip
import org.sj.conjugator.activity.BaseActivity
import org.sj.conjugator.activity.ConjugatorTabsActivity
import org.sj.conjugator.interfaces.OnItemClickListener

class RootBreakupAct : BaseActivity(), OnItemClickListener, View.OnClickListener {
    private lateinit var binding: ActivityRootBreakupBinding
    lateinit var shared: SharedPreferences
    var firstcolortat = 0
    var maincolortag = 0
    var pronouncolortag = 0
    var fourcolortag = 0
    private val appBarConfiguration: AppBarConfiguration?=null
    private lateinit var root: String
    private lateinit  var wordorverb: String
    private var verbCorpusArrayList: ArrayList<VerbCorpusBreakup>? = null
    private var occurances: ArrayList<CorpusNounWbwOccurance>? = null
    private var nounCorpusArrayList: ArrayList<NounCorpusBreakup>? = null
    var corpusNounWbwOccurances: ArrayList<CorpusNounWbwOccurance>? = null
        lateinit var  expandableListView: ExpandableListView
        lateinit var  viewPager2: ViewPager2
    var harf = false
        lateinit var  expandNounTitles: MutableList<String>
        lateinit var  expandVerbTitles: List<String>
          var  mPageNo = 0
        lateinit var  imgPg: ImageView
        lateinit var  link: TextView
    var counter = 0
        lateinit var  dialog: AlertDialog
    var updatechild: LinkedHashMap<String, List<SpannableString>> =
        LinkedHashMap<String, List<SpannableString>>()
    var expandNounVerses: LinkedHashMap<String, List<SpannableString>> =
        LinkedHashMap<String, List<SpannableString>>()
         var  expandVerbVerses: LinkedHashMap<String, List<SpannableString>> =
        LinkedHashMap<String, List<SpannableString>>()
    private     lateinit var  utils: Utils
        lateinit var  lanes: Chip
    lateinit var hanes: Chip
    private var rootdetails: ArrayList<RootWordDetails>? = null
    private var verbdetails: ArrayList<RootVerbDetails>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBreakupBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        val bundle = intent
        val prefs =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        val preferences = prefs.getString("theme", "dark")
        expandableListView = findViewById(R.id.expandableListView) as ExpandableListView
        lanes = findViewById(R.id.lanelexicon)
        val rootoccurance: TextView = binding.rootoccurance

        root= bundle.getStringExtra(QURAN_VERB_ROOT)!!
        rootoccurance.text = root

        lanes.setOnClickListener(this)
        lanes.setOnClickListener {
            val bundle = Bundle()
            //   Intent intent = new Intent(getActivity(), NounOccuranceAsynKAct.class);
            val intent = Intent(this@RootBreakupAct, LughatWordDetailsAct::class.java)
            //   getTypedValues();
            bundle.putString(QURAN_VERB_ROOT, root)
            bundle.putBoolean("dictionary", true)
            intent.putExtras(bundle)
            //   intent.putExtra(QURAN_VERB_ROOT,vb.getRoot());
            startActivity(intent)
        }
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
        if (preferences == "dark" || preferences == "blue" || preferences == "green") {
            firstcolortat = Constant.BCYAN
            maincolortag = Constant.BYELLOW
            pronouncolortag = Constant.BBLUE
            fourcolortag = Constant.BWHITE
        } else {
            firstcolortat = Constant.WBURNTUMBER
            maincolortag =
                ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.prussianblue)
            pronouncolortag = Constant.WMIDNIHTBLUE
            fourcolortag = Constant.GREENDARK
        }
        val lanelexicon: TextView = binding.lanelexicon
        val recyclerView: RecyclerView = binding.rootbreakup
        if (bundle.extras != null) {
            val bundles = intent.extras
            //   if (bundle != null) {
            root = bundles!!.getString(QURAN_VERB_ROOT)!!
            wordorverb = bundles.getString(WORDDETAILS)!!
        }
        utils = Utils(this)
        rootdetails = utils.getRootDetails(root) as ArrayList<RootWordDetails>?
        val rootDictionary: java.util.ArrayList<lughat?>? = utils.getRootDictionary(root) as java.util.ArrayList<lughat?>?
        val lanes: java.util.ArrayList<lanelexicon?>? = utils.getLanesDifinition(root) as java.util.ArrayList<lanelexicon?>?
        val hans: java.util.ArrayList<hanslexicon?>? = utils.getHansDifinition(root) as java.util.ArrayList<hanslexicon?>?
        val sb = StringBuilder()
        sb.append(root).append(" ").append("Ocurrance").append(" ").append(rootdetails!!.size)
        rootoccurance.text = sb.toString()
        val allroots: java.util.ArrayList<qurandictionary?>? = utils.getQuranDictionarybyroot(root) as java.util.ArrayList<qurandictionary?>?
        if (root == "ACC" || root == "LOC" || root == "T") {
            occurances = utils.getnounoccuranceHarfNasbZarf(root) as ArrayList<CorpusNounWbwOccurance>?
            harf = true
            ExecuteNounOcurrance()
        } else {
            harf = false
            ExecuteNounOcurrance()
        }
        lanelexicon.text = "Lanes"
        var adapter: MyRootBreakRecyclerViewAdapter? = null
        var verbDetailsRecAdapter: VerbDetailsRecAdapter? = null
        if (wordorverb == "word") {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            adapter = MyRootBreakRecyclerViewAdapter(rootdetails!!)
            recyclerView.adapter = adapter
 /*        adapter!!.SetOnItemClickListener(object : OnItemClickListener() {
                override fun onItemClick(v: View?, position: Int) {
                    val wordDetails = rootdetails!![position]
                    val datas = HashMap<String, String>()
                    val newbundle = Bundle()
                    newbundle.putInt(SURAH_ID, wordDetails.surah)
                    newbundle.putInt(AYAH_ID, wordDetails.ayah)
                    newbundle.putString(SURAH_ARABIC_NAME, wordDetails.namearabic)
                    newbundle.putString(ARABICWORD, wordDetails.arabic)
                    newbundle.putString(WORDMEANING, wordDetails.en)
                    newbundle.putSerializable("map", datas)
                    val intents = Intent(this@RootBreakupAct, TopicDetailAct::class.java)
                    intents.putExtras(newbundle)
                    startActivity(intents)

                    //   Fragment ayahfragment=new Fragment();
                    //   ayahfragment.setArguments(newbundle);
                    //  SelectedWordAyah item = new SelectedWordAyah();
                    //  item.setArguments(newbundle);
                    //  String[] data = {String.valueOf(wordDetails.getSurah()), String.valueOf(wordDetails.getAyah()), wordDetails.getNamearabic(), String.valueOf((wordDetails.getArabic())), wordDetails.getEn()};
                    //   SelectedWordAyah.newInstance(data).show(getSupportFragmentManager(), SelectedWordAyah.TAG);

                    //   Toast.makeText(RootBreakupAct.this, "chipclecked", Toast.LENGTH_LONG).show();
                }
            })*/
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 1)
            recyclerView.adapter = adapter
            verbdetails = utils.getRootVerbDetails(root) as ArrayList<RootVerbDetails>?
            verbDetailsRecAdapter = VerbDetailsRecAdapter(verbdetails!!)
            recyclerView.adapter = verbDetailsRecAdapter
            verbDetailsRecAdapter.SetOnItemClickListener(object : OnItemClickListener {

                override fun onItemClick(v: View?, position: Int) {
                    val dataBundle = Bundle()
                    val newbundle = Bundle()
                    val wordDetails = verbdetails!![position]
                    val datas = HashMap<String, String>()
                    if (v != null) {
                        if (v.tag == "conjugate") {
                            newbundle.putInt(SURAH_ID, wordDetails.surah)
                            newbundle.putInt(AYAH_ID, wordDetails.ayah)
                            newbundle.putString(SURAH_ARABIC_NAME, wordDetails.namearabic)
                            newbundle.putString(ARABICWORD, wordDetails.arabic)
                            newbundle.putString(WORDMEANING, wordDetails.en)
                            newbundle.putString(
                                VERBMOOD,
                                VerbWazan.getVerbMood(wordDetails.mood_kananumbers)
                            )
                            if (wordDetails.thulathibab!!.length == 0) {
                                newbundle.putString(
                                    QURAN_VERB_WAZAN,
                                    VerbWazan.getMazeedWazan(wordDetails.form)
                                )
                                newbundle.putString(VERBTYPE, "mazeed")
                            } else {
                                newbundle.putString(QURAN_VERB_WAZAN, wordDetails.thulathibab)
                                newbundle.putString(VERBTYPE, "mujarrad")
                            }
                            newbundle.putString(QURAN_VERB_ROOT, wordDetails.rootarabic)
                            //       dataBundle.putString(VERBTYPE, verbtype);
                            newbundle.putBoolean(SARFKABEER, true)
                            val intent = Intent(this@RootBreakupAct, ConjugatorTabsActivity::class.java)
                            intent.putExtras(newbundle)
                            startActivity(intent)
                        } else {
                            newbundle.putInt(SURAH_ID, wordDetails.surah)
                            newbundle.putInt(AYAH_ID, wordDetails.ayah)
                            newbundle.putString(SURAH_ARABIC_NAME, wordDetails.namearabic)
                            newbundle.putString(ARABICWORD, wordDetails.arabic)
                            newbundle.putString(WORDMEANING, wordDetails.en)
                            newbundle.putSerializable("map", datas)
                            val intents = Intent(this@RootBreakupAct, TopicDetailAct::class.java)
                            intents.putExtras(newbundle)
                            startActivity(intents)
                        }
                    }
                }


            })
        }
        //    rootDictionary.get(0).getHansweir();
    }

    private fun ExecuteNounOcurrance() {
        TODO("Not yet implemented")
    }


    override fun onItemClick(v: View?, position: Int) {
        Toast.makeText(this, "itemclicked", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        Toast.makeText(this, "itemclicked", Toast.LENGTH_SHORT).show()
    }
}