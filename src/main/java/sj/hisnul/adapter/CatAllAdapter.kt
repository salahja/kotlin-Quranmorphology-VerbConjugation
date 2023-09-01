package sj.hisnul.adapter

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.entity.hduanames
import java.util.Locale

class CatAllAdapter( private val context: Context) :
    RecyclerView.Adapter<CatAllAdapter.ViewHolder?>(), Filterable {

    var mItemClickListener: OnItemClickListener? = null


    private var mList:   List<hduanames> =ArrayList<hduanames>()

    private var duasfiltered:   List<hduanames> =ArrayList<hduanames>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rwz,parent, false);
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.dua_group_item_card, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)

        val prefs: SharedPreferences =
            android.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)

        //  String arabic_font_category = prefs.getString("arabic_font_category", "DejaVuSans.tff");
        val isNightmode: String? = sharedPreferences.getString("themepref", "dark")
        if (isNightmode == "dark") {
            holder.tvReference.setCompoundDrawableTintList(ColorStateList.valueOf(Color.WHITE))
        }
        if (isNightmode == "dark") {
            holder.cardview.setCardBackgroundColor(context.resources.getColor(R.color.bg_dark_blue))
        } else if (isNightmode == "blue") {
            holder.cardview.setCardBackgroundColor(context.resources.getColor(R.color.bg_dark_blue))
        } else if (isNightmode == "white") {
            holder.cardview.setCardBackgroundColor(context.resources.getColor(R.color.md_theme_dark_inversePrimary))
        }
        val catOne: hduanames = duasfiltered[position]
        holder.tvReference.setText(catOne.chap_id.toString())
        holder.tvChapname.setText(catOne.chapname)
        holder.tvChapname.setTextSize(18f)
        holder.tvReference.setTextSize(18f)
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return duasfiltered.size.toLong()
    }

    fun getItem(position: Int): Any {
        return duasfiltered[position]
    }

    override fun getItemCount(): Int {
        return duasfiltered.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                duasfiltered = if (charString.isEmpty()) {
                    mList
                } else {
                    val filteredList: MutableList<hduanames> = ArrayList<hduanames>()
                    for (details in mList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (details.chapname!!.lowercase(Locale.getDefault())
                                .contains(charString.lowercase(Locale.getDefault()))
                        ) {
                            filteredList.add(details)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = duasfiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                duasfiltered = filterResults.values as ArrayList<hduanames>
                notifyDataSetChanged()
            }
        }
    }

    fun setmutable(userlist: List<hduanames>) {
         mList=userlist
        duasfiltered=userlist
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val tvReference: TextView
        val tvChapname: TextView
        val cardview: CardView

        init {
            tvReference = view.findViewById<TextView>(R.id.txtReference)
            tvChapname = view.findViewById<TextView>(R.id.txtDuaName)
            cardview = view.findViewById<CardView>(R.id.cardview)
            view.setOnClickListener(this)
            tvReference.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, getLayoutPosition())
            }
        }
    }
}