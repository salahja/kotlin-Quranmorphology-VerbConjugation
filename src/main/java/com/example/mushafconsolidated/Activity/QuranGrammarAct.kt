package com.example.mushafconsolidated.Activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.format.DateFormat
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.BottomOptionDialog
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.SurahSummary
import com.example.mushafconsolidated.ajroomiya.AjroomiyaDetailHostActivity
import com.example.mushafconsolidated.databinding.NewFragmentReadingBinding
import com.example.mushafconsolidated.fragments.BookMarkCreateFrag
import com.example.mushafconsolidated.fragments.BookmarkFragment
import com.example.mushafconsolidated.fragments.GrammerFragmentsBottomSheet
import com.example.mushafconsolidated.fragments.IOnBackPressed
import com.example.mushafconsolidated.fragments.NewSurahDisplayFrag
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet
import com.example.mushafconsolidated.fragments.newFlowAyahWordAdapter
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.example.mushafconsolidated.intrfaceimport.PassdataInterface
import com.example.mushafconsolidated.model.CorpusAyahWord
import com.example.mushafconsolidated.model.NewQuranCorpusWbw
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.example.mushafconsolidated.settingsimport.Constants
import com.example.mushafconsolidatedimport.ParticleColorScheme
import com.example.roots.arabicrootDetailHostActivity
import com.example.utility.CorpusUtilityorig
import com.example.utility.QuranGrammarApplication
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import database.NamesGridImageAct
import org.ahocorasick.trie.Trie
import org.sj.conjugator.activity.ConjugatorAct
import sj.hisnul.activity.HisnulBottomACT
import sj.hisnul.fragments.NamesDetail
import wheel.OnWheelChangedListener
import wheel.WheelView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Date
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class QuranGrammarAct : BaseActivity(), PassdataInterface, OnItemClickListenerOnLong {
    private lateinit var mainViewModel: QuranVIewModel
    private var corpusSurahWord: List<QuranCorpusWbw>? = null
    private var newnewadapterlist = LinkedHashMap<Int, ArrayList<NewQuranCorpusWbw>>()
    private lateinit var newflowAyahWordAdapter: newFlowAyahWordAdapter
    lateinit var binding: NewFragmentReadingBinding

    private lateinit var surahWheelDisplayData: Array<String>
    private lateinit var ayahWheelDisplayData: Array<String>
    private lateinit var btnBottomSheet: FloatingActionButton
    lateinit var surahArabicName: String
    private var jumptostatus = false
    var surah_id = 0
    var verseNumber = 0
    var suraNumber = 0
    private var rukucount = 0
    var surahname: String? = null
    private var mudhafColoragainstBlack = 0
    private var mausofColoragainstBlack = 0
    private var sifatColoragainstBlack = 0
    private var brokenPlurarColoragainstBlack = 0
    private var shartagainstback = 0
    private var surahorpart = 0

    // TextView tvsurah, tvayah, tvrukus;
    private var currentSelectSurah = 0

    // --Commented out by Inspection (13/08/23, 4:31 pm):RelativeLayout layoutBottomSheet;
    var versescount = 0
    private var chapterorpart = false

    // --Commented out by Inspection (14/08/21, 7:26 PM):ChipNavigationBar chipNavigationBar;
    private var verseNo = 0
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navigationView: NavigationView

    // ChipNavigationBar chipNavigationBar;
    private lateinit var materialToolbar: Toolbar

    //goes with extracttwothree
 //   private lateinit var refflowAyahWordAdapter: refFlowAyahWordAdapter

    // private lateinit var flowAyahWordAdapterpassage: FlowAyahWordAdapterPassage
    // private UpdateMafoolFlowAyahWordAdapter flowAyahWordAdapter;
    private var mausoof = false
    private var mudhaf = false
    private var harfnasb = false
    private var shart = false
    private lateinit var soraList: ArrayList<ChaptersAnaEntity>
    private var kana = false
    private var allofQuran: List<QuranEntity>? = null
    private lateinit var shared: SharedPreferences

    //  private OnClickListener onClickListener;
    private var corpusayahWordArrayList: ArrayList<CorpusAyahWord>? = null
    //  private val newadapterlist = LinkedHashMap<Int, ArrayList<NewCorpusAyahWord>>()

    private var mafoolbihiwords: List<MafoolBihi?>? = null
    private var Jumlahaliya: List<HalEnt?>? = null
    private var Tammezent: List<TameezEnt?>? = null
    private var Mutlaqent: List<MafoolMutlaqEnt?>? = null
    private var Liajlihient: List<LiajlihiEnt?>? = null
    private var BadalErabNotesEnt: List<BadalErabNotesEnt?>? = null


    private var isMakkiMadani = 0
    var chapterno = 0
    private lateinit var parentRecyclerView: RecyclerView
    private var mushafview = false

    /*   private val viewModelS: MazeedViewModel by viewModels {
           MazeedViewModelFactoryies(
               (this.application as QuranGrammarApplication).database.quranDao()
           )
       }
   */
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed).onBackPressed()) {
            super.onBackPressed()
        }
        //  finish();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dua_group, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView?
        searchView!!.queryHint = "Type something…"
        val sear = ContextCompat.getDrawable(this, R.drawable.custom_search_box)
        searchView.clipToOutline = true
        searchView.setBackgroundDrawable(sear)
        searchView.gravity = View.TEXT_ALIGNMENT_CENTER
        searchView.maxWidth = Int.MAX_VALUE
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.jumpto -> {
                SurahAyahPicker()
            }

            R.id.settings -> {
                val settingint = Intent(this, ActivitySettings::class.java)
                startActivity(settingint)
                navigationView.setCheckedItem(R.id.Names)
            }

            R.id.mushafview -> {
                val settingint = Intent(this, WordbywordMushafAct::class.java)
                startActivity(settingint)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        this.shared = PreferenceManager.getDefaultSharedPreferences(this@QuranGrammarAct)
        val preferences = shared.getString("themepref", "dark")
        super.onCreate(savedInstanceState)
        binding = NewFragmentReadingBinding.inflate(layoutInflater)
         mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
        val view = binding.root
        setContentView(view)
        // Get a reference to the ViewModel scoped to this Fragment
        // Get a reference to the ViewModel scoped to its Activity
        //    setContentView(R.layout.new_fragment_reading)
        materialToolbar = binding.toolbarmain




        setSupportActionBar(materialToolbar)
        val prefs =
            android.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        if (preferences == "dark" || preferences == "blue" || preferences == "green") {
            shartagainstback = prefs.getInt("shartback", Color.GREEN)
            mausofColoragainstBlack = prefs.getInt("mausoofblack", Color.RED)
            mudhafColoragainstBlack = prefs.getInt("mudhafblack", Color.CYAN)
            sifatColoragainstBlack = prefs.getInt("sifatblack", Color.YELLOW)
            brokenPlurarColoragainstBlack = prefs.getInt("brokenblack", Color.GREEN)
        } else {
            shartagainstback = prefs.getInt("shartback", Constant.INDIGO)
            mudhafColoragainstBlack = prefs.getInt("mausoofwhite", Color.GREEN)
            mausofColoragainstBlack = prefs.getInt("mudhafwhite", Constant.MIDNIGHTBLUE)
            sifatColoragainstBlack = prefs.getInt("sifatwhite", Constant.ORANGE400)
            brokenPlurarColoragainstBlack = prefs.getInt("brokenwhite", Constant.DARKMAGENTA)
        }
        if (isFirstTime) {
            val intents = Intent(this@QuranGrammarAct, ActivitySettings::class.java)
            startActivity(intents)
        }
        android.preference.PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
        mausoof = shared.getBoolean("mausoof", true)
        mudhaf = shared.getBoolean("mudhaf", true)
        harfnasb = shared.getBoolean("harfnasb", true)
        shart = shared.getBoolean("shart", true)
        kana = shared.getBoolean("kana", true)
        getpreferences()
        val bundle = intent
        if (bundle.extras != null) {
            val bundles = intent.extras
            //   if (bundle != null) {
            val lastread = bundles!!.getString(Constant.QURAN_VERB_ROOT)
            if (null != lastread && lastread == "quran") {
                getpreferences()
                chapterorpart = true
            } else {
                val chapter = bundle.getIntExtra(Constant.CHAPTER, 1)
                mushafview = bundles.getBoolean("passages", false)
                val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
                val list = mainViewModel.loadListschapter().value

                initView()
                initnavigation()
                chapterno = chapter

                surahArabicName = list!![chapter - 1].abjadname
                //   setChapterno( bundle.etIntExtra(SURAH_ID,2));
                verseNo = bundle.getIntExtra(Constant.AYAH_ID, 1)
                versescount = list[chapter - 1].versescount
                isMakkiMadani = list[chapter - 1].ismakki
                rukucount = list[chapter - 1].rukucount
                SetTranslation()
            }
        } else {
            initView()
            initnavigation()
            val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
            val list = mainViewModel.loadListschapter().value
            //    final boolean chapterorpartb = bundle.getBooleanExtra(CHAPTERORPART, true);
            initView()
            initnavigation()
            versescount = list!![chapterno - 1].versescount
            isMakkiMadani = list[chapterno - 1].ismakki
            rukucount = list[chapterno - 1].rukucount
            surahArabicName = surahname.toString()
            //    SetTranslation();
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
            val newCustomFragment = NewSurahDisplayFrag.newInstance()
            transaction.replace(R.id.frame_container, newCustomFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun initnavigation() {
        btnBottomSheet = binding.fab
        btnBottomSheet = binding.fab
        drawerLayout = binding.drawer
        navigationView = binding.navigationView
        bottomNavigationView = binding.bottomNavView
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            materialToolbar,
            R.string.drawer_open,
            R.string.drawer_close
                                          )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        btnBottomSheet.setOnClickListener { v: View? -> toggleBottomSheets() }
        //  bottomNavigationView.setOnItemSelectedListener { new  }OnItemReselectedListener
        bottomNavigationView.setOnItemReselectedListener { item: MenuItem ->
            if (item.itemId == R.id.surahnav) {
                materialToolbar.title = "Surah"
                val fragmentManager = supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                val newCustomFragment = NewSurahDisplayFrag.newInstance()
                transaction.replace(R.id.frame_container, newCustomFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                navigationView.setCheckedItem(R.id.surahnav)
            }
            if (item.itemId == R.id.conjugationnav) {
                materialToolbar.title = "Conjugator"
                val conjugatorintent = Intent(this@QuranGrammarAct, ConjugatorAct::class.java)
                startActivity(conjugatorintent)
            }
            if (item.itemId == R.id.dua) {
                materialToolbar.title = "Hisnul Muslim-Dua;s"
                val searchintent = Intent(this@QuranGrammarAct, HisnulBottomACT::class.java)
                //  val searchintent = Intent(this@QuranGrammarAct, HisnulComposeAct::class.java)
                startActivity(searchintent)
            }
            if (item.itemId == R.id.names) {
                materialToolbar.title = "Quran Audio"
                val settingint = Intent(this@QuranGrammarAct, NamesGridImageAct::class.java)
                settingint.putExtra(Constants.SURAH_INDEX, chapterno)
                startActivity(settingint)
            }
            if (item.itemId == R.id.mushafview) {
                materialToolbar.title = "Mushaf"
                val settingints = Intent(this@QuranGrammarAct, QuranGrammarAct::class.java)
                //      settingints.putExtra(Constants.SURAH_INDEX, getChapterno());
                startActivity(settingints)
            }
        }
        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            if (item.itemId == R.id.bookmark) {
                drawerLayout.closeDrawers()
                val bookmarkFragment = BookmarkFragment()
                //  TameezDisplayFrag bookmarkFragment=new TameezDisplayFrag();
                val transactions = supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transactions.replace(R.id.frame_container, bookmarkFragment)
                    .addToBackStack("mujarrad")
                transactions.commit()
            }
            if (item.itemId == R.id.rootdetails) {
                drawerLayout.closeDrawers()
                val bundle = Bundle()
                val roots = Intent(this, arabicrootDetailHostActivity::class.java)
                bundle.putString(Constant.WORDDETAILS, "word")
                roots.putExtras(bundle)

                startActivity(roots)
            }
            if (item.itemId == R.id.verbdetails) {
                drawerLayout.closeDrawers()
                val verbdetails = Intent(this, arabicrootDetailHostActivity::class.java)
                verbdetails.putExtra(Constant.WORDDETAILS, "verb")
                startActivity(verbdetails)
            }
            if (item.itemId == R.id.jumptoverse) {
                drawerLayout.closeDrawers()
                val grammar = Intent(this, AjroomiyaDetailHostActivity::class.java)
                startActivity(grammar)
            }
            if (item.itemId == R.id.search) {
                drawerLayout.closeDrawers()
                materialToolbar.title = "Root Word Search"
                val search = Intent(this, SearchKeyBoardAct::class.java)
                startActivity(search)
            }
            if (item.itemId == R.id.searchtopic) {
                drawerLayout.closeDrawers()
                materialToolbar.title = "Topics"
                val searchs = Intent(this, QuranTopicSearchActivity::class.java)
                startActivity(searchs)
            }
            /*       if (item.itemId == R.id.setting) {
                   drawerLayout.closeDrawers()
                     materialToolbar!!.title = "Prayer"
                     val pray = Intent(this@QuranGrammarAct, MainTwoActivityPrayer::class.java)
                     startActivity(pray)
            }*/
            if (item.itemId == R.id.searchtopic) {
                drawerLayout.closeDrawers()
                materialToolbar.title = "Topics"
                //      val searchs = Intent(this, QuranTopicSearchActivity::class.java)
                //     startActivity(searchs)
            }
            false
        }
    }// first time

    ////////////////
    private val isFirstTime: Boolean
        get() {
            val preferences = getPreferences(MODE_PRIVATE)
            val ranBefore = preferences.getBoolean("RanBefore", false)
            if (!ranBefore) {
                // first time
                val editor = preferences.edit()
                editor.putBoolean("RanBefore", true)
                editor.apply()
            }
            return !ranBefore
        }

    private fun getpreferences() {
        val pref = applicationContext.getSharedPreferences("lastread", MODE_PRIVATE)
        chapterno = pref.getInt(Constant.CHAPTER, 1)
        verseNo = pref.getInt(Constant.AYAH_ID, 1)
        surahname = pref.getString(Constant.SURAH_ARABIC_NAME, "")
        surahArabicName = surahname.toString()
    }

    fun initDialogComponents(readposition: Int) {
        val quranEntity = allofQuran!![readposition - 1]
        val suraNames: Spinner
        val verses: Spinner
        val ok: Button
        val sorasShow: MutableList<String?>
        //   jumpDialog = new Dialog(this,R.style.Base_Theme_AppCompat_Dialog);
        val jumpDialog = Dialog(this)
        jumpDialog.setContentView(R.layout.backupjumb_to_popup)
        suraNames = jumpDialog.findViewById(R.id.suras)
        verses = jumpDialog.findViewById(R.id.verses)



        jumpDialog.show()

        ok = jumpDialog.findViewById(R.id.ok)

        val currentsurah = quranEntity.surah
        verseNumber = quranEntity.ayah
        sorasShow = java.util.ArrayList()
        soraList = mainViewModel.loadListschapter().value as ArrayList<ChaptersAnaEntity>
        for ((count, entity) in soraList.withIndex()) {
            //  sorasShow.add(((++count) + " - " + (Locale.getDefault().getDisplayLanguage().equals("العربية") ? entity.getNamearabic() : entity.getAbjadname()).replace("$$$", "'")));
            val english = entity.nameenglish
            val abjad = entity.abjadname
            sorasShow.add((count + 1).toString() + " - " + english + "-" + abjad)
        }
        val show = sorasShow.toTypedArray()
        val verseAdapter: ArrayAdapter<String?>
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this,
            R.layout.myspinner, show
                                                                  )
        verseAdapter = ArrayAdapter<String?>(
            this,
            R.layout.spinner_layout_larg, verseNo
                                            )
        suraNames.adapter = adapter
        suraNames.setSelection(currentsurah - 1)
        surahArabicName = show[currentSelectSurah].toString()
        //  setSurahArabicName(show[getCurrentSelectSurah()])
        verses.adapter = verseAdapter
        //   verses.setSelection(verseNumber);
        suraNames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long,
                                       ) {
                suraNumber = position + 1
                val sora: ChaptersAnaEntity = soraList[position]
                //   surahIndex.inputType = suraNumber
                val verseAdapter: ArrayAdapter<String?>
                val versesNumbers = if (suraNumber == 1) sora.versescount + 1 else sora.versescount
                val numbers = arrayOfNulls<String>(versesNumbers)
                for (i in 1..versesNumbers) {
                    numbers[i - 1] = i.toString() + ""
                }
                verseAdapter = ArrayAdapter<String?>(
                    this@QuranGrammarAct,
                    R.layout.spinner_layout_larg, numbers
                                                    )
                verses.adapter = verseAdapter
                if (verseNumber <= numbers.size) {
                    verses.setSelection(verseNumber - 1)
                } else {
                    verses.setSelection(numbers.size - 1)
                }
                //    surahIndex.hint = suraNumber.toString() + ""
                //   sora.getNamearabic();
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        verses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long,
                                       ) {
                verseNumber = position + 1
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        ok.setOnClickListener { }
        ok.setOnClickListener {
            jumpDialog.dismiss()
            //    soraList.get(suraNumber).getAbjadname();
            this@QuranGrammarAct.surahArabicName =
                (suraNumber.toString() + "-" + soraList[suraNumber - 1].nameenglish + "-" + soraList[suraNumber - 1].abjadname)
            this@QuranGrammarAct.surahArabicName = (soraList[suraNumber - 1].abjadname)
            //  ayahIndex.getInputType();
            //   val text = ayahIndex.text
            this@QuranGrammarAct.versescount = (soraList[suraNumber - 1].versescount)
            this@QuranGrammarAct.isMakkiMadani = (soraList[suraNumber - 1].ismakki)
            this@QuranGrammarAct.rukucount = (soraList[suraNumber - 1].rukucount)
            this@QuranGrammarAct.currentSelectSurah = (suraNumber)
            //  setVerse_no(verseNumber);
            this@QuranGrammarAct.chapterno = (suraNumber)

            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView)








            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView)
            if (currentSelectSurah == surah_id) {
                parentRecyclerView.post {
                    parentRecyclerView.scrollToPosition(
                        verseNo
                                                       )
                }
            } else {
                jumptostatus = true
                this@QuranGrammarAct.surahorpart = (currentSelectSurah)
                this@QuranGrammarAct.surah_id = (currentSelectSurah)
                ExecuteSurahWordByWord()
                //     asyncTaskcorpus = new refactoringcurrentSurahSyncWordByWord().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    private fun SurahAyahPicker() {
        val mTextView: TextView
        val chapterWheel: WheelView
        val verseWheel: WheelView

        surahWheelDisplayData = arrayOf("")
        ayahWheelDisplayData = arrayOf("")

        val current = ArrayList<String>()
        val chapterno = IntArray(1)
        val verseno = IntArray(1)
        val surahArrays = resources.getStringArray(R.array.surahdetails)
        val versearrays = resources.getStringArray(R.array.versescounts)
        val intarrays = resources.getIntArray(R.array.versescount)

        val dialogPicker = AlertDialog.Builder(this@QuranGrammarAct)




     val   soraList = mainViewModel.loadListschapter().value as ArrayList<ChaptersAnaEntity>
        val inflater = this@QuranGrammarAct.layoutInflater
        val view = inflater.inflate(R.layout.activity_wheel_t, null)
        //  View view = inflater.inflate(R.layout.activity_wheel, null);
        dialogPicker.setView(view)
        mTextView = view.findViewById(R.id.textView2)
        chapterWheel = view.findViewById(R.id.wv_year)
        verseWheel = view.findViewById(R.id.wv_month)
        chapterWheel.setEntries(*surahArrays)
        //  chapterWheel.setCurrentIndex(getSurahselected() - 1);
        chapterWheel.currentIndex = this.chapterno - 1
        //set wheel initial state
        val initial = true
        if (initial) {
            val text = chapterWheel.getItem(this.chapterno - 1) as String
            surahWheelDisplayData[0] = text
            val chapno = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            chapterno[0] = chapno[0].toInt()
            verseno[0] = 1

            val intarray: Int = if (this.chapterno != 0) {
                intarrays[this.chapterno - 1]
            } else {
                7
            }
            for (i in 1..intarray) {
                current.add(i.toString())
            }
            verseWheel.setEntriesv(current)
            val texts = surahWheelDisplayData[0] + "/" + ayahWheelDisplayData[0]
            //   = mYear[0]+ mMonth[0];
            mTextView.text = texts
        }

        versearrays[this.chapterno - 1].toInt()

        verseWheel.setEntriesv(current)
        verseWheel.currentIndex = this.verseNo
        dialogPicker.setPositiveButton("Done") { dialogInterface: DialogInterface?, i: Int ->
            val sura: String
            try {
                surahArabicName =
                    suraNumber.toString() + "-" + soraList.get(chapterno[0] - 1).nameenglish + "-" + soraList[chapterno[0] - 1].abjadname
                surahArabicName = soraList[chapterno[0] - 1].abjadname
                verseNo = verseno[0]
                versescount = soraList[chapterno[0] - 1].versescount
                isMakkiMadani = soraList[chapterno[0] - 1].ismakki
                rukucount = soraList[chapterno[0] - 1].rukucount
                currentSelectSurah = soraList[chapterno[0] - 1].chapterid

            }
            catch (e: ArrayIndexOutOfBoundsException) {
                surahArabicName =
                    suraNumber.toString() + "-" + soraList[chapterno[0]].nameenglish + "-" + soraList[chapterno[0]].abjadname
                surahArabicName = soraList[chapterno[0]].abjadname
                verseNo = 1
                versescount = soraList[chapterno[0]].versescount
                isMakkiMadani = soraList[chapterno[0]].ismakki
                rukucount = soraList[chapterno[0]].rukucount
                currentSelectSurah = soraList[chapterno[0]].chapterid
                this.chapterno = soraList[chapterno[0]].chapterid
                println(soraList[chapterno[0]].chapterid)
                println(this.chapterno  )
                println(this.chapterno)
            }
            parentRecyclerView = binding.overlayViewRecyclerView
            //
            if (currentSelectSurah == this.chapterno) {
                parentRecyclerView.post { parentRecyclerView.scrollToPosition(verseNo) }
            } else {
                jumptostatus = true
                surahorpart = currentSelectSurah
                surah_id = currentSelectSurah
                this.chapterno=currentSelectSurah
                println(soraList[chapterno[0]].chapterid)
                println(this.chapterno  )
                println(this.chapterno)
                ExecuteSurahWordByWord()
                //     asyncTaskcorpus = new refactoringcurrentSurahSyncWordByWord().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

                sura = soraList[chapterno[0] - 1].chapterid.toString()
                this.chapterno = soraList[chapterno[0] - 1].chapterid
                surahArabicName = soraList[chapterno[0] - 1].nameenglish
                surahArabicName = soraList[chapterno[0] - 1].namearabic
                val pref = getSharedPreferences("lastreadmushaf", MODE_PRIVATE)
                val editor = pref.edit()
                editor.putInt(Constant.CHAPTER, sura.toInt())
                //  editor.putInt("page", page.getAyahItemsquran().get(0).getPage());
                editor.putString(
                    Constant.SURAH_ARABIC_NAME,
                    soraList[chapterno[0] - 1].namearabic
                                )
                editor.apply()

        }
        dialogPicker.setNegativeButton("Cancel") { dialogInterface: DialogInterface?, i: Int -> }
        val alertDialog = dialogPicker.create()
        val preferences = shared.getString("themepref", "dark")
        when (preferences) {
            "light" -> alertDialog.window!!.setBackgroundDrawableResource(R.color.md_theme_dark_onSecondary)
            "brown" -> alertDialog.window!!.setBackgroundDrawableResource(R.color.background_color_light_brown)
            "blue" -> alertDialog.window!!.setBackgroundDrawableResource(R.color.prussianblue)
            "green" -> alertDialog.window!!.setBackgroundDrawableResource(R.color.mdgreen_theme_dark_onPrimary)
        }
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        //   alertDialog.show();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.show()
        val buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        buttonPositive.setTextColor(ContextCompat.getColor(this@QuranGrammarAct, R.color.green))
        val buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        buttonNegative.setTextColor(ContextCompat.getColor(this@QuranGrammarAct, R.color.red))
        when (preferences) {
            "light", "brown" -> {
                buttonPositive.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.colorMuslimMate
                                          )
                                           )
                buttonNegative.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.red
                                          )
                                           )
            }

            "blue" -> {
                buttonPositive.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.yellow
                                          )
                                           )
                buttonNegative.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.Goldenrod
                                          )
                                           )
            }

            "green" -> {
                buttonPositive.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.yellow
                                          )
                                           )
                buttonNegative.setTextColor(
                    ContextCompat.getColor(
                        this@QuranGrammarAct,
                        R.color.cyan_light
                                          )
                                           )
            }
        }
        //  wmlp.gravity = Gravity.TOP | Gravity.CENTER;
        alertDialog.window!!.attributes = lp
        alertDialog.window!!.setGravity(Gravity.TOP)
        chapterWheel.onWheelChangedListener = object : OnWheelChangedListener {
            override fun onChanged(wheel: WheelView, oldIndex: Int, newIndex: Int) {
                val text = chapterWheel.getItem(newIndex) as String
                surahWheelDisplayData[0] = text
                val chapno = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                chapterno[0] = chapno[0].toInt()
                verseno[0] = 1
                updateVerses(newIndex)
                updateTextView()
                //    updateTextView();
            }

            private fun updateVerses(newIndex: Int) {

                val intarray: Int = if (newIndex != 0) {
                    intarrays[newIndex]
                } else {
                    7
                }
                for (i in 1..intarray) {
                    current.add(i.toString())
                }
                verseWheel.setEntriesv(current)
                updateTextView()
            }

            private fun updateTextView() {
                val text = surahWheelDisplayData[0] + "/" + ayahWheelDisplayData[0]
                //   = mYear[0]+ mMonth[0];
                mTextView.text = text
            }
        }
        verseWheel.onWheelChangedListener =
            OnWheelChangedListener { wheel, oldIndex, newIndex ->
                val text = verseWheel.getItem(newIndex) as String
                ayahWheelDisplayData[0] = text
                verseno[0] = text.toInt()
            }
    }

    private fun SetTranslation() {

        shared.getBoolean("prefs_show_erab", true)
        ExecuteSurahWordByWord()
    }

    private fun ExecuteSurahWordByWord() {
        val builder = AlertDialog.Builder(this, R.style.ThemeOverlay_Material3_Dialog)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        val dialog = builder.create()

        corpusayahWordArrayList = ArrayList()
        mafoolbihiwords = mainViewModel.getMafoolSurah(chapterno).value
        Jumlahaliya = mainViewModel.getHalsurah(chapterno).value
        Tammezent = mainViewModel.getTameezsurah(chapterno).value
        Liajlihient = mainViewModel.getLiajlihiSurah(chapterno).value
        Mutlaqent = mainViewModel.getMutlaqSurah(chapterno).value
        BadalErabNotesEnt = mainViewModel.getbadalSurah(chapterno).value


         allofQuran = mainViewModel.getquranbySUrah(chapterno).value
        corpusSurahWord = mainViewModel.getQuranCorpusWbwbysurah(chapterno).value

        val ex = Executors.newSingleThreadExecutor()
        ex.execute {
            //do inbackground
            bysurah(dialog, ex)
        }
    }








    @SuppressLint("NotifyDataSetChanged")
    private fun bysurah(dialog: AlertDialog, ex: ExecutorService) {


        runOnUiThread { dialog.show() }


        val corpus = CorpusUtilityorig(this)
        newnewadapterlist = corpus.composeWBWCollection(allofQuran, corpusSurahWord)



        if (kana) {
            newnewadapterlist.let { corpus.setKana(it, chapterno) }
        }
        if (shart) {
            newnewadapterlist.let { corpus.setShart(it, chapterno) }
        }
        if (mudhaf) {
            newnewadapterlist.let { corpus.setMudhafFromDB(it, chapterno) }
            //   corpusayahWordArrayList?.get(0)?.let { corpus.setMudhafFromDB(it, chapterno) }
        }
        if (mausoof) {
            newnewadapterlist.let { corpus.SetMousufSifaDB(it, chapterno) }
            //  corpus.NewMAOUSOOFSIFA(corpusayahWordArrayList);
        }
        if (harfnasb) {
            newnewadapterlist.let { corpus.newnewHarfNasbDb(it, chapterno) }
        }



        runOnUiThread {

            parentRecyclerView = binding.overlayViewRecyclerView

            if (jumptostatus) {
                surahorpart = chapterno
            }
            val listener: OnItemClickListenerOnLong = this
            val header = ArrayList<String>()
            header.add(rukucount.toString())
            header.add(versescount.toString())
            header.add(chapterno.toString())
            header.add(surahArabicName)
            HightLightKeyWord()
            val viewmodel: QuranVIewModel by viewModels()
            if (!mushafview) {
                // viewmodel.getVersesBySurahLive(chapterno).observe(this, {
                //    allofQuran=it
                newflowAyahWordAdapter = newFlowAyahWordAdapter(
                    false,
                    Mutlaqent,
                    Tammezent,
                    BadalErabNotesEnt,
                    Liajlihient,
                    Jumlahaliya,
                    mafoolbihiwords,
                    header,
                    allofQuran,
                    newnewadapterlist,
                    this@QuranGrammarAct,
                    surahArabicName,
                    isMakkiMadani,
                    listener
                                                               )
                dialog.dismiss()
                ex.shutdown()
                newflowAyahWordAdapter.addContext(this@QuranGrammarAct)
                parentRecyclerView.setHasFixedSize(true)
                parentRecyclerView.adapter = newflowAyahWordAdapter




                //   flowAyahWordAdapter.notifyDataSetChanged()
                parentRecyclerView.post({ parentRecyclerView.scrollToPosition(verseNo) })
                //   })
            }
        }
    }

    private fun HightLightKeyWord() {
        val inshartiastr = "«إِنْ» شرطية"
        val izazarfshartsrt = "وإذا ظرف يتضمن معنى الشرط"
        val izashartiastr = "«إِذا» ظرف يتضمن معنى الشرط"
        val jawabshartstr = "جواب شرط"
        val jawabsharttwostr = "لجواب الشرط"
        val jawabalshart = "جواب الشرط"
        val jawab = "جواب"
        val shart = ArrayList<String>()
        val mutlaq = ArrayList<String>()
        mutlaq.add("مطلق")
        mutlaq.add("مفعولا مطلقا")
        mutlaq.add("مفعولا مطلقا،")
        mutlaq.add("مطلق.")
        mutlaq.add("")
        shart.add(inshartiastr)
        shart.add(izazarfshartsrt)
        shart.add(izashartiastr)
        shart.add(jawabshartstr)
        shart.add(jawabsharttwostr)
        shart.add(jawabalshart)
        shart.add(jawab)
        shart.add("شرطية")
        shart.add("شرطية.")
        shart.add("ظرف متضمن معنى الشرط")
        shart.add("وإذا ظرف زمان يتضمن معنى الشرط")
        shart.add("ظرف زمان يتضمن معنى الشرط")
        shart.add("ولو حرف شرط غير جازم")
        shart.add("حرف شرط غير جازم")
        shart.add("اللام واقعة في جواب لو")
        shart.add("حرف شرط جازم")
        shart.add("الشرطية")
        val mudhafilahistr = "مضاف إليه"
        val sifastr = "صفة"
        val mudhaflenght = mudhafilahistr.length
        val sifalength = sifastr.length
        val hal = ArrayList<String>()
        hal.add("في محل نصب حال")
        hal.add("في محل نصب حال.")
        hal.add("والجملة حالية")
        hal.add("والجملة حالية.")
        hal.add("حالية")
        hal.add("حالية.")
        hal.add("حالية:")
        hal.add("حال")
        hal.add("حال:")
        hal.add("حال.")
        hal.add("الواو حالية")
        val tameez = ArrayList<String>()
        tameez.add("تمييز")
        tameez.add("تمييز.")
        tameez.add("التمييز")
        val badal = ArrayList<String>()
        badal.add("بدل")
        badal.add("بدل.")
        val ajilihi = ArrayList<String>()
        ajilihi.add("مفعول لأجله")
        ajilihi.add("لأجله")
        ajilihi.add("لأجله.")
        val mafoolbihi = ArrayList<String>()
        mafoolbihi.add("مفعول به")
        mafoolbihi.add("مفعول به.")
        mafoolbihi.add("مفعول به.(")
        mafoolbihi.add("في محل نصب مفعول")
        mafoolbihi.add("مفعول")
        for (pojo in allofQuran!!) {
            //  String ar_irab_two = pojo.getAr_irab_two();
            val ar_irab_two = pojo.ar_irab_two
            val str = SpannableStringBuilder(ar_irab_two)
            val mudhaftrie = Trie.builder().onlyWholeWords().addKeywords(mudhafilahistr).build()
            val mudhafemit = mudhaftrie.parseText(ar_irab_two)
            val sifatrie = Trie.builder().onlyWholeWords().addKeywords(sifastr).build()
            val sifaemit = sifatrie.parseText(ar_irab_two)
            val jawabsharttwotrie =
                Trie.builder().onlyWholeWords().addKeywords(jawabsharttwostr).build()
            val jawabsharttwoemit = jawabsharttwotrie.parseText(ar_irab_two)
            val trieBuilder =
                Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(shart).build()
            val emits = trieBuilder.parseText(ar_irab_two)
            val mutlaqbuilder =
                Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(mutlaq).build()
            val mutlaqemits = mutlaqbuilder.parseText(ar_irab_two)
            val haltrie =
                Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(hal).build()
            val halemits = haltrie.parseText(ar_irab_two)
            val tameeztrie =
                Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(tameez).build()
            val tameezemit = tameeztrie.parseText(ar_irab_two)
            val badaltrie =
                Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(badal).build()
            val badalemit = badaltrie.parseText(ar_irab_two)
            val ajilihitrie =
                Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(ajilihi).build()
            val ajilihiemit = ajilihitrie.parseText(ar_irab_two)
            val mafoolbihitri =
                Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(mafoolbihi).build()
            val mafoolbihiemit = mafoolbihitri.parseText(ar_irab_two)
            for (emit in mafoolbihiemit) {
                str.setSpan(
                    ForegroundColorSpan(sifatColoragainstBlack),
                    emit.start,
                    emit.start + emit.keyword.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            for (emit in ajilihiemit) {
                str.setSpan(
                    ForegroundColorSpan(sifatColoragainstBlack),
                    emit.start,
                    emit.start + emit.keyword.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            for (emit in tameezemit) {
                str.setSpan(
                    ForegroundColorSpan(sifatColoragainstBlack),
                    emit.start,
                    emit.start + emit.keyword.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            for (emit in badalemit) {
                str.setSpan(
                    ForegroundColorSpan(sifatColoragainstBlack),
                    emit.start,
                    emit.start + emit.keyword.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            for (emit in halemits) {
                str.setSpan(
                    ForegroundColorSpan(shartagainstback),
                    emit.start,
                    emit.start + emit.keyword.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            for (emit in emits) {
                str.setSpan(
                    ForegroundColorSpan(shartagainstback),
                    emit.start,
                    emit.start + emit.keyword.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            for (emit in mutlaqemits) {
                str.setSpan(
                    ForegroundColorSpan(shartagainstback),
                    emit.start,
                    emit.start + emit.keyword.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            for (emit in mudhafemit) {
                str.setSpan(
                    ForegroundColorSpan(mausofColoragainstBlack),
                    emit.start,
                    emit.start + mudhaflenght,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            for (emit in sifaemit) {
                str.setSpan(
                    ForegroundColorSpan(mudhafColoragainstBlack),
                    emit.start,
                    emit.start + sifalength,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
            }
            //    colorerab.get(0).setErabspnabble(str);
            pojo.erabspnabble = str
        }
    }

    private fun LoadItemList(dataBundle: Bundle, word: QuranEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        val item = GrammerFragmentsBottomSheet()
        val fragmentManager = supportFragmentManager
        item.arguments = dataBundle

        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out).show(item)
        // transactions.show(item);
        val data =
            arrayOf(word.surah.toString(), word.ayah.toString(), word.translation, 1.toString())
        GrammerFragmentsBottomSheet.newInstance(data)
            .show(supportFragmentManager, WordAnalysisBottomSheet.TAG)
    }

    override fun onItemClick(v: View, position: Int) {
        qurangrammarmenu(v, position)
        //  popupWindow(view);
    }

    @SuppressLint("RestrictedApi", "InflateParams")
    fun qurangrammarmenu(view: View, position: Int) {
        val tag = view.tag
        //  val quranEntity: QuranEntity = flowAyahWordAdapter.getItem(position) as QuranEntity
        val quranEntity: QuranEntity = try {
            allofQuran!![position]
        }
        catch (e: IndexOutOfBoundsException) {
            allofQuran!![position - 1]
        }
        val colorsentence = view.findViewById<SwitchCompat>(R.id.colorized)
        val colortag = shared.getBoolean("colortag", true)
        if (tag == "bookmarfb") {
            bookMarkSelected(position)
        } else if (tag == "collection") {
            val dataBundle = Bundle()
            val chapterno = corpusayahWordArrayList!![position - 1].word[0].surahId
            val verse = corpusayahWordArrayList!![position - 1].word[0].verseId
            dataBundle.putInt(Constant.SURAH_ID, chapterno)
            dataBundle.putInt(Constant.AYAHNUMBER, verse)
            dataBundle.putString(Constant.SURAH_ARABIC_NAME, surahArabicName)
            val item = BookMarkCreateFrag()
            item.arguments = dataBundle
            val data = arrayOf(chapterno.toString(), verse.toString(), surahArabicName)
            //    item.setdata(root!!WordMeanings,wbwRootwords,grammarRootsCombined);
            //   transactions.show(item);
            BookMarkCreateFrag.newInstance(data)
                .show(this@QuranGrammarAct.supportFragmentManager, WordAnalysisBottomSheet.TAG)
        }
        if (tag == "arrowforward") {
            val currentsurah = quranEntity.surah
            val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
            if (currentsurah != 114) {
                soraList = mainViewModel.loadListschapter().value as ArrayList<ChaptersAnaEntity>

                verseNumber = quranEntity.ayah
                val intent = intent.putExtra("chapter", soraList[0].chapterid)
                    .putExtra("chapterorpart", chapterorpart).putExtra(
                        Constant.SURAH_ARABIC_NAME, soraList[0].abjadname
                                                                      )
                    .putExtra(Constant.VERSESCOUNT, soraList[0].versescount)
                    .putExtra(Constant.RUKUCOUNT, soraList[0].rukucount).putExtra(
                        Constant.MAKKI_MADANI, soraList[0].ismakki
                                                                                 )
                overridePendingTransition(0, 0)
                startActivity(intent)
                finish()
                overridePendingTransition(
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left
                                         )
            }
        } else if (tag == "arrowback") {
            val currentsurah = quranEntity.surah
            if (currentsurah != 1) {

                val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]

                soraList = mainViewModel.loadListschapter().value as ArrayList<ChaptersAnaEntity>


                verseNumber = quranEntity.ayah
                val intent = intent.putExtra("chapter", soraList[0].chapterid)
                    .putExtra("chapterorpart", chapterorpart).putExtra(
                        Constant.SURAH_ARABIC_NAME, soraList[0].abjadname
                                                                      )
                    .putExtra(Constant.VERSESCOUNT, soraList[0].versescount)
                    .putExtra(Constant.RUKUCOUNT, soraList[0].rukucount).putExtra(
                        Constant.MAKKI_MADANI, soraList[0].ismakki
                                                                                 )
                overridePendingTransition(0, 0)
                startActivity(intent)
                finish()
                overridePendingTransition(
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left
                                         )
            }
        } else if (tag == "colorize") {
            if (colortag) {
                val editor =
                    android.preference.PreferenceManager.getDefaultSharedPreferences(this@QuranGrammarAct)
                        .edit()
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putBoolean("colortag", false)
                editor.apply()
                ReloadActivity(colorsentence)
            } else {
                val editor =
                    android.preference.PreferenceManager.getDefaultSharedPreferences(this@QuranGrammarAct)
                        .edit()
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putBoolean("colortag", true)
                editor.apply()
                ReloadActivity(colorsentence)
            }
        } else if (tag == "overflowbottom") {
            val chapterno = corpusayahWordArrayList!![position - 1].word[0].surahId
            val verse = corpusayahWordArrayList!![position - 1].word[0].verseId
            val name = surahArabicName
            val data = arrayOf(chapterno.toString(), verse.toString(), name)
            BottomOptionDialog.newInstance(data)
                .show(this@QuranGrammarAct.supportFragmentManager, WordAnalysisBottomSheet.TAG)
        } else if (tag == "jumptofb") {
            initDialogComponents(position)
        } else if (tag == "sharefb") {
            takeScreenShot(window.decorView)
        } else if (tag == "helpfb") {
            val chapterno = corpusayahWordArrayList!![position - 1].word[0].surahId
            val dataBundle = Bundle()
            dataBundle.putInt(Constant.SURAH_ID, chapterno)
            val item = SurahSummary()
            item.arguments = dataBundle
            SurahSummary.newInstance(chapterno).show(supportFragmentManager, NamesDetail.TAG)
        } else if (tag == "overflow_img") {
            @SuppressLint("RestrictedApi") val menuBuilder = MenuBuilder(this)
            val inflater = MenuInflater(this)
            inflater.inflate(R.menu.popup_menu, menuBuilder)
            @SuppressLint("RestrictedApi") val optionsMenu =
                MenuPopupHelper(this, menuBuilder, view)
            optionsMenu.setForceShowIcon(true)
// Set Item Click Listener
            menuBuilder.setCallback(object : MenuBuilder.Callback {
                override fun onMenuItemSelected(menu: MenuBuilder, item: MenuItem): Boolean {
                    if (item.itemId == R.id.actionTafsir) { // Handle option1 Click
                        val readingintent =
                            Intent(this@QuranGrammarAct, TafsirFullscreenActivity::class.java)
                        val chapterno = corpusayahWordArrayList!![position - 1].word[0].surahId
                        val verse = corpusayahWordArrayList!![position - 1].word[0].verseId

                        readingintent.putExtra(Constant.SURAH_ID, chapterno)
                        readingintent.putExtra(Constant.AYAH_ID, verse)
                        readingintent.putExtra(Constant.SURAH_ARABIC_NAME, surahArabicName)
                        startActivity(readingintent)
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.bookmark) { // Handle option2 Click
                        bookMarkSelected(position)
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.jumpto) { // Handle option2 Click
                        //  SurahAyahPicker();
                        initDialogComponents(position)
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.action_share) {
                        takeScreenShot(window.decorView)
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.ivHelp) { // Handle option2 Click
                        ParticleColorScheme.newInstance().show(
                            this@QuranGrammarAct.supportFragmentManager,
                            WordAnalysisBottomSheet.TAG
                                                              )
                        optionsMenu.dismiss()
                        return true
                    }
                    if (item.itemId == R.id.colorized) { // Handle option2 Click
                        if (colortag) {
                            val editor =
                                android.preference.PreferenceManager.getDefaultSharedPreferences(
                                    this@QuranGrammarAct
                                                                                                )
                                    .edit()
                            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                            editor.putBoolean("colortag", false)
                            editor.apply()
                            ReloadActivity()
                        } else {
                            val editor =
                                android.preference.PreferenceManager.getDefaultSharedPreferences(
                                    this@QuranGrammarAct
                                                                                                )
                                    .edit()
                            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                            editor.putBoolean("colortag", true)
                            editor.apply()
                            ReloadActivity()
                        }
                        optionsMenu.dismiss()
                        return true
                    }
                    return false
                }

                override fun onMenuModeChange(menu: MenuBuilder) {}
            })
            optionsMenu.show()
        } else if (tag == "help_img") {
            println("check")
            ParticleColorScheme.newInstance()
                .show(this@QuranGrammarAct.supportFragmentManager, WordAnalysisBottomSheet.TAG)
        } else if (tag == "qurantext") {
            val word: QuranEntity = if (position != 0) {
                allofQuran!![position - 1]
            } else {
                allofQuran!![position]
            }
            val dataBundle = Bundle()
            dataBundle.putInt(Constant.SURAH_ID, word.surah)
            dataBundle.putInt(Constant.AYAHNUMBER, Math.toIntExact(word.ayah.toLong()))
            LoadItemList(dataBundle, word)
        }
    }

    private fun takeScreenShot(view: View) {
        val date = Date()
        val format = DateFormat.format("MM-dd-yyyy_hh:mm:ss", date)
        try {
            val mainDir = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES), "FilShare"
                              )
            val path = "$mainDir/Mushafapplication-$format.jpeg"
            //    File zipfile = new File(getExternalFilesDir(null).getAbsolutePath() + getString(R.string.app_folder_path) + File.separator + DATABASEZIP);
            view.isDrawingCacheEnabled = true
            val color = Color.RED
            val bitmap = getBitmapFromView(view, color)
            val imageFile = File(path)
            val fileOutputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
            shareScreenShot(imageFile)
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getBitmapFromView(view: View, defaultColor: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.width, view.height, Bitmap.Config.ARGB_8888
                                        )
        val canvas = Canvas(bitmap)
        canvas.drawColor(defaultColor)
        view.draw(canvas)
        return bitmap
    }

    private fun shareScreenShot(imageFile: File) {
        val uri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider",
            imageFile
                                            )
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_TEXT, "Download Application from Instagram")
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        val resInfoList =
            packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                              )
        }
        //  startActivity(Intent.createChooser(intent, "Share PDF using.."));
        try {
            this.startActivity(Intent.createChooser(intent, "Share With"))
        }
        catch (e: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "No App Available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ReloadActivity() {
        Log.e(TAG, "onClick called")
        val intent =
            intent.putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(
                Constant.SURAH_ARABIC_NAME, surahArabicName
                                                                                                   )
                .putExtra("passages", mushafview)
                .putExtra(Constant.VERSESCOUNT, versescount).putExtra(Constant.RUKUCOUNT, rukucount)
                .putExtra(
                    Constant.MAKKI_MADANI, isMakkiMadani
                         )
        overridePendingTransition(0, 0)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
    }

    private fun bookMarkSelected(position: Int) {
        //  position = flowAyahWordAdapter.getAdapterposition();
        val chapterno = corpusayahWordArrayList!![position - 1].word[0].surahId
        val verse = corpusayahWordArrayList!![position - 1].word[0].verseId
        val en = BookMarks()
        en.header = "pins"
        en.chapterno = chapterno.toString()
        en.verseno = verse.toString()
        en.surahname = surahArabicName

        val viewmodel: QuranVIewModel by viewModels()

        viewmodel.Insertbookmark(en)
        val view = findViewById<View>(R.id.bookmark)
        val snackbar = Snackbar
            .make(view, "BookMark Created", Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setTextColor(Color.CYAN)
        snackbar.setBackgroundTint(Color.BLACK)
        snackbar.show()
    }

    private fun ReloadActivity(colorsentence: SwitchCompat) {
        Log.e(TAG, "onClick called")
        val intent =
            intent.putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(
                Constant.SURAH_ARABIC_NAME, surahArabicName
                                                                                                   )
                .putExtra(Constant.VERSESCOUNT, versescount).putExtra(Constant.RUKUCOUNT, rukucount)
                .putExtra(
                    Constant.MAKKI_MADANI, isMakkiMadani
                         )
        overridePendingTransition(0, 0)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
    }

    override fun onItemLongClick(position: Int, v: View) {
        //    Toast.makeText(this, "longclick", Toast.LENGTH_SHORT).show();
    }

    private fun initView() {

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        btnBottomSheet = binding.fab
        val verlayViewRecyclerView = findViewById<RecyclerView>(R.id.overlayViewRecyclerView)
        verlayViewRecyclerView.layoutManager = linearLayoutManager
        // bookmarkchip.setOnClickListener(v -> CheckStringLENGTHS());
    }

    private fun toggleBottomSheets() {
        if (bottomNavigationView.visibility == View.VISIBLE) {
            bottomNavigationView.visibility = View.GONE
            //    btnBottomSheet.setText("Close sheet");
        } else {
            bottomNavigationView.visibility = View.VISIBLE
            //    btnBottomSheet.setText("Expand sheet");
        }
    }

    override fun ondatarecevied(
        chapterno: Int,
        partname: String?,
        totalverses: Int,
        rukucount: Int,
        makkimadani: Int,
                               ) {
        Log.e(TAG, "onClick called")
        val intent =
            intent.putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(
                Constant.SURAH_ARABIC_NAME, partname
                                                                                                   )
                .putExtra(Constant.VERSESCOUNT, totalverses).putExtra(Constant.RUKUCOUNT, rukucount)
                .putExtra(
                    Constant.MAKKI_MADANI, makkimadani
                         )
        overridePendingTransition(0, 0)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
    }

    companion object {
        private const val TAG = "fragment"
    }
}


