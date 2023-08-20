package com.example.mushafconsolidated

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils.Utils
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidatedimport.MushafApplication.Companion.appContext
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * FontQuranListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */
class BottomOptionDialog constructor() : BottomSheetDialogFragment() {
    private var chap_id: Int = 0
    private var verse_id: Int = 0
    private var name: String? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getArguments() != null) {

            name =arguments?.getString("name")
            chap_id = arguments?.getInt("chap_id")!!
            verse_id = arguments?.getInt("verse_no")!!
        }
        val utils: Utils = Utils(getActivity())
        var appContext: Context = requireContext()
        //  setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Material3_BottomSheetDialog);
    }

   public lateinit var mItemClickListener: OnItemClickListener
    var radioGroup: RadioGroup? = null
    private var fontQuranAdapter: FontQuranAdapter? = null
    lateinit var frameLayout: RelativeLayout
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        if (mItemClickListener != null) {
            this.mItemClickListener = mItemClickListener
        }
    }

    override fun onCreateView(
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


    private class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup?) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.options_bottom, parent, false)),
        View.OnClickListener {

        var ivCopy: ImageView
        var ivBookmark: ImageView
        var ivShare: ImageView
        var textView: TextView
        var textView2: TextView
        var textView3: TextView? = null

        init {
            // TODO: Customize the item layout
            //  super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            ivCopy = itemView.findViewById(R.id.imageView)
            ivBookmark = itemView.findViewById(R.id.imageView2)
            ivShare = itemView.findViewById(R.id.imageView3)
            textView = itemView.findViewById(R.id.textView)
            textView2 = itemView.findViewById(R.id.textView2)
            var frameLayout = itemView.findViewById<RelativeLayout>(R.id.bottomSheet)
            itemView.setOnClickListener(this)

            ivCopy.setOnClickListener {
                val editor =

                    android.preference.PreferenceManager.getDefaultSharedPreferences(appContext)
                        .edit()

                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putString("quranFont", "AlQalam.ttf")
                editor.apply()

            }
            ivBookmark.setOnClickListener {
                val editor =
                    android.preference.PreferenceManager.getDefaultSharedPreferences(appContext)
                        .edit()
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putString("quranFont", "me_quran.ttf")
                editor.apply()
              //  dismiss()
            }
            ivShare.setOnClickListener {
                val editor =
                    android.preference.PreferenceManager.getDefaultSharedPreferences(appContext)
                        .edit()
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putString("quranFont", "Pdms.ttf")
                editor.apply()
             //   dismiss()
            }
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }


    }

    private inner class FontQuranAdapter constructor() :
        RecyclerView.Adapter<BottomOptionDialog.ViewHolder>() {
        private var mItemClickListener: OnItemClickListener? = null
        public override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BottomOptionDialog.ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context!!), parent)



        }

        public override fun onBindViewHolder(holder: BottomOptionDialog.ViewHolder, position: Int) {
            val sharedPreferences: SharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    requireContext()
                )
            val mequran: Typeface = Typeface.createFromAsset(
                requireContext().getAssets(), "me_quran.ttf"
            )
            val qalam: Typeface = Typeface.createFromAsset(
                requireContext().getAssets(), "AlQalam.ttf"
            )
            val amiri: Typeface = Typeface.createFromAsset(
                requireContext().getAssets(), "Pdms.ttf"
            )
            val isNightmode: String? = sharedPreferences.getString("themepref", "dark")
            holder.ivShare.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(view: View?) {
                    System.out.printf("check")
                }
            })
            /*      holder.ivShare.setOnClickListener(convertView -> {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,
                        holder.duaheader.getText() + "\n\n" +
                                holder.tvDuaArabic.getText() + "\n\n" +
                                holder.tvDuaTranslation.getText() + "\n\n" +
                                holder.tvDuaReference.getText() + "\n\n" +
                                convertView.getResources().getString(R.string.action_share_credit)
                );
                intent.setType("text/plain");
                convertView.context!!.startActivity(
                        Intent.createChooser(
                                intent,
                                convertView.getResources().getString(R.string.action_share_title)
                        )
                );
            });
*/
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
        fun newInstance(data: Array<String>): BottomOptionDialog {
            val fragment: BottomOptionDialog = BottomOptionDialog()
            val args: Bundle = Bundle()
            args.putStringArray(BottomOptionDialog.Companion.ARG_OPTIONS_DATA, data)
            fragment.setArguments(args)
            return fragment
        }
    }
}