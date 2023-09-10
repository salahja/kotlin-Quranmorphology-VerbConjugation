package org.sj.conjugator.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.SARFKABEER
import com.example.Constant.VERBMOOD
import com.example.Constant.VERBTYPE
import com.example.mushafconsolidated.Activity.QuranGrammarAct
import com.example.mushafconsolidated.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import database.VerbDatabaseUtils
import database.entity.MazeedEntity
import database.entity.MujarradVerbs
import database.verbrepo.VerbModel
import org.sj.conjugator.fragments.SettingsFragmentVerb
import org.sj.conjugator.utilities.SharedPref
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class ConjugatorAct : BaseActivity(), View.OnClickListener {
    private val keyValues: SparseArray<String> = SparseArray<String>()
    lateinit var quranbtn: Button
    lateinit var settingbtn: Button
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var layoutBottomSheet: RelativeLayout
    lateinit var sheetBehavior: BottomSheetBehavior<*>
    var tlist: ListView? = null
    var mlist: ListView? = null
    lateinit var nasara: Chip
    lateinit var zaraba: Chip
    lateinit var samia: Chip
    lateinit var fataha: Chip
    lateinit var karuma: Chip
    lateinit var haseeba: Chip
    lateinit var tafeel: Chip
    lateinit var mufala: Chip
    lateinit var ifal: Chip
    lateinit var tafaul: Chip
    lateinit var tafaaul: Chip
    lateinit var infala: Chip
    lateinit var iftiala: Chip
    lateinit var istifala: Chip
    lateinit var mujarradbtn: MaterialButton
    lateinit var mazeedbtn: MaterialButton
    var isSarfKabeed = false
    var mujarradVerbs: ArrayList<MujarradVerbs> = ArrayList<MujarradVerbs>()
    private lateinit var editTextAuto: EditText
    lateinit var editText: EditText
    private lateinit var verbmood: RadioGroup
    private lateinit var indicative: RadioButton
    private lateinit var subjunctive: RadioButton
    lateinit var radioText: String
    lateinit var inputtext: String
    private lateinit var keyboard: View

    private lateinit var inputConnection: InputConnection
    private var mazeedEntityVerbs: ArrayList<MazeedEntity> = ArrayList<MazeedEntity>()
    private var isautocomplete = false
    fun setIsautocomplete(isautocomplete: Boolean) {
        this.isautocomplete = isautocomplete
    }

    /* fun setSarfKabeed(sarfKabeed: Boolean) {
         isSarfKabeed = sarfKabeed
     }
 */
    override fun onBackPressed() {
        super.onBackPressed()
    }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conjugator_key_activity_autocomplete)
        KeyboardUtil.hideKeyboard(this@ConjugatorAct)
        keyboard = findViewById(R.id.arabic_keyboard)
        val callButton: FloatingTextButton = findViewById(R.id.action_buttons)
        callButton.setOnClickListener { view: View? ->
            super@ConjugatorAct.finish()
            super.onBackPressed()
        }
        //    hideKeyboardSoft();
        contextOfApplication = getApplicationContext()
        val ic: InputConnection
        SetUpAutoComplete()
        ic = editTextAuto.onCreateInputConnection(EditorInfo())
        // InputConnection ic = editTextAuto.onCreateInputConnection(new EditorInfo());
        setInputConnection(ic)
        init()
        // kb. getCharSequence();
    }

    private fun SetUpAutoComplete() {
        KeyboardUtil.hideKeyboard(this@ConjugatorAct)
        val viewmodel: VerbModel by viewModels()
        var root: Array<String?> = emptyArray()
        val util = VerbDatabaseUtils(this@ConjugatorAct)
        //   val verbAll: ArrayList<MujarradVerbs?>? = util.mujarradAall
        // val size = verbAll?.size
        //   root = arrayOfNulls(size!!)
        var actv: AutoCompleteTextView =
            findViewById(R.id.autoCompleteTextView) as AutoCompleteTextView
        editTextAuto = findViewById(R.id.autoCompleteTextView)
        var i = 0
        viewmodel.getMujarradll().observe(this) {
            val len = it.size
            root = arrayOfNulls(len)
            val arr = mutableListOf<String>()
            for (entity in it) {
                arr.add(entity.root)


            }


            val autoadapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this, R.layout.dropdown_item_list, arr);
            //Getting the instance of AutoCompleteTextView

            val sizes = 500
            actv.setDropDownHeight(sizes)
            actv.setThreshold(1) //will start working from first character
            actv.setAdapter(autoadapter) //setting the adapter data into the AutoCompleteTextView
            //  actv.adapter=autoadapter
            actv.setTextColor(Color.RED)
            //   actv.setTextSize((float) 50.00);

            actv.setRawInputType(InputType.TYPE_CLASS_TEXT)
            actv.setTextIsSelectable(true)
            //   KeyboardUtil.hideKeyboard(this);
            actv.setShowSoftInputOnFocus(false)
            actv.setOnFocusChangeListener(View.OnFocusChangeListener { view: View?, hasFocus: Boolean ->
                if (hasFocus) {
                    keyboard!!.visibility = LinearLayout.VISIBLE
                    if (tlist != null) tlist!!.adapter = null
                    if (mlist != null) mlist!!.adapter = null
                } //   keyboard.setVisibility(LinearLayout.GONE);
            })
        }


    }

    private fun init() {
        quranbtn = findViewById(R.id.qurangrammar)
        settingbtn = findViewById(R.id.conjugatorsetting)
        layoutBottomSheet = findViewById(R.id.bottom_sheet)
        sheetBehavior = BottomSheetBehavior.from<View>(layoutBottomSheet)
        floatingActionButton = findViewById(R.id.fab)
        mujarradbtn = findViewById(R.id.mujarradbtn)
        mazeedbtn = findViewById(R.id.mazeedbtn)
        nasara = findViewById(R.id.nasara)
        zaraba = findViewById(R.id.zaraba)
        samia = findViewById(R.id.samia)
        fataha = findViewById(R.id.fataha)
        karuma = findViewById(R.id.karuma)
        haseeba = findViewById(R.id.hasiba)
        tafeel = findViewById(R.id.tafeel)
        mufala = findViewById(R.id.mufala)
        ifal = findViewById(R.id.ifal)
        tafaul = findViewById(R.id.tafaul)
        tafaaul = findViewById(R.id.tafaaaul)
        infala = findViewById(R.id.infala)
        iftiala = findViewById(R.id.iftiala)
        istifala = findViewById(R.id.istifala)
        verbmood = findViewById(R.id.verbcase)
        indicative = findViewById(R.id.rdindicative)
        subjunctive = findViewById(R.id.rdsubjunctive)
        val jussive: RadioButton = findViewById(R.id.rdjussive)
        val emphasized: RadioButton = findViewById(R.id.emphasized)
        keyboard = findViewById(R.id.arabic_keyboard)
        val key_delete: Button = findViewById(R.id.key_delete)
        val key_AC: Button = findViewById(R.id.key_AC)
        val key_enter: Button = findViewById(R.id.key_enter)
        keyboard = findViewById(R.id.arabic_keyboard)
        val dhad: Button = findViewById(R.id.dhad)
        val suwad: Button = findViewById(R.id.suwad)
        val qaf: Button = findViewById(R.id.qaf)
        val fa: Button = findViewById(R.id.fa)
        val ghain: Button = findViewById(R.id.ghain)
        val ayn: Button = findViewById(R.id.ayn)
        val haaa: Button = findViewById(R.id.haaa)
        val kha: Button = findViewById(R.id.kha)
        val ha: Button = findViewById(R.id.ha)
        val jeem: Button = findViewById(R.id.jeem)
        //     GRadioGroup gr = new GRadioGroup(nasara,zaraba,samia,fatha,karuma,hasiba,two,three,four,five,six,seven,eight,ten);
        quranbtn!!.setOnClickListener(this)
        settingbtn!!.setOnClickListener(this)
        floatingActionButton.setOnClickListener(this)
        mujarradbtn.setOnClickListener(this)
        mazeedbtn.setOnClickListener(this)
        nasara.setOnClickListener(this)
        zaraba.setOnClickListener(this)
        samia.setOnClickListener(this)
        fataha.setOnClickListener(this)
        karuma.setOnClickListener(this)
        haseeba.setOnClickListener(this)
        tafeel.setOnClickListener(this)
        tafaul.setOnClickListener(this)
        tafaaul.setOnClickListener(this)
        mufala.setOnClickListener(this)
        infala.setOnClickListener(this)
        istifala.setOnClickListener(this)
        iftiala.setOnClickListener(this)
        ifal.setOnClickListener(this)
        dhad.setOnClickListener(this)
        suwad.setOnClickListener(this)
        qaf.setOnClickListener(this)
        fa.setOnClickListener(this)
        ghain.setOnClickListener(this)
        ayn.setOnClickListener(this)
        haaa.setOnClickListener(this)
        kha.setOnClickListener(this)
        ha.setOnClickListener(this)
        jeem.setOnClickListener(this)
        keyValues.put(R.id.dhad, "ض")
        keyValues.put(R.id.suwad, "ص")
        keyValues.put(R.id.qaf, "ق")
        keyValues.put(R.id.fa, "ف")
        keyValues.put(R.id.ghain, "غ")
        keyValues.put(R.id.ayn, "ع")
        keyValues.put(R.id.haaa, "ه")
        keyValues.put(R.id.kha, "خ")
        keyValues.put(R.id.ha, "ح")
        keyValues.put(R.id.jeem, "ج")
        val sheen: Button = findViewById(R.id.sheen)
        val seen: Button = findViewById(R.id.seen)
        val ya: Button = findViewById(R.id.ya)
        val ba: Button = findViewById(R.id.ba)
        val lam: Button = findViewById(R.id.lam)
        val alif: Button = findViewById(R.id.hamza)
        val ta: Button = findViewById(R.id.ta)
        val nun: Button = findViewById(R.id.nun)
        val meem: Button = findViewById(R.id.meem)
        val kaf: Button = findViewById(R.id.kaf)
        sheen.setOnClickListener(this)
        seen.setOnClickListener(this)
        ya.setOnClickListener(this)
        ba.setOnClickListener(this)
        lam.setOnClickListener(this)
        alif.setOnClickListener(this)
        ta.setOnClickListener(this)
        nun.setOnClickListener(this)
        meem.setOnClickListener(this)
        kaf.setOnClickListener(this)
        //   key00.setOnClickListener(this);
        key_delete.setOnClickListener(this)
        key_AC.setOnClickListener(this)
        key_enter.setOnClickListener(this)
        //  key_dot.setOnClickListener(this);
        keyValues.put(R.id.sheen, "ش")
        keyValues.put(R.id.seen, "س")
        keyValues.put(R.id.ya, "ي")
        keyValues.put(R.id.ba, "ب")
        keyValues.put(R.id.lam, "ل")
        keyValues.put(R.id.hamza, "ء")
        keyValues.put(R.id.ta, "ت")
        keyValues.put(R.id.nun, "ن")
        keyValues.put(R.id.meem, "م")
        keyValues.put(R.id.kaf, "ك")
        val zoay: Button = findViewById(R.id.zoay)
        val toay: Button = findViewById(R.id.toay)
        val dhal: Button = findViewById(R.id.dhal)
        val dal: Button = findViewById(R.id.dal)
        val zaa: Button = findViewById(R.id.zaa)
        val raa: Button = findViewById(R.id.raa)
        val waw: Button = findViewById(R.id.waw)
        val tamarboot: Button = findViewById(R.id.tamarboota)
        val tha: Button = findViewById(R.id.tha)
        zoay.setOnClickListener(this)
        toay.setOnClickListener(this)
        dhal.setOnClickListener(this)
        dal.setOnClickListener(this)
        zaa.setOnClickListener(this)
        raa.setOnClickListener(this)
        waw.setOnClickListener(this)
        tamarboot.setOnClickListener(this)
        tha.setOnClickListener(this)
        keyValues.put(R.id.zoay, "ظ")
        keyValues.put(R.id.toay, "ط")
        keyValues.put(R.id.dhal, "ذ")
        keyValues.put(R.id.dal, "د")
        keyValues.put(R.id.zaa, "ز")
        keyValues.put(R.id.raa, "ر")
        keyValues.put(R.id.waw, "و")
        keyValues.put(R.id.tamarboota, "ة")
        keyValues.put(R.id.tha, "ث")
        clearParameters()
    }

    private fun clearParameters() {
        nasara.setVisibility(View.GONE)
        zaraba.setVisibility(View.GONE)
        fataha.setVisibility(View.GONE)
        samia.setVisibility(View.GONE)
        karuma.setVisibility(View.GONE)
        haseeba.setVisibility(View.GONE)
        ifal.setVisibility(View.GONE)
        tafeel.setVisibility(View.GONE)
        tafaul.setVisibility(View.GONE)
        tafaaul.setVisibility(View.GONE)
        infala.setVisibility(View.GONE)
        iftiala.setVisibility(View.GONE)
        istifala.setVisibility(View.GONE)
        mufala.setVisibility(View.GONE)
    }

    override fun onClick(view: View) {
        //   hideKeyboardSoft();
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN, BottomSheetBehavior.STATE_DRAGGING, BottomSheetBehavior.STATE_SETTLING, BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        if (inputConnection == null) {
            val logTag = "Keyboard"
            Log.i(logTag, "Input connection == null")
            return
        }
        val currentText: CharSequence =
            inputConnection.getExtractedText(ExtractedTextRequest(), 0).text
        val beforeCursorText: CharSequence? =
            inputConnection.getTextBeforeCursor(currentText.length, 0)
        val afterCursorText: CharSequence? =
            inputConnection.getTextAfterCursor(currentText.length, 0)
        when (view.id) {
            R.id.conjugatorsetting -> {
                val fragment = SettingsFragmentVerb()
                val transaction: FragmentTransaction =
                    getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.replace(R.id.frame_container, fragment)
                transaction.addToBackStack("setting")
                transaction.commit()
            }

            R.id.qurangrammar -> {
                val intent = Intent(this@ConjugatorAct, QuranGrammarAct::class.java)
                finish()
                startActivity(intent)
            }

            R.id.fab -> {
                toggleBottomSheet()
                val charSequence: CharSequence? =
                    inputConnection.getTextBeforeCursor(currentText.length, 0)
                if (charSequence.toString().length == 3) {
                    inputtext = charSequence.toString()
                    InitSelecton(charSequence.toString())
                }
            }

            R.id.key_enter -> {
                val charSequence: CharSequence? =
                    inputConnection.getTextBeforeCursor(currentText.length, 0)
                if (charSequence.toString().length == 3) {
                    inputtext = charSequence.toString()
                    InitSelecton(charSequence.toString())
                }
            }

            R.id.key_delete -> {
                val selectedText: CharSequence = inputConnection.getSelectedText(0)
                val charSequences: CharSequence? = inputConnection.getTextBeforeCursor(10, 10)
                if (TextUtils.isEmpty(selectedText)) inputConnection.deleteSurroundingText(
                    1,
                    0
                ) else inputConnection.commitText("", 1)
            }

            R.id.key_AC -> if (beforeCursorText != null) {
                if (afterCursorText != null) {
                    inputConnection.deleteSurroundingText(
                        beforeCursorText.length,
                        afterCursorText.length
                    )
                }
            }

            R.id.tafeel -> {
                typedValues
                //   rivate void InitDiaalog(String root, String wazan, String verbtype) {
                InitDiaalog(mazeedEntityVerbs[0].root, "2", "mazeed")
            }

            R.id.mujarradbtn -> {
                val dataBundle = Bundle()
                dataBundle.putString(VERBTYPE, "mujarrad")
                val intents = Intent(this@ConjugatorAct, SarfSagheerActivity::class.java)
                intents.putExtras(dataBundle)
                finish()
                startActivity(intents)
            }

            R.id.mazeedbtn -> {
                val mdataBundle = Bundle()
                mdataBundle.putString(VERBTYPE, "mazeed")
                val mintent = Intent(this@ConjugatorAct, SarfSagheerActivity::class.java)
                mintent.putExtras(mdataBundle)
                finish()
                startActivity(mintent)
            }

            R.id.mufala -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "3", "mazeed")
            }

            R.id.ifal -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "1", "mazeed")
            }

            R.id.tafaul -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "7", "mazeed")
            }

            R.id.tafaaaul -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "8", "mazeed")
            }

            R.id.infala -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "4", "mazeed")
            }

            R.id.iftiala -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "5", "mazeed")
            }

            R.id.istifala -> {
                typedValues
                //   rivate void InitDiaalog(String root, String wazan, String verbtype) {
                InitDiaalog(mazeedEntityVerbs[0].root, "9", "mazeed")
            }

            R.id.nasara -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "1", "mujarrad")
            }

            R.id.zaraba -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "2", "mujarrad")
            }

            R.id.samia -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "4", "mujarrad")
            }

            R.id.fataha -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "3", "mujarrad")
            }

            R.id.karuma -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "5", "mujarrad")
            }

            R.id.hasiba -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "6", "mujarrad")
            }

            else -> inputConnectionCommitText(view)
        }
    }

    fun toggleBottomSheet() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            //    btnBottomSheet.setText("Close sheet");
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            //    btnBottomSheet.setText("Expand sheet");
        }
    }

    // inputConnectionCommitText(view);
    private val typedValues: Unit
        private get() {
            val charSequence: CharSequence
            val beforeCursorText: CharSequence
            val afterCursorText: CharSequence
            val currentText: CharSequence
            currentText = inputConnection.getExtractedText(ExtractedTextRequest(), 0).text
            beforeCursorText = inputConnection.getTextBeforeCursor(currentText.length, 0)!!
            afterCursorText = inputConnection.getTextAfterCursor(currentText.length, 0)!!
            // inputConnectionCommitText(view);
            charSequence = inputConnection.getTextBeforeCursor(currentText.length, 0)!!
            if (charSequence.toString().length == 3) {
                inputtext = charSequence.toString()
                InitSelecton(charSequence.toString())
            }
        }

    fun setInputConnection(ic: InputConnection?) {
        if (ic != null) {
            inputConnection = ic
        }
    }

    private fun inputConnectionCommitText(view: View) {
        val value: String = keyValues.get(view.id)
        inputConnection.commitText(value, 1)
    }

    private fun InitSelecton(roots: String) {
        keyboard!!.visibility = LinearLayout.GONE
        editTextAuto.clearFocus()
        val split = roots.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val root = split[0]
        val pref = SharedPref(this)
        tlist = ListView(this@ConjugatorAct)
        mlist = ListView(this@ConjugatorAct)
        val utils = VerbDatabaseUtils(this@ConjugatorAct)
        mujarradVerbs = utils.getMujarradVerbs(root) as ArrayList<MujarradVerbs>
        for (s in mujarradVerbs) {
            when (s.bab) {
                "1" -> {
                    nasara.setText(s.babname)
                    nasara.setEnabled(true)
                    nasara.setVisibility(View.VISIBLE)
                }

                "2" -> {
                    zaraba.setVisibility(View.VISIBLE)
                    zaraba.setEnabled(true)
                    zaraba.setText(s.babname)
                }

                "3" -> {
                    fataha.setVisibility(View.VISIBLE)
                    fataha.setEnabled(true)
                    fataha.setText(s.babname)
                }

                "4" -> {
                    samia.setVisibility(View.VISIBLE)
                    samia.setEnabled(true)
                    samia.setText(s.babname)
                }

                "5" -> {
                    karuma.setVisibility(View.VISIBLE)
                    karuma.setEnabled(true)
                    karuma.setText(s.babname)
                }

                "6" -> {
                    haseeba.setVisibility(View.VISIBLE)
                    haseeba.setEnabled(true)
                    haseeba.setText(s.babname)
                }
            }
        }
        mazeedEntityVerbs = utils.getMazeedRoot(root) as ArrayList<MazeedEntity>
        for (s in mazeedEntityVerbs) {
            when (s.form) {
                "1" -> {
                    ifal.setText(s.babname)
                    ifal.setEnabled(true)
                    ifal.setVisibility(View.VISIBLE)
                }

                "2" -> {
                    tafeel.setEnabled(true)
                    tafeel.setText(s.babname)
                    tafeel.setVisibility(View.VISIBLE)
                }

                "3" -> {
                    mufala.setEnabled(true)
                    mufala.setText(s.babname)
                    mufala.setVisibility(View.VISIBLE)
                }

                "4" -> {
                    infala.setEnabled(true)
                    infala.setText(s.babname)
                    infala.setVisibility(View.VISIBLE)
                }

                "5" -> {
                    iftiala.setEnabled(true)
                    iftiala.setText(s.babname)
                    iftiala.setVisibility(View.VISIBLE)
                }

                "9" -> {
                    istifala.setEnabled(true)
                    istifala.setText(s.babname)
                    istifala.setVisibility(View.VISIBLE)
                }

                "7" -> {
                    tafaul.setEnabled(true)
                    tafaul.setText(s.babname)
                    tafaul.setVisibility(View.VISIBLE)
                }

                "8" -> {
                    tafaaul.setEnabled(true)
                    tafaaul.setText(s.babname)
                    tafaaul.setVisibility(View.VISIBLE)
                }
            }
        }
        val thulathia = ArrayList<String>()
        val mazeed = ArrayList<String>()
        for (entity in mujarradVerbs) {
            thulathia.add(entity.babname)
        }
        for (dict in mazeedEntityVerbs) {
            mazeed.add(dict.babname + "," + dict.form)
        }
        if ((thulathia.size == 0) and (mazeed.size == 0)) {
            editTextAuto.setText(R.string.notfound)
        }
    }

    private fun InitDiaalog(root: String, wazan: String, verbtype: String) {
        val dataBundle = Bundle()
        val selectedRadioButton: RadioButton =
            findViewById(verbmood.getCheckedRadioButtonId()) as RadioButton
        //get RadioButton text
        val selected: String = selectedRadioButton.getText().toString()
        // display it as Toast to the user
        val checked: Boolean = indicative.isChecked()
        val accusativeChecked: Boolean = subjunctive.isChecked()
        when (selected) {
            "Indicative" -> dataBundle.putString(VERBMOOD, "Indicative")
            "Subjunctive" -> dataBundle.putString(VERBMOOD, "Subjunctive")
            "Jussive" -> dataBundle.putString(VERBMOOD, "Jussive")
            "Emphasized" -> dataBundle.putString(VERBMOOD, "Emphasized")
        }
        //  ninitThulathiAdapter(root);
        dataBundle.putString(QURAN_VERB_WAZAN, wazan)
        dataBundle.putString(QURAN_VERB_ROOT, root)
        dataBundle.putString(VERBTYPE, verbtype)
        dataBundle.putBoolean(SARFKABEER, isSarfKabeed)
        isSarfKabeed = false;
        val intent = Intent(this@ConjugatorAct, ConjugatorTabsActivity::class.java)
        intent.putExtras(dataBundle)
        startActivity(intent)
    }

    companion object {
        var contextOfApplication: Context? = null
    }
}