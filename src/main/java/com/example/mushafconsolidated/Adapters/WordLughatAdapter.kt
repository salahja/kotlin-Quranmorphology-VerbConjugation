package com.example.mushafconsolidated.Adaptersimport

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener



class WordLughatAdapter(
    private var worddictorary: ArrayList<lughat>?,
    private var context: Context?,
    var language: String
) : RecyclerView.Adapter<WordLughatAdapter.ItemViewAdapter>() {
    var mItemClickListener: OnItemClickListener? = null
    var isSarfSagheerMazeed: Boolean = false

    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();
    private val sarfsagheer: ArrayList<ArrayList<*>>? = null
    public override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordLughatAdapter.ItemViewAdapter {
        val view: View
        view = LayoutInflater.from(parent.context!!).inflate(R.layout.lughat_layout, parent, false)
        return ItemViewAdapter(view)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onBindViewHolder(holder: WordLughatAdapter.ItemViewAdapter, position: Int) {
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context!!
        )
        val quranFont: String? = sharedPreferences.getString("quranFont", "kitab.ttf")
        val mequran: Typeface = Typeface.createFromAsset(context!!.getAssets(), quranFont)
        //    holder.wordDictionary.setText(worddetails.get("word"));
        if (!worddictorary!!.isEmpty()) {
            var replace1: String? = ""
            var replace2: String? = ""
            var replace3: String? = ""
            var replace4: String? = ""
            if ((language == "english")) {
                val en_lughat: String = worddictorary!!.get(0).en_lughat
                val meanings: String = worddictorary!!.get(0).meaning
                val rootword: String = worddictorary!!.get(0).rootword
                val arabicword: String = worddictorary!!.get(0).arabicword
                replace1 = en_lughat.replace("\\n", "<br><p>")
                replace2 = rootword.replace("\\n", "<br><p>")
                replace3 = arabicword.replace("\\n", "<br><p>")
                replace4 = meanings.replace("\\n", "<br><p>")
                holder.wordDictionary.setText(Html.fromHtml(replace1))
                holder.rootwowrd.setText(Html.fromHtml(replace2))
                holder.arabicword.setText(Html.fromHtml(replace3))
                holder.meaning.setText(Html.fromHtml(replace4))
            } else if ((language == "urdu")) {
                val ur_lughat: String = worddictorary!!.get(0).ur_lughat
                val urdu: String =
                    ur_lughat.replace("\\n", "<br><p>").replace("\\n".toRegex(), "<br><p>")
                val meanings: String = worddictorary!!.get(0).meaning
                val rootword: String = worddictorary!!.get(0).rootword
                val arabicword: String = worddictorary!!.get(0).arabicword
                replace2 = rootword.replace("\\n", "<br><p>")
                replace3 = arabicword.replace("\\n", "<br><p>")
                replace4 = meanings.replace("\\n", "<br><p>")
                holder.wordDictionary.setText(Html.fromHtml(urdu))
                holder.rootwowrd.setText(Html.fromHtml(replace2))
                holder.arabicword.setText(Html.fromHtml(replace3))
                holder.meaning.setText(Html.fromHtml(replace4))
            }
            //.replace("\\n", "<br>");
        }
    }

    public override fun getItemId(position: Int): Long {
        return worddictorary!!.get(position).surah.toLong()
    }

    public override fun getItemCount(): Int {
        return worddictorary!!.size
    }

    fun getItem(position: Int): Any {
        return worddictorary!!.get(position)
    }

    fun setRootWordsAndMeanings(
        dictionary: ArrayList<lughat>,
        context: Context
    ) {
        worddictorary = dictionary
        this.context = context
    }

    inner class ItemViewAdapter constructor(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val wordDictionary: TextView
        val wordDictionaryUrdu: TextView
        val meaning: TextView
        val rootwowrd: TextView
        val arabicword: TextView
        val referenceView: TextView
        var dismissview: ImageView? = null
        var i: Int = ContextCompat.getColor(context!!, R.color.kashmirigreen)

        init {
            rootwowrd = view.findViewById(R.id.rootward)
            arabicword = view.findViewById(R.id.arabicword)
            wordDictionary = view.findViewById(R.id.wordDictionary)
            wordDictionaryUrdu = view.findViewById(R.id.wordDictionaryUrdu)
            meaning = view.findViewById(R.id.meaning)
            referenceView = view.findViewById(R.id.referenceView)
        }

        public override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, getLayoutPosition())
            }
        }
    }
}