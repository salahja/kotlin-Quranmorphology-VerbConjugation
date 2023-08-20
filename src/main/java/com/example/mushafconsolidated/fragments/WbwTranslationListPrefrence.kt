package com.example.mushafconsolidated.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
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
class WbwTranslationListPrefrence constructor() : BottomSheetDialogFragment() {
    private val chap_id: Int = 0
    private val verse_id: Int = 0
    private val name: String? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var mItemClickListener: OnItemClickListener? = null
    var radioGroup: RadioGroup? = null
    private var fontQuranAdapter: WbwTranslationListPrefrence.FontQuranAdapter? = null
    lateinit var frameLayout: RelativeLayout
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

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setLayoutManager(LinearLayoutManager(context!!))
        val details: ArrayList<String> = ArrayList()
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
            layout.wbw_prference_dialog, parent, false
        )
    ), View.OnClickListener {
        var wbwen: RadioButton
        var wbwbangla: RadioButton
        var wbwindonesia: RadioButton
        var wbwurdu: RadioButton

        init {
            wbwen = itemView.findViewById(R.id.wbwen)
            wbwbangla = itemView.findViewById(R.id.wbwbangla)
            wbwurdu = itemView.findViewById(R.id.wbwurdu)
            wbwindonesia = itemView.findViewById(R.id.wbwindonesia)
            frameLayout = itemView.findViewById(R.id.bottomSheet)
            itemView.setOnClickListener(this)
            wbwen.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    editor.putString("wbw", "en")
                    editor.apply()
                    dismiss()
                }
            })
            wbwbangla.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    editor.putString("wbw", "bn")
                    editor.apply()
                    dismiss()
                }
            })
            wbwindonesia.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    editor.putString("wbw", "in")
                    editor.apply()
                    dismiss()
                }
            })
            wbwurdu.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("wbw", "ur")
                    editor.apply()
                    dismiss()
                }
            })
        }

        public override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, getLayoutPosition())
            }
        }
    }

    private inner class FontQuranAdapter constructor() :
        RecyclerView.Adapter<WbwTranslationListPrefrence.ViewHolder>() {
        private var mItemClickListener: OnItemClickListener? = null
        public override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): WbwTranslationListPrefrence.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context!!),
                parent
            )
        }

        public override fun onBindViewHolder(
            holder: WbwTranslationListPrefrence.ViewHolder,
            position: Int
        ) {
            val sharedPreferences: SharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    requireContext()
                )
            val theme: String? = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                requireActivity()
            ).getString("wbw", "en")
            if ((theme == "en")) {
                holder.wbwen.setChecked(true)
            } else if ((theme == "bn")) {
                holder.wbwbangla.setChecked(true)
            } else if ((theme == "ur")) {
                holder.wbwurdu.setChecked(true)
            } else if ((theme == "in")) {
                holder.wbwindonesia.setChecked(true)
            }
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

        // TODO: Customize parameter argument names
        private val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(): WbwTranslationListPrefrence {
            val fragment: WbwTranslationListPrefrence = WbwTranslationListPrefrence()
            return fragment
        }
    }
}