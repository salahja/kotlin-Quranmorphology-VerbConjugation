package com.example.mushafconsolidated.Adaptersimport


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.QuranGrammarApplication

import java.util.Locale


//public class VerseDisplayAdapter extends RecyclerView.Adapter<VerseDisplayAdapter.ItemViewAdapter> {
//public class SurahPartAdap extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
class NewSurahDisplayAdapter(
    private val context: Context?,
    allAnaChapters: ArrayList<ChaptersAnaEntity>
) :
    RecyclerView.Adapter<NewSurahDisplayAdapter.ItemViewAdapter>(), Filterable {
    var mItemClickListener: OnItemClickListener? = null
    private var listonearray: List<ChaptersAnaEntity>
    private val surahname: String? = null
    private var chapterfilered: List<ChaptersAnaEntity>

    init {
        listonearray = allAnaChapters
        chapterfilered = allAnaChapters
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewAdapter {
        val view: View
        //    view = LayoutInflater.from(parent.context!!).inflate(R.layout.surarowlinear, parent, false);
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.orignalsurarowlinear, parent, false)
        return ItemViewAdapter(view, viewType)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onBindViewHolder(holder: ItemViewAdapter, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")
        val surah = chapterfilered[position]
        val context = QuranGrammarApplication.context!!
        val pref = context.getSharedPreferences("lastread", Context.MODE_PRIVATE)
        val imgs = this.context?.resources?.obtainTypedArray(R.array.sura_imgs)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val theme = sharedPreferences.getString("themepref", "dark")
        val defaultfont = sharedPreferences.getBoolean("default_font", true)
        val surahIsmakki: Int = surah.ismakki
        val cno: Int = surah.chapterid
        //  holder.tvsurahleft.setText(sb);
        holder.tvsurahleft.setText(surah.nameenglish)
        if (!defaultfont) {
            holder.tvsurahleft.textSize = SharedPref.SeekarabicFontsize().toFloat()
        }
        //
        val drawable = imgs?.getDrawable(cno - 1)
        imgs?.recycle()
        holder.ivsurahicon.setImageDrawable(drawable)
        if (surahIsmakki == 1) {
            holder.makkimadaniIcon.setImageResource(R.drawable.ic_makkah_foreground)
        } else {
            holder.makkimadaniIcon.setImageResource(R.drawable.ic_madinah_foreground)
        }
        if (theme == "green") {
            holder.surahcardview.setCardBackgroundColor(context.resources.getColor(R.color.mdgreen_theme_dark_onPrimary))
        }
        if (theme == "dark" || theme == "blue" || theme == "green") {
            holder.makkimadaniIcon.setColorFilter(Color.CYAN)
            holder.ivsurahicon.setColorFilter(Color.CYAN)
        } else {
            holder.makkimadaniIcon.setColorFilter(Color.BLUE)
            holder.ivsurahicon.setColorFilter(Color.BLACK)
        }
        //   holder.tvsurahleft.setTextSize(SharedPref.SeekarabicFontsize());
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return chapterfilered.size.toLong()
    }

    fun getItem(position: Int): Any {
        return chapterfilered[position]
    }

    override fun getItemCount(): Int {
        return chapterfilered.size
    }

    fun setUp(listone: List<ChaptersAnaEntity>, listtwo: List<ChaptersAnaEntity?>?) {
        listonearray = listone
    }

    fun setUp(allAnaChapters: ArrayList<ChaptersAnaEntity>) {
        listonearray = allAnaChapters
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                var bool = false
                val charArray = charString.toCharArray()
                if (!charString.isEmpty()) {
                    bool = Character.isDigit(charArray[0])
                }
                if (charString.isEmpty()) {
                    chapterfilered = listonearray
                } else {
                    if (!bool) {
                        val filteredList: MutableList<ChaptersAnaEntity> = ArrayList()
                        for (details in listonearray) {
                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (details.nameenglish.toLowerCase().contains(
                                    charString.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            ) {
                                filteredList.add(details)
                            }
                        }
                        chapterfilered = filteredList
                    } else {
                        val filteredList: MutableList<ChaptersAnaEntity> = ArrayList()
                        for (details in listonearray) {
                            val str = charString.toInt()
                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (details.chapterid === str) {
                                filteredList.add(details)
                            }
                            chapterfilered = filteredList
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = chapterfilered
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                chapterfilered = filterResults.values as ArrayList<ChaptersAnaEntity>
                notifyDataSetChanged()
            }
        }
    }

    inner class ItemViewAdapter internal constructor(layout: View, viewType: Int) :
        RecyclerView.ViewHolder(layout), View.OnClickListener // current clickListerner
    {
        var tvnumber: TextView? = null
        var tvsurahright: TextView? = null
        val tvsurahleft: TextView
        var part: TextView? = null
        var tvSurahlefttarabic: TextView? = null
        var tvSurahrightarabic: TextView? = null
        //val overlayTypeChapterView: TextView
        var overlayTypePartView: TextView? = null
        var surah_name_arabic: TextView? = null
        var referenceview: TextView? = null
        var row_surah: RelativeLayout? = null

        //   public ConstraintLayout surah_row_table;///for rnew_surah_row
        var surah_row_table: LinearLayout? = null
        val makkimadaniIcon: ImageView
        val ivsurahicon: ImageView
        var tvarabicright: ImageView? = null
        val surahcardview: CardView

        init {
            surahcardview = itemView.findViewById(R.id.surahcardview)
            //  tvsurahright = itemView.findViewById(R.id.tvSuraright);
            tvsurahleft = itemView.findViewById(R.id.tvArabic)
            makkimadaniIcon = itemView.findViewById(R.id.makkimadaniicon)
     //     overlayTypeChapterView = itemView.findViewById(R.id.overlayTypeChapterView)
            ivsurahicon = itemView.findViewById(R.id.surahicon)
            layout.setOnClickListener(this) // current clickListerner
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }

    companion object {
        private const val TAG = "SurahPartAdap "
    }
}