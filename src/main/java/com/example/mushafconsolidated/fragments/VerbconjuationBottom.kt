package com.example.mushafconsolidated.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.layout

import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * ThemeListPrefrence.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */
class VerbconjuationBottom constructor() : BottomSheetDialogFragment() {
    var mItemClickListener: OnItemClickListener? = null
    var radioGroup: RadioGroup? = null
    private var fontQuranAdapter: VerbconjuationBottom.FontQuranAdapter? = null
    lateinit var frameLayout: RelativeLayout
    var mLocalityList: List<String>? = ArrayList()
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.quran_list_dialog, container, false)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLocalityList = requireArguments().getStringArrayList("list")
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setLayoutManager(LinearLayoutManager(context!!))
        val details: ArrayList<String> = ArrayList()
        mLocalityList = requireArguments().getStringArrayList("list")
        fontQuranAdapter = FontQuranAdapter()
        recyclerView.setAdapter(fontQuranAdapter)
        fontQuranAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            public override fun onItemClick(v: View?, position: Int) {
                val checkedRadioButtonId: Int = radioGroup!!.getCheckedRadioButtonId()
            }
        })
    }

    private inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            layout.conjugationbottom, parent, false
        )
    ), View.OnClickListener {
        var hua: TextView
        var huma: TextView
        var hum: TextView
        var hia: TextView
        var humaf: TextView
        var hunna: TextView
        var anta: TextView
        var antumam: TextView
        var antum: TextView
        var anti: TextView
        var antumaf: TextView
        var antunna: TextView
        var ana: TextView
        var nahnu: TextView

        init {
            // TODO: Customize the item layout
            //  super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            hua = itemView.findViewById(R.id.hua)
            huma = itemView.findViewById(R.id.huma)
            hum = itemView.findViewById(R.id.hum)
            hia = itemView.findViewById(R.id.hia)
            humaf = itemView.findViewById(R.id.humaf)
            hunna = itemView.findViewById(R.id.hunna)
            anta = itemView.findViewById(R.id.anta)
            antumam = itemView.findViewById(R.id.antuma)
            antum = itemView.findViewById(R.id.antum)
            anti = itemView.findViewById(R.id.anti)
            antumaf = itemView.findViewById(R.id.antumaf)
            antunna = itemView.findViewById(R.id.antunna)
            ana = itemView.findViewById(R.id.ana)
            nahnu = itemView.findViewById(R.id.nahnu)
            frameLayout = itemView.findViewById(R.id.bottomSheet)
            itemView.setOnClickListener(this)
        }

        public override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, getLayoutPosition())
            }
        }
    }

    private inner class FontQuranAdapter constructor() :
        RecyclerView.Adapter<VerbconjuationBottom.ViewHolder>() {
        private var mItemClickListener: OnItemClickListener? = null
        public override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): VerbconjuationBottom.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context!!),
                parent
            )
        }

        public override fun onBindViewHolder(
            holder: VerbconjuationBottom.ViewHolder,
            position: Int
        ) {
            val sharedPreferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                    requireContext()
                )
            val theme: String? = PreferenceManager.getDefaultSharedPreferences(
                requireActivity()
            ).getString("themepref", "dark")
            holder.hua.setText(mLocalityList!!.get(0))
            holder.huma.setText(mLocalityList!!.get(1))
            holder.hum.setText(mLocalityList!!.get(2))
            holder.hia.setText(mLocalityList!!.get(3))
            holder.humaf.setText(mLocalityList!!.get(4))
            holder.hunna.setText(mLocalityList!!.get(5))
            holder.anta.setText(mLocalityList!!.get(6))
            holder.antumam.setText(mLocalityList!!.get(7))
            holder.antum.setText(mLocalityList!!.get(8))
            holder.anti.setText(mLocalityList!!.get(9))
            holder.antumaf.setText(mLocalityList!!.get(7))
            holder.antunna.setText(mLocalityList!!.get(10))
            holder.ana.setText(mLocalityList!!.get(11))
            holder.nahnu.setText(mLocalityList!!.get(12))
        }

        public override fun getItemCount(): Int {
            return 1
        }

        fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
            this.mItemClickListener = mItemClickListener
        }
    }

    companion object {
        val TAG: String = "opton"
        private val localityList: List<*>? = null

        // TODO: Customize parameter argument names
        private val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(list: ArrayList<*>?): VerbconjuationBottom {
            val fragment: VerbconjuationBottom = VerbconjuationBottom()
            val bundle: Bundle = Bundle()
            bundle.putParcelableArrayList("list", list as java.util.ArrayList<out Parcelable>?)
            fragment.setArguments(bundle)
            return fragment
        }
    }
}