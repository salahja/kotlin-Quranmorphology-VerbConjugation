package com.example.mushafconsolidated.Activity

import AudioPlayed
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.CHAPTER
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.mushafconsolidated.Activityimport.AyahCoordinate
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.Adapters.LineMushaAudioAdapter
import com.example.mushafconsolidated.Adapters.PageMushaAudioAdapter
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.Page
import com.example.mushafconsolidated.Entities.Qari
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.example.mushafconsolidated.receiversimport.AudioAppConstants
import com.example.mushafconsolidated.receiversimport.DownloadService
import com.example.mushafconsolidated.receiversimport.FileManager
import com.example.mushafconsolidated.receiversimport.QuranValidateSources
import com.example.mushafconsolidated.receiversimport.Settingsss
import com.example.mushafconsolidated.settingsimport.Constants
import com.example.utility.AudioPositionSaved
import com.example.utility.ConfigPreferences
import com.example.utility.FlowLayout
import com.example.utility.MovableFloatingActionButton
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.DiscontinuityReason
import com.google.android.exoplayer2.Player.MediaItemTransitionReason
import com.google.android.exoplayer2.Player.PositionInfo
import com.google.android.exoplayer2.Tracks
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.StyledPlayerView.FullscreenButtonClickListener
import com.google.android.exoplayer2.util.EventLogger
import com.google.android.exoplayer2.util.RepeatModeUtil
import com.google.android.exoplayer2.util.Util
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import wheel.OnWheelChangedListener
import wheel.WheelView
import java.io.File
import java.text.MessageFormat
import java.util.Locale
import java.util.Objects


