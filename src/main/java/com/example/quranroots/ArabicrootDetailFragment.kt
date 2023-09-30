package com.example.quranroots

import ArabicrootListFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.WORDDETAILS
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.databinding.FragmentArabicrootDetailBinding
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.quranroots.placeholder.PlaceholderContent
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * A fragment representing a single arabicroot detail screen.
 * This fragment is either contained in a [ArabicrootListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class ArabicrootDetailFragment
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment(), AdapterView.OnItemClickListener {
    /**
     * The placeholder content this fragment is presenting.
     */
    private var mItem: PlaceholderContent.PlaceholderItem? = null
    private val mToolbarLayout: CollapsingToolbarLayout? = null
    private val mTextView: TextView? = null
    private var rootsArrayList = ArrayList<String>()
    private var adapter: RootDetailAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        assert(arguments != null)
        if (requireArguments().containsKey(ARG_ITEM_ID)) {
            // Load the placeholder content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = PlaceholderContent.ITEM_MAP[requireArguments().getString(ARG_ITEM_ID)]
            if (mItem == null) {
                rootsArrayList.add("")
            } else {
                rootsArrayList = mItem!!.details
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View =
            inflater.inflate(R.layout.fragment_arabicroot_details_list, container, false)
        val recyclerView: RecyclerView =
            view.findViewById(R.id.arabicroot_detaillist_rec)
        var layoutManager = LinearLayoutManager(activity)
        adapter = RootDetailAdapter(rootsArrayList, requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager = GridLayoutManager(activity, 4)
        layoutManager.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 3 else 1
            }
        })
        recyclerView.setHasFixedSize(true)
        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


        //  view.setOnDragListener(dragListener);
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val binding: FragmentArabicrootDetailBinding? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter?.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val bmark = adapter!!.getItem(position) as String
                //        ChaptersAnaEntity surah = (ChaptersAnaEntity) bookmarksShowAdapter.getItem(position);
                val dataBundle = Bundle()
                dataBundle.putString(QURAN_VERB_ROOT, bmark)

                assert(arguments != null)
                if (arguments!!.containsKey(WORDDETAILS)) {
                    dataBundle.putString(WORDDETAILS, arguments!!.getString(WORDDETAILS))
                }
                val intent = Intent(activity, RootBreakupAct::class.java)
                dataBundle.putString(QURAN_VERB_ROOT, bmark)
                intent.putExtras(dataBundle)
                startActivity(intent)
            }
        })
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
        System.out.printf("check")
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}