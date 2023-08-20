package org.sj.conjugator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.MUJARRADVERBTAG
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.SARFKABEERWEAKNESS
import com.example.Constant.VERBMOOD
import com.example.Constant.VERBTYPE
import com.example.mushafconsolidated.R
import org.sj.conjugator.adapter.IsmZarffKabeerAdapter
import org.sj.conjugator.utilities.GatherAll
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class FragmentIsmZarf : Fragment() {
    var recyclerView: RecyclerView? = null
    var isAugmented = false
    var isUnAugmented = false
    private lateinit var callButton: FloatingTextButton
    private lateinit  var layoutManager: LinearLayoutManager
    private   var skabeer = ArrayList<ArrayList<*>>()
    private   val getsarfsagheer: ArrayList<ArrayList<*>>? = null
    private   val verbformmazeed = 0
    private lateinit var verbformthulathi: String
    private lateinit   var augmentedFormula: String
    private   lateinit var unaugmentedFormula: String
    private  lateinit  var verbroot: String
    private lateinit   var verbmood: String
    private lateinit   var verbweakness: String
    fun newInstance(): FragmentIsmZarf {
        val f = FragmentIsmZarf()
        val b = Bundle()
        //  f.setArguments(b);
        val dataBundle = requireArguments()
        verbformthulathi = dataBundle.getString(QURAN_VERB_WAZAN)!!
        verbweakness = dataBundle.getString(SARFKABEERWEAKNESS)!!
        f.arguments = dataBundle
        return f
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.sarfkabeerheader, container, false)
        callButton = view.findViewById(R.id.action_buttons)
        val dataBundle = arguments
        if (dataBundle != null) {
            val callingfragment = dataBundle.getString(MUJARRADVERBTAG)
            if (callingfragment != null) {
                if (callingfragment == "tverblist") {
                    callButton.setVisibility(View.VISIBLE)
                } else {
                    callButton.setVisibility(View.GONE)
                }
            } else {
                callButton.setVisibility(View.GONE)
            }
        }
        callButton.setOnClickListener(View.OnClickListener {
            val fm = activity
                ?.getSupportFragmentManager()
            if (fm != null) {
                fm.popBackStack()
            }
        })
        assert(dataBundle != null)
        if (dataBundle!!.getString(VERBTYPE) == "mujarrad") {
            isUnAugmented = true
            unaugmentedFormula = dataBundle.getString(QURAN_VERB_WAZAN)!!
        } else {
            augmentedFormula = dataBundle.getString(QURAN_VERB_WAZAN)!!
            isAugmented = true
        }
        verbroot = dataBundle.getString(QURAN_VERB_ROOT)!!
        verbmood = dataBundle.getString(VERBMOOD)!!
        recyclerView = view.findViewById(R.id.sarfrecview)
        skabeer = setUparrays(view)
        return view
    }

    private fun setUparrays(view: View): ArrayList<ArrayList<*>> {
        if (isUnAugmented) {
            ninitThulathiAdapter()
        } else {
            //   initMazeedAdapter();
            initMazeedAdapterNew()
        }
        return skabeer
    }

    private fun initMazeedAdapterNew() {}
    private fun ninitThulathiAdapter() {
        val mujarradListing: ArrayList<ArrayList<*>> =
            GatherAll.instance.getMujarradZarf(verbroot, unaugmentedFormula)
        if (!mujarradListing.isEmpty()) {
            val ska = IsmZarffKabeerAdapter(mujarradListing, requireContext())
            recyclerView!!.adapter = ska
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView
        recyclerView = view.findViewById(R.id.sarfrecview)
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val ref: ImageView
        ref = view.findViewById(R.id.dismissView)
        //dismiss(ref);
    }

    private fun dismiss(ref: ImageView) {
        ref.setOnClickListener { val s = "tagone" }
    }

    companion object {
        private const val TAG = "PermissionDemo"
    }
}