class ShowMushafActivity : BaseActivity(), OnItemClickListenerOnLong, View.OnClickListener,
    FullscreenButtonClickListener {
    val isjuz = false
    lateinit var exo_settings: ImageButton
    lateinit var exo_close: ImageButton
    lateinit var exo_bottom_bar: ImageButton
    lateinit var ayaprogress: MaterialTextView
    private lateinit var surahWheelDisplayData: Array<String>
    private lateinit var ayahWheelDisplayData: Array<String>
    var versestartrange = 0
    var verseendrange = 0
    private var currenttrack = 0
    private var resumelastplayed = 0
    private var onClickOrRange = false
    lateinit var llStartRange: LinearLayout
    lateinit var llEndRange: LinearLayout

    //  private LinkedHashMap<Integer, Integer> hlights;
    private val hlights: LinkedHashMap<Int, ArrayList<AyahCoordinate>> =
        LinkedHashMap<Int, ArrayList<AyahCoordinate>>()
    var flow = false
    var singleline = false
    var rangeRecitation = false
    private lateinit var fullQuranPages: ArrayList<Page>
    private lateinit var juzquranpages: ArrayList<Page>
    private var resume = false
    private var ayahcounter = 0
    var ayah = 0
    lateinit var passageadapter: PageMushaAudioAdapter
    private val passage = LinkedHashMap<Int, String>()
    private val pages = LinkedHashMap<Int, String>()
    var audioType = 0
    var prevqari = ""
    private val handler = Handler()
    private lateinit var marray: MutableList<MediaItem>
    private var marrayrange: List<MediaItem>? = null

    //private val singleverse: String
    private var isSingle = false
    private var isStartFrom = false
    private var quranbySurahadapter: List<QuranEntity?>? = null

    //  private val resetplayer: MaterialButton
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var selectedqari: String
    lateinit var qariname: TextView
    lateinit var buffering: ImageView
    lateinit var canceldownload: MaterialButton

    //  FrameLayout eqContainer;


    protected lateinit var playerView: PlayerControlView
    protected var player: ExoPlayer? = null
    private lateinit var trackSelectionParameters: TrackSelectionParameters
    private lateinit var lastSeenTracks: Tracks
    private var startAutoPlay = false
    private var startItemIndex = 0
    private var startPosition: Long = 0
    lateinit var playiv: ImageView
    private var pausePlayFlag = false
    var surahselected = 0
    var verselected = 0
    var versescount = 0
    lateinit var surahNameEnglish: String
    lateinit var surahNameArabic: String
    private lateinit var isNightmode: String

    // LinearLayout fabLayout1, fabLayout2,fabLayout3;
    //  FloatingActionButton fab, fab1, fab2, fab3;
    lateinit var playfb: MovableFloatingActionButton

    // Use the ExtendedFloatingActionButton to handle the
    // These TextViews are taken to make visible and
    // invisible along with FABs except parent FAB's action
    // name
    lateinit var resetfbtxt: TextView
    override fun onBackPressed() {

        //unregister broadcast for download ayat
        LocalBroadcastManager.getInstance(this@ShowMushafActivity)
            .unregisterReceiver(downloadPageAya)
        //stop flag of auto start
        startBeforeDownload = false
        if (player != null) {
            player!!.release()
        }
        val pref: SharedPreferences = getSharedPreferences("lastaya", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("lastaya", currenttrack)
        if (!hlights.isEmpty()) {
            if (currenttrack == 0) {
                Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                    hlights[currenttrack + 1]
                )[0].passage!!?.let {
                    editor.putInt(
                        "trackposition", it
                    )
                }
            } else {
                hlights[currenttrack]!![0].passage!!?.let { editor.putInt("trackposition", it) }
            }
        }
        editor.apply()
        super.onBackPressed()
    }

    var isMusicplaying = false
    private var surah = 0
    lateinit var recyclerView: RecyclerView
    lateinit var repository: Utils
    lateinit var lineMushaAudioAdapter: LineMushaAudioAdapter
    lateinit var typeface: Typeface
    lateinit var txtView: TextView

    //   val translationBooks: Spinner = TODO()
    private lateinit var readers: Spinner
    private lateinit var downloadFooter: RelativeLayout
    private lateinit var normalFooter: LinearLayout
    private lateinit var playerFooter: RelativeLayout
    private lateinit var audio_settings_bottom: RelativeLayout

    //  TextView startrange, startimage, endrange, endimage;
    lateinit var startrange: MaterialTextView
    lateinit var endrange: MaterialTextView
    fun setStartPosition(startPosition: Long) {
        this.startPosition = startPosition
    }

    //  private List<TranslationBook> booksInfo;
    private lateinit var readersList: List<Qari>
    private lateinit var mediaPlayerDownloadProgress: ProgressBar
    private lateinit var exoplayerBottomBehaviour: BottomSheetBehavior<RelativeLayout>
    private lateinit var audioSettingBottomBehaviour: BottomSheetBehavior<RelativeLayout>
    lateinit var resetfab: FloatingActionButton
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vfour_expandable_newactivity_show_ayahs)
        //    ButterKnife.bind(this);
        //    QuranGrammarApplication.appContext = ShowMushafActivity.this;
        //  intentmyservice = new Intent(this, AudioService.class);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val intent = Intent(BROADCAST_SEEKBAR)
        getpreferences()
        //   lastPlayed
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        isNightmode = sharedPreferences.getString("themepref", "dark")!!
        //  repository = Utils.getInstance(getApplication());
        repository = Utils(this)
        typeface = Typeface.createFromAsset(getAssets(), "me_quran.ttf")
        selectedqari = sharedPreferences.getString("qari", "35")!!

        //region Description
        if (getIntent().hasExtra(Constants.SURAH_INDEX)) {
            surah = getIntent().getIntExtra(Constants.SURAH_INDEX, 1)
            singleline = getIntent().getBooleanExtra(Constants.MUSHAFDISPLAY, false)
            surahselected = surah
            //   getIntent().getIntExtra(Constants.SURAH_GO_INDEX, 1);
            val ayahtrack: Int = getIntent().getIntExtra(Constants.AYAH_GO_INDEX, 0)
            if (ayahtrack > 0) {
                setStartPosition(ayahtrack.toLong())
            }
            Log.d(TAG, "onCreate: ayah  $ayah")

            //       showMessage(String.valueOf(pos));D
        }
        //endregion
        playerView = findViewById(R.id.player_view)
        playerView!!.requestFocus()
        if (savedInstanceState != null) {
            trackSelectionParameters = TrackSelectionParameters.fromBundle(
                savedInstanceState.getBundle(KEY_TRACK_SELECTION_PARAMETERS)!!
            )
            startAutoPlay = savedInstanceState.getBoolean(KEY_AUTO_PLAY)
            startItemIndex = savedInstanceState.getInt(KEY_ITEM_INDEX)
            startPosition = savedInstanceState.getLong(KEY_POSITION)
        } else {
            trackSelectionParameters = TrackSelectionParameters.Builder( /* context= */this).build()
            clearStartPosition()
        }
        val bottomsheetexoplayer: RelativeLayout = findViewById(R.id.footerplayer)
        exoplayerBottomBehaviour = BottomSheetBehavior.from(bottomsheetexoplayer)
        exoplayerBottomBehaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
        val playerbottomsheet: RelativeLayout = findViewById(R.id.audio_settings_bottom)
        audioSettingBottomBehaviour = BottomSheetBehavior.from(playerbottomsheet)
        audioSettingBottomBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
        recyclerView = findViewById(R.id.rvAyahsPages) as RecyclerView
        initSpinner()
        if (!singleline) {
            if (!isjuz) {
                loadFullQuran()
                preparesSurahHlights()
            } else {
                loadQuranjuz()
                preparesJuzHlights()
                println("check")
            }
        }
        initRV()
    }

    private fun getpreferences() {
        val pref: SharedPreferences =
            getApplicationContext().getSharedPreferences("lastreadmushaf", MODE_PRIVATE)
        surah = pref.getInt(CHAPTER, 20)
        surahselected = surah
    }

    private val lastPlayed: Unit
        private get() {
            val aplayed: AudioPositionSaved =
                ConfigPreferences.getLastPlayedAudio(this, surah.toString())
            resumelastplayed = aplayed.audiopsaved!!.get(0)!!.ayah
        }

    private fun loadQuranjuz() {
        val pages: MutableList<Page> = ArrayList()
        //  List<QuranEntity?>? quranEntities = Utils.getQuranbySurah(surah);
        val quranEntities: List<QuranEntity?>? = Utils.getQuranbyJuz(3)
        val firstpage = quranEntities?.get(0)!!.page
        lateinit var page: Page
        var ayahItems: List<QuranEntity?>?
        for (i in firstpage..quranEntities?.get(quranEntities.size - 1)!!.page) {
            // ayahItems = repository.getAyahsByPageQuran(surah, i);
            ayahItems = Utils.getAyahsByPagejuz(2, i) as List<QuranEntity?>?
            if (ayahItems!!.size > 0) {
                page = Page()
                page.ayahItemsquran = ayahItems
                //    page.se(ayahItems);
                page.pageNum = i
                page.juz = ayahItems[0]!!.juz
                pages.add(page)
            }
        }
        juzquranpages = ArrayList(pages)
    }

    private fun loadFullQuran() {
        val pages: MutableList<Page> = ArrayList()
        val quranEntities: List<QuranEntity?>? = repository.getQuranbySurah(surah)
        val firstpage = quranEntities?.get(0)!!.page
        lateinit var page: Page
        var ayahItems: List<QuranEntity?>?
        for (i in firstpage..quranEntities?.get(quranEntities.size - 1)!!.page) {
            ayahItems = repository.getAyahsByPageQuran(surah, i)
            if (ayahItems != null) {
                if (ayahItems.size > 0) {
                    page = Page()
                    page.ayahItemsquran = ayahItems
                    //    page.se(ayahItems);
                    page.pageNum = i
                    page.juz = ayahItems[0]!!.juz
                    pages.add(page)
                }
            }
        }
        fullQuranPages = ArrayList(pages)
    }

    private fun preparesJuzHlights() {
        var counter = 1
        for (i in juzquranpages!!.indices) {
            val page = juzquranpages!![i]
            var aya = ""
            var builder = java.lang.StringBuilder()
            var ayahmat = java.util.ArrayList<Int>()
            for (ayahItem in page.ayahItemsquran!!) {
                aya = ayahItem!!.qurantext
                builder.append(MessageFormat.format("{0} ﴿ {1} ﴾ ", aya, ayahItem.ayah))
                ayahmat.add(ayahItem.ayah)
            }
            JuzpreparehighlightsNew(counter, builder, ayahmat, ayahcounter++)
            ayahmat = java.util.ArrayList()
            builder = java.lang.StringBuilder()
            counter++
        }
    }

    private fun JuzpreparehighlightsNew(
        passageno: Int,
        str: StringBuilder,
        ayahmat: ArrayList<Int>,
        ayahcounter: Int,
    ) {
        var ayahcounter = ayahcounter
        val holder = recyclerView!!.findViewHolderForAdapterPosition(1)
        val ayahindex = ayahmat[0]
        val ayahmaz = ayahmat.size
        val split1 = str.toString().split("﴿".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val start = 0
        //  = str.indexOf("1");
        val end = str.indexOf(ayahindex.toString())
        val acf = AyahCoordinate(0, end, passageno)
        val Coordinatesf: ArrayList<AyahCoordinate> = ArrayList<AyahCoordinate>()
        Coordinatesf.add(acf)
        hlights[ayahcounter] = Coordinatesf
        //  ayahindex++;
        for (i in split1.indices) {
            val s = str.indexOf(ayahindex.toString())
            val e = str.indexOf((ayahindex + 1).toString())
            if (s != -1 && e != -1) {
                val ac = AyahCoordinate(s, e, passageno)
                val Coordinates: ArrayList<AyahCoordinate> = ArrayList<AyahCoordinate>()
                Coordinates.add(ac)
                hlights[++ayahcounter] = Coordinates
            } else {
                println("$s $e")
            }
        }
        println("check")
    }

    private fun preparesSurahHlights() {
        var counter = 1
        for (i in fullQuranPages!!.indices) {
            val page = fullQuranPages!![i]
            var aya = ""
            var builder = java.lang.StringBuilder()
            var ayahmat = java.util.ArrayList<Int>()
            for (ayahItem in page.ayahItemsquran!!) {
                if (ayahItem != null) {
                    aya = ayahItem.qurantext
                }
                if (ayahItem != null) {
                    builder.append(MessageFormat.format("{0} ﴿ {1} ﴾ ", aya, ayahItem.ayah))
                }
                ayahmat.add(ayahItem!!.ayah)
            }
            preparehighlightsNew(counter, builder, ayahmat)
            ayahmat = java.util.ArrayList()
            builder = java.lang.StringBuilder()
            counter++
        }
    }

    private fun initSpinner() {
        readers = (findViewById(R.id.selectReaders) as Spinner?)!!
        readers!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long,
            ) {
                readerName = readers!!.selectedItem.toString()
                getReaderAudioLink(readerName)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        readers!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long,
            ) {
                readerName = readers!!.selectedItem.toString()
                getReaderAudioLink(readerName)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        runOnUiThread(Runnable { //check language to load readers arabic or english
            val readersNames: MutableList<String> = ArrayList()
            readersList = repository.qaris
            for (reader in readersList!!) {
                if (reader.audiotype == 0 || reader.audiotype == 2) {
                    readersNames.add(reader.name_english)
                } /*else {
                            readersNames.add(reader.getName_english());


                        }*/
            }

            //add custom spinner adapter for readers
            val spinnerReaderAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this@ShowMushafActivity,
                R.layout.spinner_layout_larg,
                R.id.spinnerText,
                readersNames
            )
            readers!!.adapter = spinnerReaderAdapter
            for (counter in readersNames.indices) {
                if (readersNames[counter].trim { it <= ' ' } == selectedqari!!.trim { it <= ' ' }) {
                    readers!!.setSelection(counter)
                    break
                }
            }
        })
    }


    fun SurahAyahPicker(isrefresh: Boolean, starttrue: Boolean) {
        val rangevalues = ArrayList<Int>()
        val mTextView: TextView
        val chapterWheel: WheelView
        val verseWheel: WheelView
        lateinit var wvDay: WheelView
        val utils = Utils(this@ShowMushafActivity)
        val mYear = arrayOf(arrayOfNulls<String>(1))
        val mMonth = arrayOfNulls<String>(1)
        surahWheelDisplayData = arrayOf("")
        ayahWheelDisplayData = arrayOf("")
        //  val current = arrayOf<ArrayList<Any>>(ArrayList<Any>())
        var current = ArrayList<String>()
        var mDay: Int
        val chapterno = IntArray(1)
        val verseno = IntArray(1)
        val surahArrays: Array<String> = getResources().getStringArray(R.array.surahdetails)
        val versearrays: Array<String> = getResources().getStringArray(R.array.versescounts)
        val intarrays: IntArray = getResources().getIntArray(R.array.versescount)
        //     final AlertDialog.Builder dialogPicker = new AlertDialog.Builder(this);
        val dialogPicker = AlertDialog.Builder(this@ShowMushafActivity)
        val dlg = Dialog(this@ShowMushafActivity)
        //  AlertDialog dialog = builder.create();
        val soraList: List<ChaptersAnaEntity?>? = repository.getAllAnaChapters()
        val inflater: LayoutInflater = this@ShowMushafActivity.getLayoutInflater()
        val view = inflater.inflate(R.layout.activity_wheel_t, null)
        //  View view = inflater.inflate(R.layout.activity_wheel, null);
        dialogPicker.setView(view)
        mTextView = view.findViewById(R.id.textView2)
        chapterWheel = view.findViewById(R.id.wv_year)
        verseWheel = view.findViewById(R.id.wv_month)
        chapterWheel.setEntries(*surahArrays)
        chapterWheel.currentIndex = surahselected - 1
        //set wheel initial state
        val initial = true
        if (initial) {
            val text = chapterWheel.getItem(surahselected - 1) as String
            surahWheelDisplayData[0] = text
            val chapno = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            chapterno[0] = chapno[0].toInt()
            verseno[0] = 1
            //     current[0] = ArrayList<Any>()
            val intarray: Int
            intarray = if (surahselected != 0) {
                intarrays[surahselected - 1]
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

//        wvDay = (WheelView) view.findViewById(R.id.wv_day);
        val currentsurahVersescount: Array<String>
        val vcount = versearrays[surahselected - 1].toInt()
        for (i in 1..vcount) {
            current.add(i.toString())
        }
        verseWheel.setEntriesv(current)
        verseWheel.currentIndex = ayah
        dialogPicker.setPositiveButton("Done") { dialogInterface: DialogInterface?, i: Int ->
            var sura = ""

            //   setSurahArabicName(suraNumber + "-" + soraList.get(suraNumber - 1).getNameenglish() + "-" + soraList.get(suraNumber - 1).getAbjadname());
            if (chapterno[0] == 0) {
                surahselected = surah
            } else {
                sura = soraList!![chapterno[0] - 1]!!.chapterid.toString()
                surahselected = soraList[chapterno[0] - 1]!!.chapterid
                surahNameEnglish = soraList[chapterno[0] - 1]!!.nameenglish
                surahNameArabic = soraList[chapterno[0] - 1]!!.namearabic
                val pref: SharedPreferences = getSharedPreferences("lastreadmushaf", MODE_PRIVATE)
                val editor = pref.edit()
                editor.putInt(CHAPTER, sura.toInt())
                //  editor.putInt("page", page.getAyahItemsquran().get(0).getPage());
                editor.putString(SURAH_ARABIC_NAME, soraList[chapterno[0] - 1]!!.namearabic)
                editor.apply()
            }
            val verse = verseno[0]
            ayah = verse
            val aya = verseno[0].toString()
            if (isrefresh && starttrue) {
                releasePlayer()
                RefreshActivity(sura, aya, false)
            } else if (starttrue) {
                updateStartRange(verse)
                rangevalues.add(verse)
            } else {
                updateEndRange(verse)
                rangevalues.add(verse)
            }
        }
        dialogPicker.setNegativeButton(
            "Cancel"
        ) { dialogInterface: DialogInterface?, i: Int -> }
        val alertDialog = dialogPicker.create()
        val preferences = sharedPreferences!!.getString("themepref", "dark")
        val db = ContextCompat.getColor(this, R.color.odd_item_bg_dark_blue_light)
        if (preferences == "light") {
            alertDialog.window!!.setBackgroundDrawableResource(R.color.md_theme_dark_onSecondary)
            //   alertDialog.getWindow().setBackgroundDrawableResource(R.color.md_theme_dark_onTertiary);

            //
        } else if (preferences == "brown") {
            alertDialog.window!!.setBackgroundDrawableResource(R.color.background_color_light_brown)
            //  cardview.setCardBackgroundColor(ORANGE100);
        } else if (preferences == "blue") {
            alertDialog.window!!.setBackgroundDrawableResource(R.color.prussianblue)
            //  cardview.setCardBackgroundColor(db);
        } else if (preferences == "green") {
            alertDialog.window!!.setBackgroundDrawableResource(R.color.mdgreen_theme_dark_onPrimary)
            //  cardview.setCardBackgroundColor(MUSLIMMATE);
        }
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        //   alertDialog.show();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val wmlp = alertDialog.window!!.attributes
        alertDialog.show()
        val buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        buttonPositive.setTextColor(ContextCompat.getColor(this@ShowMushafActivity, R.color.green))
        val buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        buttonNegative.setTextColor(ContextCompat.getColor(this@ShowMushafActivity, R.color.red))
        if (preferences == "light") {
            buttonPositive.setTextColor(
                ContextCompat.getColor(
                    this@ShowMushafActivity,
                    R.color.colorMuslimMate
                )
            )
            buttonNegative.setTextColor(
                ContextCompat.getColor(
                    this@ShowMushafActivity,
                    R.color.red
                )
            )
        } else if (preferences == "brown") {
            buttonPositive.setTextColor(
                ContextCompat.getColor(
                    this@ShowMushafActivity,
                    R.color.colorMuslimMate
                )
            )
            buttonNegative.setTextColor(
                ContextCompat.getColor(
                    this@ShowMushafActivity,
                    R.color.red
                )
            )
            //  cardview.setCardBackgroundColor(ORANGE100);
        } else if (preferences == "blue") {
            buttonPositive.setTextColor(
                ContextCompat.getColor(
                    this@ShowMushafActivity,
                    R.color.yellow
                )
            )
            buttonNegative.setTextColor(
                ContextCompat.getColor(
                    this@ShowMushafActivity,
                    R.color.Goldenrod
                )
            )
            //  cardview.setCardBackgroundColor(db);
        } else if (preferences == "green") {
            buttonPositive.setTextColor(
                ContextCompat.getColor(
                    this@ShowMushafActivity,
                    R.color.yellow
                )
            )
            buttonNegative.setTextColor(
                ContextCompat.getColor(
                    this@ShowMushafActivity,
                    R.color.cyan_light
                )
            )
            //  cardview.setCardBackgroundColor(MUSLIMMATE);
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
                //     current[0] = java.util.ArrayList<Any>()
                val intarray: Int
                intarray = if (newIndex != 0) {
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

    private fun updateEndRange(verse: Int) {
        val st = StringBuilder()
        st.append(surahNameEnglish).append("-").append(surahselected).append(":").append(
            ayah
        )
        verseendrange = verse
        endrange!!.text = st.toString()
        rangeRecitation = true
    }

    private fun updateStartRange(verse: Int) {
        val st = StringBuilder()
        val stt = StringBuilder()
        st.append(surahNameEnglish).append("-").append(surahselected).append(":").append(
            ayah
        )
        versestartrange = verse
        startrange!!.text = st.toString()
        rangeRecitation = true
    }

    private fun RefreshActivity(s: String, aya: String, b: Boolean) {
        Log.e(TAG, "onClick called")
        val intent: Intent = this.getIntent()
        //  surah = getIntent().getIntExtra(Constants.SURAH_INDEX, 1);
        val parentActivityRef = intent.getStringExtra("PARENT_ACTIVITY_REF")
        if (b) {
            intent.putExtra(Constants.MUSHAFDISPLAY, true)
            intent.putExtra(Constants.SURAH_INDEX, surah)
        } else if (s.isEmpty()) {
            intent.putExtra(Constants.MUSHAFDISPLAY, false)
            intent.putExtra(Constants.SURAH_INDEX, surah)
        } else {
            intent.putExtra(Constants.SURAH_INDEX, s.toInt())
            intent.putExtra(Constants.AYAH_GO_INDEX, aya.toInt())
        }
        this.overridePendingTransition(0, 0)
        startActivity(intent)
        this.finish()
        this.overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
    }

    private val sendUpdatesToUI: Runnable = object : Runnable {
        override fun run() {
            //  rvAyahsPages.post(() -> rvAyahsPages.scrollToPosition((ayah)));
///musincadapter
            // RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) rvAyahsPages.findViewHolderForAdapterPosition(currenttrack);
            val holder = recyclerView!!.findViewHolderForAdapterPosition(currenttrack)
            val ab = StringBuilder()
            ab.append("Aya").append(":").append(currenttrack).append(" ").append("of").append(
                versescount
            )
            ayaprogress!!.text = ab.toString()
            if (null != holder) {
                try {
                    if (holder.itemView.findViewById<View?>(R.id.quran_textView) != null) {
                        if (isNightmode == "light") {
                            holder.itemView.findViewById<View>(R.id.quran_textView)
                                .setBackgroundColor(
                                    Color.LTGRAY
                                )
                            val textViews =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            val str = textViews.text.toString()
                            val span = SpannableStringBuilder(str)
                            span.setSpan(
                                ForegroundColorSpan(Color.CYAN),
                                0,
                                str.length,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                        } else if (isNightmode == "brown") {
                            holder.itemView.findViewById<View>(R.id.quran_textView)
                                .setBackgroundColor(
                                    Color.CYAN
                                )
                            val textViews =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            val str = textViews.text.toString()
                            val span = SpannableStringBuilder(str)
                            span.setSpan(
                                ForegroundColorSpan(Color.CYAN),
                                0,
                                str.length,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                        } else {
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            textView.text
                            val strs = textView.text.toString()
                            val spans = SpannableStringBuilder(strs)
                            spans.setSpan(
                                BackgroundColorSpan(Color.BLUE),
                                0,
                                strs.length,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            textView.text = spans
                            //  holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.BLUE);
                            //for vtwoadapter
                        }
                    }
                } catch (exception: NullPointerException) {
                    Toast.makeText(
                        this@ShowMushafActivity,
                        "null pointer udapte",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            val holderp = recyclerView!!.findViewHolderForAdapterPosition(currenttrack - 1)
            if (currenttrack > 1) {
                if (null != holderp) {
                    try {
                        val arrayList = ArrayList<String>()
                        val fl: FlowLayout = FlowLayout(this@ShowMushafActivity, arrayList)
                        val arrayList1 = fl.arrayList
                        fl.getChildAt(ayah)
                        val drawingCacheBackgroundColor =
                            holderp.itemView.findViewById<View>(R.id.quran_textView).drawingCacheBackgroundColor
                        if (holderp.itemView.findViewById<View?>(R.id.quran_textView) != null) {
                            //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                            holderp.itemView.findViewById<View>(R.id.quran_textView)
                                .setBackgroundColor(drawingCacheBackgroundColor)
                        }
                    } catch (exception: NullPointerException) {
                        Toast.makeText(
                            this@ShowMushafActivity,
                            "UPDATE HIGHLIGHTED",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            recyclerView!!.post { recyclerView!!.scrollToPosition(currenttrack) }

            //  handler.postDelayed(this, 1000);
        }

        private fun Highlightverse(textView: TextView) {
            val start: Int
            val end: Int
            val starta: String
            val endb: String
            val str = textView.text.toString()
            val split1 = str.split("﴿".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            val span = SpannableStringBuilder(str)
            if (currenttrack == 1) {
                start = 0
                endb = currenttrack.toString()
            } else {
                starta = currenttrack.toString()
                start = str.indexOf(starta)
                endb = (currenttrack + 1).toString()
            }
            end = str.indexOf(endb)
            try {
                println(span.subSequence(start, end))
                span.setSpan(
                    ForegroundColorSpan(Color.CYAN),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.text = span
                val split = str.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            } catch (exception: IndexOutOfBoundsException) {
                println(exception)
            }
        }
    }
    private val picker: Runnable = object : Runnable {
        val trackchange = false

        // int currentAdapterP=hlights.get(currenttrack-1).get(0).passage;
        override fun run() {

            //  rvAyahsPages.post(() -> rvAyahsPages.scrollToPosition((ayah)));
            handler.postDelayed(this, 1500)
        }

        private fun setVerseHighLight(textView: TextView, foreGroundcoloer: Int) {
            val str = textView.text.toString()
            val span = SpannableStringBuilder(str)
            try {
                span.setSpan(
                    ForegroundColorSpan(foreGroundcoloer),
                    hlights[currenttrack]!![0].start!!,
                    hlights[currenttrack]!![0].end!!,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.text = span
                val split = str.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            } catch (exception: IndexOutOfBoundsException) {
                println(exception)
            }
        }
    }
    private val NewsendUpdatesToUIPassage: Runnable = object : Runnable {
        val trackchange = false

        // int currentAdapterP=hlights.get(currenttrack-1).get(0).passage;
        override fun run() {
            lateinit var holder: RecyclerView.ViewHolder
            if (onClickOrRange) {
                if (hlights[currenttrack] != null) {
                    holder = recyclerView!!.findViewHolderForAdapterPosition(
                        hlights[versestartrange]!![0].passage!!!!
                    )!!
                    recyclerView!!.post {
                        recyclerView!!.scrollToPosition(
                            hlights[versestartrange]!![0].passage!!!!
                        )
                    }
                } else {
                    holder = hlights[currenttrack + 1]!![0].passage!!?.let {
                        recyclerView!!.findViewHolderForAdapterPosition(
                            it
                        )
                    }!!
                    recyclerView!!.post {
                        hlights[currenttrack + 1]?.get(0)?.let {
                            recyclerView!!.scrollToPosition(
                                it.passage!!
                            )
                        }
                    }
                    currenttrack++
                }
            } else {
                if (hlights[currenttrack] != null) {
                } else {
                }
            }
            if (null != holder) {
                try {
                    if (holder.itemView.findViewById<View?>(R.id.quran_textView) != null) {
                        if (isNightmode == "brown") {

                            //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            setVerseHighLight(textView, Color.BLUE)
                        } else {
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            setVerseHighLight(textView, Color.CYAN)

                            //   holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Constant.MUSLIMMATE);
                        }
                    } else if (holder.itemView.findViewById<View?>(R.id.rukuview) != null) {
                        println("rukuvue")
                    }
                } catch (exception: NullPointerException) {
                    Toast.makeText(
                        this@ShowMushafActivity,
                        "null pointer udapte",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            val holderp = recyclerView!!.findViewHolderForAdapterPosition(1)
            val temp = 2
            if (null != holderp) {
                try {
                    val arrayList = ArrayList<String>()
                    val fl: FlowLayout = FlowLayout(this@ShowMushafActivity, arrayList)
                    fl.getChildAt(ayah)
                    val drawingCacheBackgroundColor =
                        holderp.itemView.findViewById<View>(R.id.quran_textView).drawingCacheBackgroundColor
                    if (holderp.itemView.findViewById<View?>(R.id.quran_textView) != null) {
                        //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                        holderp.itemView.findViewById<View>(R.id.quran_textView)
                            .setBackgroundColor(drawingCacheBackgroundColor)
                    }
                } catch (exception: NullPointerException) {
                    Toast.makeText(
                        this@ShowMushafActivity,
                        "UPDATE HIGHLIGHTED",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            //  rvAyahsPages.post(() -> rvAyahsPages.scrollToPosition((ayah)));
            handler.postDelayed(this, 2000)
        }

        private fun setVerseHighLight(textView: TextView, foreGroundcoloer: Int) {
            val str = textView.text.toString()
            val span = SpannableStringBuilder(str)
            try {
                span.setSpan(
                    ForegroundColorSpan(foreGroundcoloer),
                    Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack]
                    )[0].start!!,
                    Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack]
                    )[0].end!!,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.text = span
                val split = str.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            } catch (exception: IndexOutOfBoundsException) {
                println(exception)
            }
        }
    }
    private val sendUpdatesToUIJuz: Runnable = object : Runnable {
        val trackchange = false

        // int currentAdapterP=hlights.get(currenttrack-1).get(0).passage;
        override fun run() {
            val holder: RecyclerView.ViewHolder
            val ab = StringBuilder()
            ab.append("Aya").append(":").append(currenttrack).append(" ").append("of").append(
                versescount
            )
            ayaprogress!!.text = ab.toString()
            if (hlights[currenttrack] != null) {
                holder = recyclerView!!.findViewHolderForAdapterPosition(
                    Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack - 1]
                    )[0].passage!!!!
                )!!
                if (currenttrack < Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[hlights.size - 1]
                    )[0].passage!!!!
                ) recyclerView!!.post {
                    recyclerView!!.scrollToPosition(
                        Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                            hlights[currenttrack - 1]
                        )[0].passage!!!!
                    )
                } else {
                    recyclerView!!.post {
                        recyclerView!!.scrollToPosition(
                            Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                                hlights[currenttrack]
                            )[0].passage!!!!
                        )
                    }
                }
            } else {
                holder = recyclerView!!.findViewHolderForAdapterPosition(
                    Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack + 1]
                    )[0].passage!!!!
                )!!
                if (currenttrack < Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[hlights.size - 1]
                    )[0].passage!!!!
                ) recyclerView!!.post {
                    recyclerView!!.scrollToPosition(
                        Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                            hlights[currenttrack + 1]
                        )[0].passage!!!!
                    )
                }
                currenttrack++
            }
            if (null != holder) {
                try {
                    val drawingCacheBackgroundColor =
                        holder.itemView.findViewById<View>(R.id.rukuview).drawingCacheBackgroundColor
                    if (holder.itemView.findViewById<View?>(R.id.quran_textView) != null) {
                        if (isNightmode == "light") {

                            //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            val odd_item_bg_brown = R.color.odd_item_bg_brown
                            setVerseHighLight(textView, Color.RED)
                        } else if (isNightmode == "brown") {

                            //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            setVerseHighLight(textView, Color.BLUE)
                        } else {
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            setVerseHighLight(textView, Color.CYAN)

                            //   holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Constant.MUSLIMMATE);
                        }
                    } else if (holder.itemView.findViewById<View?>(R.id.rukuview) != null) {
                        println("rukuvue")
                    }
                } catch (exception: NullPointerException) {
                    Toast.makeText(
                        this@ShowMushafActivity,
                        "null pointer udapte",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            lateinit var holderp: RecyclerView.ViewHolder
            if (currenttrack > 1) {
                val pos: Int = Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                    hlights[currenttrack - 1]
                )[0].passage!!
                holderp = recyclerView!!.findViewHolderForAdapterPosition(pos)!!
            }
            //   RecyclerView.ViewHolder holderp = (RecyclerView.ViewHolder) recyclerView.findViewHolderForAdapterPosition(1);
            val temp = 2
            if (null != holderp) {
                try {
                    val arrayList = ArrayList<String>()
                    val fl: FlowLayout = FlowLayout(this@ShowMushafActivity, arrayList)
                    val arrayList1 = fl.arrayList
                    fl.getChildAt(ayah)
                    val drawingCacheBackgroundColor =
                        holderp.itemView.findViewById<View>(R.id.quran_textView).drawingCacheBackgroundColor
                    if (holderp.itemView.findViewById<View?>(R.id.quran_textView) != null) {
                        //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                        holderp.itemView.findViewById<View>(R.id.quran_textView)
                            .setBackgroundColor(drawingCacheBackgroundColor)
                    }
                } catch (exception: NullPointerException) {
                    Toast.makeText(
                        this@ShowMushafActivity,
                        "UPDATE HIGHLIGHTED",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            //  rvAyahsPages.post(() -> rvAyahsPages.scrollToPosition((ayah)));
            handler.postDelayed(this, 5000)
        }

        private fun setVerseHighLight(textView: TextView, foreGroundcoloer: Int) {
            val str = textView.text.toString()
            val span = SpannableStringBuilder(str)
            try {
                span.setSpan(
                    ForegroundColorSpan(foreGroundcoloer),
                    hlights[currenttrack]!![0].start!!,
                    hlights[currenttrack]!![0].end!!,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.text = span
                val split = str.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            } catch (exception: java.lang.IndexOutOfBoundsException) {
                println(exception)
            }
        }

        private fun Highlightverse(textView: TextView) {
            val start: Int
            val end: Int
            val starta: String
            val endb: String
            val str = textView.text.toString()
            val split1 = str.split("﴿".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            val span = SpannableStringBuilder(str)
            if (currenttrack == 1) {
                start = 0
                endb = currenttrack.toString()
            } else {
                starta = currenttrack.toString()
                start = str.indexOf(starta)
                endb = (currenttrack + 1).toString()
            }
            end = str.indexOf(endb)
            try {
                println(span.subSequence(start, end))
                span.setSpan(
                    ForegroundColorSpan(Color.CYAN),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.text = span
                val split = str.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            } catch (exception: IndexOutOfBoundsException) {
                println(exception)
            }
        }
    }
    private val sendUpdatesToUIPassage: Runnable = object : Runnable {
        val trackchange = false

        // int currentAdapterP=hlights.get(currenttrack-1).get(0).passage;
        override fun run() {
            val holder: RecyclerView.ViewHolder?
            val ab = StringBuilder()
            ab.append("Aya").append(":").append(currenttrack).append(" ").append("of").append(
                versescount
            )
            ayaprogress!!.text = ab.toString()
            if (hlights[currenttrack] != null) {
                holder = recyclerView!!.findViewHolderForAdapterPosition(
                    Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack]
                    )[0].passage!!!!
                )
                if (currenttrack < Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[hlights.size]
                    )[0].passage!!
                ) recyclerView!!.post {
                    recyclerView!!.scrollToPosition(
                        Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                            hlights[currenttrack + 1]
                        )[0].passage!!
                    )
                } else {
                    recyclerView!!.post {
                        recyclerView!!.scrollToPosition(
                            Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                                hlights[currenttrack]
                            )[0].passage!!
                        )
                    }
                }
            } else {
                holder = recyclerView!!.findViewHolderForAdapterPosition(
                    Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack + 1]
                    )[0].passage!!
                )
                if (currenttrack < Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[hlights.size]
                    )[0].passage!!
                ) recyclerView!!.post {
                    recyclerView!!.scrollToPosition(
                        Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                            hlights[currenttrack + 1]
                        )[0].passage!!
                    )
                }
                currenttrack++
            }
            if (null != holder) {
                try {
                    //      int drawingCacheBackgroundColor = holder.itemView.findViewById(R.id.rukuview).getDrawingCacheBackgroundColor();
                    if (holder.itemView.findViewById<View?>(R.id.quran_textView) != null) {
                        if (isNightmode == "light") {

                            //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            val odd_item_bg_brown = R.color.odd_item_bg_brown
                            setVerseHighLight(textView, Color.RED)
                        } else if (isNightmode == "brown") {

                            //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            setVerseHighLight(textView, Color.BLUE)
                        } else {
                            val textView =
                                holder.itemView.findViewById<TextView>(R.id.quran_textView)
                            setVerseHighLight(textView, Color.CYAN)

                            //   holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Constant.MUSLIMMATE);
                        }
                    } else if (holder.itemView.findViewById<View?>(R.id.rukuview) != null) {
                        println("rukuvue")
                    }
                } catch (exception: NullPointerException) {
                    Toast.makeText(
                        this@ShowMushafActivity,
                        "null pointer udapte",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            var holderp: RecyclerView.ViewHolder? = null
            if (currenttrack > 1) {
                val pos: Int = Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                    hlights[currenttrack - 1]
                )[0].passage!!
                holderp = recyclerView!!.findViewHolderForAdapterPosition(pos)!!
            }
            //   RecyclerView.ViewHolder holderp = (RecyclerView.ViewHolder) recyclerView.findViewHolderForAdapterPosition(1);
            val temp = 2
            if (null != holderp) {
                try {
                    val arrayList = ArrayList<String>()
                    val fl: FlowLayout = FlowLayout(this@ShowMushafActivity, arrayList)
                    val arrayList1 = fl.arrayList
                    fl.getChildAt(ayah)
                    val drawingCacheBackgroundColor =
                        holderp.itemView.findViewById<View>(R.id.quran_textView).drawingCacheBackgroundColor
                    if (holderp.itemView.findViewById<View?>(R.id.quran_textView) != null) {
                        //    holder.itemView.findViewById(R.id.quran_textView).setBackgroundColor(Color.CYAN);
                        holderp.itemView.findViewById<View>(R.id.quran_textView)
                            .setBackgroundColor(drawingCacheBackgroundColor)
                    }
                } catch (exception: NullPointerException) {
                    Toast.makeText(
                        this@ShowMushafActivity,
                        "UPDATE HIGHLIGHTED",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            //  rvAyahsPages.post(() -> rvAyahsPages.scrollToPosition((ayah)));
            handler.postDelayed(this, 5000)
        }

        private fun setVerseHighLight(textView: TextView, foreGroundcoloer: Int) {
            val str = textView.text.toString()
            val span = SpannableStringBuilder(str)
            try {
                span.setSpan(
                    ForegroundColorSpan(foreGroundcoloer),
                    Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack]
                    )[0].start!!,
                    Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack]
                    )[0].end!!,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.text = span
                val split = str.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            } catch (exception: IndexOutOfBoundsException) {
                println(exception)
            }
        }
    }

    protected fun releasePlayer() {
        if (player != null) {
            updateTrackSelectorParameters()
            updateStartPosition()
            player!!.release()
            player = null
            playerView!!.player = null
            val mediaItems = emptyList<MediaItem>()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            //      this.releasePlayer();
        }
    }

    protected fun initializePlayer() {
        if (isMusicplaying) {
            releasePlayer()
        }
        if (player == null) {
            playerFooter!!.visibility = View.VISIBLE
            normalFooter!!.visibility = View.GONE
            downloadFooter!!.visibility = View.GONE
            val stream = false
            val playbackPosition = 0L
            //      myPlayer mp=new myPlayer(ShowMushafActivity.this,surah);
            marray = if (isjuz) {
                createMediaItemsJuz()
            } else {
                createMediaItems()
            }
            //urrah      marray = createMediaItems();
            if (marray!!.isEmpty()) {
                return
            }
            player = ExoPlayer.Builder(this).build()
            lastSeenTracks = Tracks.EMPTY
            player!!.addListener(PlayerEventListener())
            player!!.trackSelectionParameters = trackSelectionParameters!!
            player!!.addListener(PlayerEventListener())
            player!!.addAnalyticsListener(EventLogger())
            player!!.setAudioAttributes(AudioAttributes.DEFAULT,  /* handleAudioFocus= */true)
            player!!.playWhenReady = startAutoPlay
            player!!.repeatMode = Player.REPEAT_MODE_ALL
            playerView!!.repeatToggleModes = RepeatModeUtil.REPEAT_TOGGLE_MODE_ONE
            player!!.seekTo(ayah, playbackPosition)
            if (versestartrange != 0) {
                ayah = versestartrange
            }
            playerView!!.player = player
            player!!.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (player!!.playWhenReady && playbackState == Player.STATE_READY) {
                        isMusicplaying = true // media actually playing
                    } else if (player!!.playWhenReady) {
                        isMusicplaying = false
                        // might be idle (plays after prepare()),
                        // buffering (plays when data available)
                        // or ended (plays when seek away from end)
                    } else {
                        pausePlayFlag = true
                        // player paused in any state
                    }
                    //   super@Player.Listener.onPlaybackStateChanged(playbackState)
                    //  super@Listener.onPlaybackStateChanged(playbackState)
                }
            })
            val haveStartPosition = startItemIndex != C.INDEX_UNSET
            if (haveStartPosition) {
                //    player.seekTo(startItemIndex, startPosition);
            }
            if (rangeRecitation) {
                if (verseendrange == 0) {
                    verseendrange = versescount
                }
                if (versestartrange != 0) {
                    marrayrange = marray!!.subList(versestartrange, verseendrange)
                    player!!.setMediaItems(
                        marrayrange as MutableList<MediaItem>,  /* resetPosition= */
                        !haveStartPosition
                    )
                }
            } else {
                player!!.setMediaItems(marray!!,  /* resetPosition= */!haveStartPosition)
            }
            val str = "(" + surahNameArabic + ")" + "(" + surahNameEnglish + ")" + ":" + readerName
            qariname!!.text = str
            //   qariname.setText(readerName);
            player!!.prepare()
            if (resume) {
                player!!.seekToDefaultPosition(resumelastplayed)
            }
            if (audioSettingBottomBehaviour!!.state == BottomSheetBehavior.STATE_EXPANDED) {
                audioSettingBottomBehaviour!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
            } else {
                audioSettingBottomBehaviour!!.setState(BottomSheetBehavior.STATE_EXPANDED)
            }
            if (exoplayerBottomBehaviour!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
                audioSettingBottomBehaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
                player!!.play()
            }
        }
        //updateButtonVisibility();
    }

    private fun createMediaItemsJuz(): MutableList<MediaItem> {
        val repository = Utils(this)
        val chap: List<ChaptersAnaEntity?>? = repository.getSingleChapter(surahselected)
        println(versestartrange.toString() + " " + verseendrange)
        val ayaLocations: MutableList<String> = ArrayList()
        marray = ArrayList()
        if (isSingle) {
            val sngleverseplay: List<QuranEntity?>? = Utils.getsurahayahVerses(
                surahselected,
                verselected
            )
            //Create files locations for the all page ayas
            //Create files locations for the all page ayas
            for (ayaItem in sngleverseplay!!) {
                ayaLocations.add(
                    FileManager.createAyaAudioLinkLocation(
                        this,
                        readerID,
                        ayaItem!!.ayah,
                        ayaItem.surah
                    )
                )
                val location = FileManager.createAyaAudioLinkLocation(
                    this,
                    readerID,
                    ayaItem!!.ayah,
                    ayaItem.surah
                )
                marray.add(MediaItem.fromUri(location))
            }
        } else if (isStartFrom) {
            onClickOrRange = true
            val fromrange: List<QuranEntity?>? = Utils.getQuranbySurahAyahrange(
                surahselected,
                verselected, chap!![0]!!.versescount
            )
            for (ayaItem in fromrange!!) {
                ayaLocations.add(
                    FileManager.createAyaAudioLinkLocation(
                        this,
                        readerID,
                        ayaItem!!.ayah,
                        ayaItem!!.surah
                    )
                )
                val location = FileManager.createAyaAudioLinkLocation(
                    this,
                    readerID,
                    ayaItem.ayah,
                    ayaItem.surah
                )
                marray.add(MediaItem.fromUri(location))
            }
        } else {
            val quranbyJuz: List<QuranEntity?>? = Utils.getQuranbyJuz(3)
            for (ayaItem in quranbyJuz!!) {
                ayaLocations.add(
                    FileManager.createAyaAudioLinkLocation(
                        this,
                        readerID,
                        ayaItem!!.ayah,
                        ayaItem!!.surah
                    )
                )
                val location = FileManager.createAyaAudioLinkLocation(
                    this,
                    readerID,
                    ayaItem.ayah,
                    ayaItem.surah
                )
                marray.add(MediaItem.fromUri(location))
            }
        }
        return marray
    }

    private fun createMediaItems(): MutableList<MediaItem> {
        val repository = Utils(this)
        val chap: List<ChaptersAnaEntity?>? = repository.getSingleChapter(
            surahselected
        )
        println(versestartrange.toString() + " " + verseendrange)
        val ayaLocations: MutableList<String> = ArrayList()
        marray = ArrayList()
        if (isSingle) {
            val sngleverseplay: List<QuranEntity?>? = repository.getsurahayahVerses(
                surahselected, verselected
            )
            //Create files locations for the all page ayas
            //Create files locations for the all page ayas
            for (ayaItem in sngleverseplay!!) {
                ayaLocations.add(
                    FileManager.createAyaAudioLinkLocation(
                        this,
                        readerID,
                        ayaItem!!.ayah,
                        ayaItem.surah
                    )
                )
                val location = FileManager.createAyaAudioLinkLocation(
                    this,
                    readerID,
                    ayaItem.ayah,
                    ayaItem.surah
                )
                marray.add(MediaItem.fromUri(location))
            }
        } else if (isStartFrom) {
            onClickOrRange = true
            val fromrange: List<QuranEntity?>? = Utils.getQuranbySurahAyahrange(
                surahselected,
                verselected, chap!![0]!!.versescount
            )
            for (ayaItem in fromrange!!) {
                ayaLocations.add(
                    FileManager.createAyaAudioLinkLocation(
                        this,
                        readerID,
                        ayaItem!!.ayah,
                        ayaItem.surah
                    )
                )
                val location = FileManager.createAyaAudioLinkLocation(
                    this,
                    readerID,
                    ayaItem.ayah,
                    ayaItem.surah
                )
                marray.add(MediaItem.fromUri(location))
            }
        } else {
            val quranbySurah: List<QuranEntity?>? = repository.getQuranbySurah(
                surahselected
            )
            for (ayaItem in quranbySurah!!) {
                ayaLocations.add(
                    FileManager.createAyaAudioLinkLocation(
                        this,
                        readerID,
                        ayaItem!!.ayah,
                        ayaItem.surah
                    )
                )
                val location = FileManager.createAyaAudioLinkLocation(
                    this,
                    readerID,
                    ayaItem.ayah,
                    ayaItem.surah
                )
                marray.add(MediaItem.fromUri(location))
            }
        }
        return marray
    }

    private fun updateTrackSelectorParameters() {
        if (player != null) {
            trackSelectionParameters = player!!.trackSelectionParameters
        }
    }

    private fun updateStartPosition() {
        if (player != null) {
            startAutoPlay = player!!.playWhenReady
            startItemIndex = player!!.currentMediaItemIndex
            startPosition = Math.max(0, player!!.contentPosition)
        }
    }

    override fun onFullscreenButtonClick(isFullScreen: Boolean) {}
    private inner class PlayerEventListener : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: @Player.State Int) {
            if (playbackState == Player.STATE_ENDED) {
                println("playbackstate")
            }
            //     updateButtonVisibility();
        }

        override fun onPlayerError(error: PlaybackException) {
            if (error.errorCode == PlaybackException.ERROR_CODE_BEHIND_LIVE_WINDOW) {
                assert(player != null)
                player!!.seekToDefaultPosition()
                player!!.prepare()
            } else {
            }
        }

        override fun onMediaItemTransition(
            mediaItem: MediaItem?, reason: @MediaItemTransitionReason Int,
        ) {
            //  listener.onMediaItemTransition(mediaItem, reason);
            println("check")
        }

        override fun onPositionDiscontinuity(
            oldPosition: PositionInfo, newPosition: PositionInfo, reason: @DiscontinuityReason Int,
        ) {
            println("oldpostion" + " " + oldPosition + "newpostion " + " " + newPosition + "reason" + " " + reason)
            println("check")
        }

        override fun onTracksChanged(tracks: Tracks) {
            //   updateButtonVisibility();
            assert(player != null)
            val currentTracks = player!!.currentTracks
            currenttrack = player!!.currentMediaItemIndex
            //     currenttrack=resumelastplayed;
            val resume = true
            println("Ayah$ayah")
            if (rangeRecitation) {
                currenttrack += versestartrange
                currenttrack++
            } else if (onClickOrRange) {
                currenttrack += ayah
            } else {
                currenttrack++
            }

            //    NewsendUpdatesToUIPassage.run();
            if (!singleline) {
                if (player!!.isPlaying || player!!.playWhenReady) {
                    if (isjuz) {
                        sendUpdatesToUIJuz.run()
                    } else {
                        sendUpdatesToUIPassage.run()
                    }
                } else {
                    handler.removeCallbacks(sendUpdatesToUIPassage)
                }
            } else {
                if (player!!.isPlaying || player!!.playWhenReady) {
                    sendUpdatesToUI.run()
                } else {
                    handler.removeCallbacks(sendUpdatesToUI)
                }
            }
            if (tracks === lastSeenTracks) {
                return
            }
            if (tracks.containsType(C.TRACK_TYPE_AUDIO)
                && !tracks.isTypeSupported(C.TRACK_TYPE_AUDIO,  /* allowExceedsCapabilities= */true)
            ) {
                //showToast(R.string.error_unsupported_audio);
            }
            lastSeenTracks = tracks
        }
    }

    private fun preparehighlightsNew(
        passageno: Int,
        str: java.lang.StringBuilder,
        ayahmat: java.util.ArrayList<Int>,
    ) {
        val holder = recyclerView.findViewHolderForAdapterPosition(1)
        var ayahindex = ayahmat[0]
        val ayahmaz = ayahmat.size
        val split1 = str.toString().split("﴿".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val start = 0
        //  = str.indexOf("1");
        val end = str.indexOf(ayahindex.toString())
        val acf = AyahCoordinate(0, end, passageno)
        val Coordinatesf = java.util.ArrayList<AyahCoordinate>()
        Coordinatesf.add(acf)
        hlights[ayahindex] = Coordinatesf
        //  ayahindex++;
        for (i in split1.indices) {
            val s = str.indexOf(ayahindex.toString())
            val e = str.indexOf((ayahindex + 1).toString())
            if (s != -1 && e != -1) {
                val ac = AyahCoordinate(s, e, passageno)
                val Coordinates = java.util.ArrayList<AyahCoordinate>()
                Coordinates.add(ac)
                hlights[++ayahindex] = Coordinates
            } else {
                println("$s $e")
            }
        }
        println("check")
    }

    protected fun clearStartPosition() {
        startAutoPlay = true
        startItemIndex = C.INDEX_UNSET
        startPosition = C.TIME_UNSET
    }

    @SuppressLint("WrongViewCast", "NotifyDataSetChanged")
    private fun initRV() {
        ayaprogress = findViewById(R.id.ayaprogress) as MaterialTextView
        canceldownload = findViewById(R.id.canceldownload) as MaterialButton
        canceldownload!!.setOnClickListener(this)
        qariname = findViewById(R.id.lqari) as TextView
        //  buffering = findViewById(R.id.exo_buffering) as ImageView
        val chooseDisplaytype: SwitchCompat = findViewById(R.id.chooseDisplaytype)
        chooseDisplaytype.setOnClickListener(this)
        playfb = findViewById(R.id.playfb) as MovableFloatingActionButton
        playfb!!.setOnClickListener(this)
        exo_settings = findViewById(R.id.exo_settings)
        exo_settings!!.setOnClickListener(this)
        exo_close = findViewById(R.id.exo_close) as ImageButton
        exo_bottom_bar = findViewById(R.id.exo_bottom_bar) as ImageButton
        //  private ImageView playbutton;
        val playbutton: MaterialButton = findViewById(R.id.playbutton)
        val playresume = findViewById(R.id.playresume) as MaterialButton
        playresume.setOnClickListener(this)
        val surahselection = findViewById(R.id.surahselection) as MaterialButton
        surahselection.setOnClickListener(this)
        exo_close!!.setOnClickListener(this)
        playbutton.setOnClickListener(this)
        exo_bottom_bar!!.setOnClickListener(this)
        chooseDisplaytype.isChecked = singleline
        startrange = findViewById(R.id.start_range)
        endrange = findViewById(R.id.endrange)
        startrange!!.setOnClickListener(this)
        llStartRange = (findViewById(R.id.llStartRange) as LinearLayout?)!!
        llStartRange!!.setOnClickListener(this)
        endrange!!.setOnClickListener(this)
        llEndRange = (findViewById(R.id.llEndRange) as LinearLayout?)!!
        llEndRange!!.setOnClickListener {
            val starttrue = false

            //   WbwSurah wbwSurah=new WbwSurah(ShowMushafActivity.this);
            //   startverse[0] =wbwSurah.SurahAyahPicker(false,starttrue,getSurahselected(),getAyah());
            SurahAyahPicker(false, starttrue)
        }
        llStartRange!!.setOnClickListener(object : View.OnClickListener {
            val starttrue = true
            override fun onClick(v: View) {
                marrayrange = null
                SurahAyahPicker(false, starttrue)
            }
        })
        playiv = findViewById(R.id.play) as ImageView
        playiv!!.setOnClickListener(this)
        audio_settings_bottom = findViewById(R.id.audio_settings_bottom)
        normalFooter = findViewById(R.id.normalfooter) as LinearLayout
        downloadFooter = findViewById(R.id.footerdownload) as RelativeLayout
        playerFooter = findViewById(R.id.footerplayer) as RelativeLayout
        mediaPlayerDownloadProgress = findViewById(R.id.downloadProgress) as ProgressBar
        chooseDisplaytype.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                singleline = true
                RefreshActivity("", "", true)
            } else {
                singleline = false
                RefreshActivity("", "", false)
            }
        }
        startrange!!.setOnClickListener(object : View.OnClickListener {
            val starttrue = true
            override fun onClick(v: View) {
                SurahAyahPicker(false, starttrue)
            }
        })
        endrange!!.setOnClickListener {
            val starttrue = false
            SurahAyahPicker(false, starttrue)
        }
        surahselection.setOnClickListener { SurahAyahPicker(true, true) }
        playfb!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if (audioSettingBottomBehaviour!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
                    audioSettingBottomBehaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
                    audio_settings_bottom!!.visibility = View.VISIBLE
                } else {
                    audioSettingBottomBehaviour!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
                    //    audio_settings_bottom.setVisibility(View.GONE);
                }
                if (exoplayerBottomBehaviour!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
                    exoplayerBottomBehaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
                    if (player != null) player!!.play()
                } else {
                    if (player != null) player!!.pause()
                    exoplayerBottomBehaviour!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
                }
                val st = StringBuilder()
                val stt = StringBuilder()
                st.append(surahNameEnglish).append("-").append(surahselected).append(":")
                    .append("1")
                stt.append(surahNameEnglish).append("-").append(surahselected).append(":").append(
                    versescount
                )
                startrange!!.text = st.toString()
                startrange!!.text = stt.toString()
            }
        })
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        quranbySurahadapter = repository.getQuranbySurah(surah)
        var sb = StringBuilder()
        for (entity in quranbySurahadapter!!) {
            if (entity!!.passage_no != 0) {
                sb.append(entity!!.qurantext).append("﴿").append(entity.ayah).append("﴾")
            } else {
                passage[entity.passage_no] = sb.toString()
                sb = java.lang.StringBuilder()
            }
        }
        val chapter: List<ChaptersAnaEntity?>? = repository.getSingleChapter(surah)
        //  initlistview(quranbySurah, chapter);
        val listener: OnItemClickListenerOnLong = this
        val header = ArrayList<String>()
        header.add(chapter!![0]!!.rukucount.toString())
        header.add(chapter[0]!!.versescount.toString())
        header.add(chapter[0]!!.chapterid.toString())
        header.add(chapter[0]!!.abjadname)
        header.add(chapter[0]!!.nameenglish)
        versescount = chapter[0]!!.versescount
        surahNameEnglish = chapter[0]!!.nameenglish
        surahNameArabic = chapter[0]!!.namearabic
        recyclerView!!.layoutManager = manager
        recyclerView!!.setHasFixedSize(true)
        if (singleline) {

            lineMushaAudioAdapter = LineMushaAudioAdapter(
                this,
                quranbySurahadapter,
                listener,
                chapter[0]!!.namearabic,
                chapter[0]!!.ismakki,
                header
            )


            recyclerView!!.adapter = lineMushaAudioAdapter
        } else {
            passageadapter = if (!isjuz) {
                PageMushaAudioAdapter(
                    fullQuranPages,
                    this,
                    listener,
                    chapter[0]!!.chapterid.toLong(),
                    chapter[0]!!.namearabic,
                    chapter[0]!!.ismakki,
                    header
                )
            } else {
                PageMushaAudioAdapter(
                    juzquranpages,
                    this,
                    listener,
                    chapter[0]!!.chapterid.toLong(),
                    chapter[0]!!.namearabic,
                    chapter[0]!!.ismakki,
                    header
                )
            }
            recyclerView!!.adapter = passageadapter
            passageadapter.notifyDataSetChanged()
        }
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        exo_bottom_bar!!.setOnClickListener { SurahAyahPicker(true, true) }
        exo_close!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //reset player
                verselected = 1
                versestartrange = 0
                verseendrange = 0
                ayah = 0
                marrayrange = emptyList()
                resume = false
                rangeRecitation = false
                handler.removeCallbacks(sendUpdatesToUI)
                recyclerView!!.post { recyclerView!!.scrollToPosition(0) }
                releasePlayer()
                initializePlayer()

                //    RefreshActivity("", " ", false);
            }
        })
        exo_settings!!.setOnClickListener {
            if (audioSettingBottomBehaviour!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
                audioSettingBottomBehaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
                audio_settings_bottom!!.visibility = View.VISIBLE
            } else {
                audioSettingBottomBehaviour!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
                //    audio_settings_bottom.setVisibility(View.GONE);
            }
            if (exoplayerBottomBehaviour!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
                exoplayerBottomBehaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
                assert(player != null)
                player!!.play()
            } else {
                if (player != null) {
                    player!!.pause()
                }
                exoplayerBottomBehaviour!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }
        playbutton.setOnClickListener { DownloadIfnotPlay() }
        playresume.setOnClickListener {
            resume = true
            DownloadIfnotPlay()
        }
        canceldownload!!.setOnClickListener {
            downloadFooter!!.visibility = View.GONE
            normalFooter!!.visibility = View.VISIBLE
            //stop flag of auto start audio after download
            startBeforeDownload = false
            //stop download service
            stopService(Intent(this@ShowMushafActivity, DownloadService::class.java))
        }

        // to preserver quran direction from right to left
        recyclerView!!.layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    fun getReaderAudioLink(readerName: String) {
        for (reader in readersList) {
            if (reader.name_english == readerName && (Locale.getDefault().displayLanguage == "العربية")) {
                downloadLink = reader.url
                readerID = reader.id
                audioType = reader.audiotype
                break
            } else if (reader.name_english == readerName) {
                downloadLink = reader.url
                readerID = reader.id
                break
            }
        }
    }

    protected override fun onPause() {
//        mSensorManager.unregisterListener(this);
        super.onPause()

        //unregister broadcast for download ayat
        LocalBroadcastManager.getInstance(this).unregisterReceiver(downloadPageAya)
        //stop flag of auto start
        startBeforeDownload = false
    }

    protected override fun onResume() {
        super.onResume()

        //register broadcast for download ayat
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(downloadPageAya, IntentFilter(AudioAppConstants.Download.INTENT))

        //make footer change to normal if audio end in pause
        if (!Settingsss.isMyServiceRunning(this, DownloadService::class.java)) {
            playerFooter!!.visibility = View.GONE
            normalFooter!!.visibility = View.GONE
        } else {
            if (downloadFooter!!.visibility != View.VISIBLE) {
                playerFooter!!.visibility = View.VISIBLE
            } else {
                playerFooter!!.visibility = View.GONE
            }
            normalFooter!!.visibility = View.GONE
        }

        //loadPagesReadLoge();
    }

    private val downloadPageAya: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val value = intent.getLongExtra(AudioAppConstants.Download.NUMBER, 0).toInt()
            val max = intent.getLongExtra(AudioAppConstants.Download.MAX, 0).toInt()
            val status = intent.getStringExtra(AudioAppConstants.Download.DOWNLOAD)
            if (status != null) {
                if (status == AudioAppConstants.Download.IN_DOWNLOAD) {
                    downloadFooter!!.visibility = View.VISIBLE
                    normalFooter!!.visibility = View.GONE
                    playerFooter!!.visibility = View.GONE
                    mediaPlayerDownloadProgress!!.max = max
                    mediaPlayerDownloadProgress!!.progress = value
                } else if (status == AudioAppConstants.Download.FAILED) {
                    mediaPlayerDownloadProgress!!.max = 1
                    mediaPlayerDownloadProgress!!.progress = 1
                } else if (status == AudioAppConstants.Download.SUCCESS) {
                    mediaPlayerDownloadProgress!!.max = 1
                    mediaPlayerDownloadProgress!!.progress = 1
                    //check if you auto play after download
                    if (startBeforeDownload) {
                        //change views
                        downloadFooter!!.visibility = View.GONE
                        normalFooter!!.visibility = View.GONE
                        playerFooter!!.visibility = View.VISIBLE
                        initializePlayer()
                    } else {
                        downloadFooter!!.visibility = View.GONE
                        normalFooter!!.visibility = View.GONE
                        playerFooter!!.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun createDownloadLink(): List<String> {
        val chap: List<ChaptersAnaEntity?>? = repository.getSingleChapter(surah)
        surahselected = surah
        val downloadLin: MutableList<String> = ArrayList()

        //validate if aya download or not
        if (!QuranValidateSources.validateSurahAudio(
                this, readerID,
                surahselected
            )
        ) {

            //create aya link
            val suraLength = chap?.get(0)?.chapterid.toString().trim { it <= ' ' }.length
            var suraID = chap?.get(0)!!.chapterid.toString() + ""

            //   int ayaLength = String.valueOf(ayaItem.ayaID).trim().length();
            if (suraLength == 1) suraID =
                "00" + (chap!!.get(0)!!.chapterid) else if (suraLength == 2) suraID =
                "0" + chap?.get(0)!!.chapterid
            val s = downloadLink + chap!!.get(0)!!.chapterid + AudioAppConstants.Extensions.MP3
            downloadLin.add(s)
            Log.d("DownloadLinks", downloadLink + suraID + AudioAppConstants.Extensions.MP3)
        }
        return downloadLin
    }

    fun createDownloadLinks(): List<String>? {
        val chap = repository.getSingleChapter(surah)
        val quranbySurah: List<QuranEntity?>? = repository.getQuranbySurah(surah)
        surahselected = surah
        //   int ayaID=0;
        var counter = 0
        //   quranbySurah.add(0, new QuranEntity(1, 1, 1));
        val downloadLinks: MutableList<String> = java.util.ArrayList()
        //   ayaList.add(0, new Aya(1, 1, 1));
        //loop for all page ayat
//check if readerID is 0
        if (readerID == 0) {
            for (qari in readersList) {
                if (qari.name_english == selectedqari) {
                    readerID = qari.id
                    downloadLink = qari.url
                    break
                }
            }
        }
        if (quranbySurah != null) {
            for (ayaItem in quranbySurah) {
                //validate if aya download or not
                if (!QuranValidateSources.validateAyaAudio(
                        this,
                        readerID,
                        ayaItem!!.ayah,
                        ayaItem.surah
                    )
                ) {

                    //create aya link


                    //create aya link
                    val suraLength: Int =
                        chap!!.get(0)!!.chapterid.toString().trim { it <= ' ' }.length
                    var suraID: String = chap.get(0)!!.chapterid.toString() + ""


                    val ayaLength = ayaItem.ayah.toString().trim { it <= ' ' }.length
                    //   int ayaLength = String.valueOf(ayaItem.ayaID).trim().length();
                    //   int ayaLength = String.valueOf(ayaItem.ayaID).trim().length();
                    var ayaID = java.lang.StringBuilder(
                        java.lang.StringBuilder().append(ayaItem.ayah).append("").toString()
                    )
                    if (suraLength == 1) suraID =
                        "00" + ayaItem.surah else if (suraLength == 2) suraID = "0" + ayaItem.surah

                    if (ayaLength == 1) {
                        ayaID = java.lang.StringBuilder("00" + ayaItem.ayah)
                    } else if (ayaLength == 2) {
                        ayaID = java.lang.StringBuilder("0" + ayaItem.ayah)
                    }
                    counter++
                    //add aya link to list
                    //chec
                    downloadLinks.add(downloadLink + suraID + ayaID + AudioAppConstants.Extensions.MP3)
                    Log.d(
                        "DownloadLinks",
                        downloadLink + suraID + ayaID + AudioAppConstants.Extensions.MP3
                    )
                }
            }
        }
        return downloadLinks
    }


    private fun DownloadIfnotPlay() {
        val internetStatus: Int = Settingsss.checkInternetStatus(this)

//https://cdn.islamic.network/quran/audio-surah/128/ar.alafasy/
        //check if there is other download in progress
        if (!Settingsss.isMyServiceRunning(this, DownloadService::class.java)) {
            //internal media play

            //internal media play
            val Links: List<String>?
            val everyayah = true
            Links = if (audioType != 2) {
                createDownloadLinks()
            } else {
                createDownloadLink()
            }

            if (Links!!.size != 0) {
                //check if the internet is opened
                DownLoadIfNot(internetStatus, Links as ArrayList<String>)
            } else {
                initializePlayer()
                playerFooter!!.visibility = View.VISIBLE
                audio_settings_bottom!!.visibility = View.GONE
            }
        } else {
            //Other thing in download
            Toast.makeText(this, getString(R.string.download_busy), Toast.LENGTH_SHORT).show()
        }
    }

    private fun DownLoadIfNot(internetStatus: Int, Links: ArrayList<String>) {
        if (internetStatus <= 0) {
            val builder = AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
            builder.setCancelable(false)
            builder.setTitle(getResources().getString(R.string.Alert))
            builder.setMessage(getResources().getString(R.string.no_internet_alert))
            builder.setPositiveButton(getResources().getString(R.string.ok),
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            builder.show()
        } else {
            //change view of footer to media
            //      footerContainer.setVisibility(View.VISIBLE);
            playerFooter!!.visibility = View.GONE
            audioSettingBottomBehaviour!!.state = BottomSheetBehavior.STATE_COLLAPSED
            //  mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            downloadFooter!!.visibility = View.VISIBLE

            //check audio folders

            // String app_folder_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/Audio/" + readerID;
            val app_folder_path =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                    .toString() + "/audio/" + readerID
            val f = File(app_folder_path)
            val path = f.absolutePath
            val file = File(path)
            if (!file.exists()) file.mkdirs()
            startBeforeDownload = true
            val intent = Intent(this@ShowMushafActivity, DownloadService::class.java)
            intent.putStringArrayListExtra(AudioAppConstants.Download.DOWNLOAD_LINKS, Links)
            intent.putExtra(AudioAppConstants.Download.DOWNLOAD_LOCATION, app_folder_path)
            startService(intent)
        }
    }

    override fun onItemClick(v: View, position: Int) {
        val istag = v.tag
        if (istag == "verse" && singleline) {
            onClickOrRange = true
            val ayah1 = quranbySurahadapter!![position]!!.ayah
            isSingle = true
            verselected = ayah1 - 1
            ayah = ayah1 - 1
            DownloadIfnotPlay()
            isSingle = false
        }
    }

    protected override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
        handler.removeCallbacks(sendUpdatesToUI)
        handler.removeCallbacks(sendUpdatesToUIPassage)
        if (currenttrack != 0) {
            val pref: SharedPreferences = getSharedPreferences("lastaya", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putInt("lastaya", currenttrack)
            val ap: ArrayList<AudioPlayed?>? = null
            val audioPlayed = AudioPlayed()
            audioPlayed.surah = surah
            audioPlayed.ayah = currenttrack
            if (!hlights.isEmpty()) {
                editor.putInt(
                    "trackposition", Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                        hlights[currenttrack]
                    )[0].passage!!
                )
                audioPlayed.trackposition = (
                        Objects.requireNonNull<ArrayList<AyahCoordinate>>(
                            hlights[currenttrack]
                        )[0].passage!!
                        )
            } else {
                editor.putInt("trackposition", currenttrack)
            }
            ap?.add(audioPlayed)
            editor.apply()
            ConfigPreferences.setLastPlayedAudio(this, ap, surah.toString())
        }
        //unregister broadcast for download ayat
        LocalBroadcastManager.getInstance(this@ShowMushafActivity)
            .unregisterReceiver(downloadPageAya)
        //stop flag of auto start
        startBeforeDownload = false
        if (player != null) {
            player!!.release()
        }

        // finish();
    }

    override fun onItemLongClick(position: Int, v: View) {
        val istag = v.tag
        if (istag == "verse" && singleline) {
            onClickOrRange = true
            val ayah1 = quranbySurahadapter!![position]!!.ayah
            ayah = ayah1
            isStartFrom = true
            verselected = ayah1 - 1
            ayah = ayah1 - 1
            DownloadIfnotPlay()
            isStartFrom = false
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            //   this.initializePlayer();
        }
    }

    companion object {
        private const val KEY_TRACK_SELECTION_PARAMETERS = "track_selection_parameters"
        private const val KEY_ITEM_INDEX = "item_index"
        private const val KEY_POSITION = "position"
        private const val KEY_AUTO_PLAY = "auto_play"

        // For ad playback only.
        const val BROADCAST_SEEKBAR = "com.example.mushafconsolidated.Activity.sendseekbar"
        var readerID = 0
        lateinit var downloadLink: String
        lateinit var readerName: String
        var startBeforeDownload = false
        private const val TAG = "ShowMushafActivity"

        //   int pos;
        private const val songEnded = 0
    }

    override fun onClick(v: View?) {
        if (v == findViewById(R.id.play)) {
            DownloadIfnotPlay()
        } else if (v == findViewById(R.id.canceldownload)) {
            downloadFooter.visibility = View.GONE
            normalFooter.visibility = View.VISIBLE
            //stop flag of auto start audio after download
            startBeforeDownload = false
            //stop download service
            stopService(Intent(this, DownloadService::class.java))
        }
    }
}



