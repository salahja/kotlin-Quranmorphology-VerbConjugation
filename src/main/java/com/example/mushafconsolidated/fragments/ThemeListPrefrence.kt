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
class ThemeListPrefrence constructor() : BottomSheetDialogFragment() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var mItemClickListener: OnItemClickListener? = null
    var radioGroup: RadioGroup? = null
    private var fontQuranAdapter: ThemeListPrefrence.FontQuranAdapter? = null
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
        recyclerView.setLayoutManager(LinearLayoutManager(requireContext()))
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
            layout.theme_prference_dialog, parent, false
        )
    ), View.OnClickListener {
        var purple: RadioButton
        var black: RadioButton
        var dark_blue: RadioButton
        var green: RadioButton
        var brown: RadioButton

        init {
            // TODO: Customize the item layout
            //  super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            purple = itemView.findViewById(R.id.Purple)
            black = itemView.findViewById(R.id.Black)
            dark_blue = itemView.findViewById(R.id.Dark_Blue)
            green = itemView.findViewById(R.id.green)
            brown = itemView.findViewById(R.id.Brown)
          //  frameLayout = itemView.findViewById(R.id.bottomSheet)
            itemView.setOnClickListener(this)
            purple.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("themepref", "light")
                    editor.apply()
                    dismiss()
                }
            })
            black.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("themepref", "dark")
                    editor.apply()
                    dismiss()
                }
            })
            dark_blue.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("themepref", "blue")
                    editor.apply()
                    dismiss()
                }
            })
            green.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                    editor.putString("themepref", "green")
                    editor.apply()
                    dismiss()
                }
            })
            brown.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View?) {
                    val editor: SharedPreferences.Editor =
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                    editor.putString("themepref", "brown")
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
        RecyclerView.Adapter<ThemeListPrefrence.ViewHolder>() {
        private var mItemClickListener: OnItemClickListener? = null
        public override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ThemeListPrefrence.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context!!),
                parent
            )
        }

        public override fun onBindViewHolder(holder: ThemeListPrefrence.ViewHolder, position: Int) {
            val sharedPreferences: SharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    requireContext()
                )
            val theme: String? = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                requireActivity()
            ).getString("themepref", "dark")
            if ((theme == "light")) {
                holder.purple.setChecked(true)
            } else if ((theme == "dark")) {
                holder.black.setChecked(true)
            } else if ((theme == "blue")) {
                holder.dark_blue.setChecked(true)
            } else if ((theme == "light")) {
                holder.green.setChecked(true)
            } else if ((theme == "brown")) {
                holder.brown.setChecked(true)
            } else if ((theme == "green")) {
                holder.green.setChecked(true)
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
        fun newInstance(): ThemeListPrefrence {
            val fragment: ThemeListPrefrence = ThemeListPrefrence()
            return fragment
        }
    }
}