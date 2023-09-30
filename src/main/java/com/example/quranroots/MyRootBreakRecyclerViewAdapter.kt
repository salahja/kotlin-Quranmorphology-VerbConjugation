package com.example.quranroots

import android.text.SpannableString
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.RootWordDetails
import com.example.mushafconsolidated.R
import com.example.utility.CorpusUtilityorig.Companion.NewSetWordSpan
import com.google.android.material.chip.Chip
import org.sj.conjugator.interfaces.OnItemClickListener

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyRootBreakRecyclerViewAdapter(private val mValues: ArrayList<RootWordDetails>) :
    RecyclerView.Adapter<MyRootBreakRecyclerViewAdapter.ViewHolder>() {
    private lateinit var mItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rec_arabicroot_detail, parent, false)
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lughat = mValues[position]
        val sb = StringBuilder()
        val spannableString = NewSetWordSpan(
            lughat.tagone, lughat.tagtwo, lughat.tagthree, lughat.tagfour, lughat.tagfive,
            lughat.araone!!, lughat.aratwo!!, lughat.arathree!!, lughat.arafour!!, lughat.arafive!!
        )
        //  sb.append(lughat.getSurah()).append("   ").append(lughat.getNamearabic()).append(lughat.getAyah()).append(" ").append(lughat.getArabic());
        sb.append(lughat.ayah).append("  ").append(lughat.namearabic).append("   ")
            .append(lughat.surah).append(" ").append(lughat.en)
        val sbs = SpannableString(sb)
        val charSequence = TextUtils.concat(spannableString, sb)
        // charSequence=TextUtils.concat(sb);
        //   sb.append(lughat.getSurah()).append(":").append(lughat.getAyah()).append(":").append(lughat.getArabic()).append("-").append(lughat.getAbjadname());
        holder.arabicroot_detail.text = charSequence

        //     holder.mIdView.setText(mValues.get(position).id);
        //    holder.mContentView.setText(mValues.get(position).content);
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        if (mItemClickListener != null) {
            this.mItemClickListener = mItemClickListener
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        // public final ImageView id;
        val arabicroot_detail: Chip
        var cardview: CardView? = null

        init {
            view.tag = this
            itemView.setOnClickListener(this)
            //  id = view.findViewById(R.id.imgview);
            arabicroot_detail = view.findViewById(R.id.recarabicroot_detail)
            arabicroot_detail.tag = "root"
            arabicroot_detail.setOnClickListener(this)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, layoutPosition)
            }
        }
    }
}