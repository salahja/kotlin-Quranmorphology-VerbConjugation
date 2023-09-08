package com.example.mushafconsolidated.fragments




/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */


import android.app.ProgressDialog
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceManager
import com.example.Constant
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.model.SarfSagheerPOJO



 
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
class GrammerFragmentsBottomSheet : BottomSheetDialogFragment() {
    var chapterid = 0
    var ayanumber = 0
    var isMazeedSarfSagheer = false
    var isThulathiSarfSagheer = false
    var isverbconjugaton = false
    var participles = false
    var isNoun = false
    lateinit var expandableListView: ExpandableListView
    var expandableListTitle: List<String>? = null
    var expandableListDetail: LinkedHashMap<String, List<SpannableString>>? = null
    var kanaExpandableListDetail: List<SpannableString>? = null
    var wordetailsall = HashMap<Int, HashMap<String, SpannableStringBuilder>>()
    var verbdetailsall = HashMap<Int, HashMap<String, String>>()
    var vbdetail = HashMap<String, String>()
    var wordbdetail: HashMap<String, SpannableStringBuilder>? = null
    var mudhafspans = BackgroundColorSpan(Constant.MIDNIGHTBLUE)
    var showGrammarFragments = false
    var ThulathiMazeedConjugatonList: ArrayList<ArrayList<*>>? = null
    var sarf: SarfSagheerPOJO? = null
    private val spannableHarf: SpannableStringBuilder? = null
    private val spannable: SpannableStringBuilder? = null
    private val HarfNasbAndZarf: String? = null
    private var dialog: AlertDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.expand_list, container, false)
        // RecyclerView recyclerView = view.findViewById(R.id.wordByWordRecyclerView);
        expandableListView = view.findViewById<View>(R.id.expandableListView) as ExpandableListView
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
        val bundle = this.requireArguments()
        val stringArray = bundle.getStringArray(ARG_OPTIONS_DATA)

        chapterid = stringArray!![0].toInt()
        val shared = PreferenceManager.getDefaultSharedPreferences(
            requireContext()
        )
        showGrammarFragments = shared.getBoolean("fragments", false)
        ayanumber = stringArray[1].toInt()
        val ex = Executors.newSingleThreadExecutor()
        val utils = Utils(activity)
        ex.execute(object : Runnable {
            val mProgressBar = ProgressDialog(activity)
            override fun run() {
                activity!!.runOnUiThread { dialog!!.show() }
                val corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>
                corpusSurahWord = utils.getCorpusWbwBySurahAyahWordid(chapterid, ayanumber, 1) as ArrayList<NewCorpusExpandWbwPOJO>
                val corpusNounWord: List<NounCorpus?>? =
                    utils.getQuranNouns(chapterid, ayanumber, 1)
                val expandableListData =
                    ExpandableListData(chapterid, ayanumber, corpusSurahWord, utils)
                expandableListDetail = expandableListData.data
                kanaExpandableListDetail = expandableListData.kana
                expandableListTitle = ArrayList(expandableListDetail!!.keys)
                ThulathiMazeedConjugatonList = ArrayList()
                isverbconjugaton=false
                participles=false

                corpusNounWord?.size
                activity!!.runOnUiThread {
                    ex.shutdown()
                    dialog!!.dismiss()
                    val grammarFragmentsListAdapter: GrammarFragmentsListAdapter
                    grammarFragmentsListAdapter = GrammarFragmentsListAdapter(
                        context!!, expandableListTitle as ArrayList<String>,
                        expandableListDetail!!
                    )
                    expandableListView.setAdapter(grammarFragmentsListAdapter)
                    for (i in 0 until grammarFragmentsListAdapter.getGroupCount()) {
                        expandableListView!!.expandGroup(i)
                    }
                }
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val TAG = "bottom"

        // TODO: Customize parameter argument names
        private const val ARG_OPTIONS_DATA = "options_data"

        // TODO: Customize parameters
        fun newInstance(data: Array<String>): GrammerFragmentsBottomSheet {
            val fragment = GrammerFragmentsBottomSheet()
            val args = Bundle()
            args.putStringArray(ARG_OPTIONS_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}