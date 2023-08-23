package com.example.mushafconsolidated.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.qurandictionary
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener

import java.util.Locale


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.MyViewHolder>, Filterable {
    var mItemClickListener: OnItemClickListener? = null
    private var qurandictionaryArrayList: ArrayList<qurandictionary>? = null
    private val olistener: View.OnClickListener? = null
    private val context: Context
    private var qurandictionaryFiltered: List<qurandictionary>? = null
    private var listener: ContactsAdapterListener? = null
    private val downloadtype = false

    constructor(context: Context, listener: ContactsAdapterListener?) {
        this.context = context
        this.listener = listener
    }

    constructor(
        context: Context,
        qurandictionaryArrayList: ArrayList<qurandictionary>?,
        b: Boolean
    ) {
        this.context = context
        this.qurandictionaryArrayList = qurandictionaryArrayList
        qurandictionaryFiltered = qurandictionaryArrayList
    }

    fun getItem(position: Int): Any {
        return qurandictionaryFiltered!![position]
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_row_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entity = qurandictionaryFiltered!![position]
        holder.arabicroot.text = entity.rootarabic
        holder.buckwaterroot.text = entity.rootbuckwater
        //  holder.englishname.setText(entity.getEnglish_name());
        //  holder.translationid.setText(entity.getTranslation_id());
    }

    override fun getItemCount(): Int {
        return qurandictionaryFiltered!!.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                qurandictionaryFiltered = if (charString.isEmpty()) {
                    qurandictionaryArrayList
                } else {
                    val filteredList: MutableList<qurandictionary> = ArrayList()
                    for (details in qurandictionaryArrayList!!) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (details.rootarabic.lowercase(Locale.getDefault()).contains(
                                charString.lowercase(
                                    Locale.getDefault()
                                )
                            ) ||
                            details.rootbuckwater!!.lowercase(Locale.getDefault()).contains(
                                charString.lowercase(
                                    Locale.getDefault()
                                )
                            )
                        ) {
                            filteredList.add(details)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = qurandictionaryFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                qurandictionaryFiltered = filterResults.values as ArrayList<qurandictionary>
                notifyDataSetChanged()
            }
        }
    }

    interface ContactsAdapterListener {
        fun onselected(translationEntity: qurandictionary?)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var arabicroot: TextView
        var buckwaterroot: TextView
        var translationid: TextView? = null
        var englishname: TextView? = null
        var downloadicon: ImageView? = null
        var deleteicon: ImageView? = null

        init {
            arabicroot = view.findViewById(R.id.arabicroot)
            buckwaterroot = view.findViewById(R.id.buckwaterroot)
            view.setOnClickListener(this)


            /*
               view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onselected(translationEntitiesFiltered.get(getAdapterPosition()));
                }
            });
             */
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}