package com.example.quranroots

import android.preference.PreferenceManager
import android.text.SpannableString
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.RootWordDetails
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.fragments.refWordMorphologyDetails
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.mushafconsolidatedimport.Config
import com.example.utility.CorpusUtilityorig.Companion.NewSetWordSpan
import com.example.utility.QuranGrammarApplication
import com.example.utility.QuranGrammarApplication.Companion.context
import com.google.android.material.chip.Chip
import com.tooltip.Tooltip
import org.sj.conjugator.interfaces.OnItemClickListener

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyRootBreakRecyclerViewAdapter(
    private val rootWordDetails: ArrayList<RootWordDetails>,
    private val   corpusSurahWord: List<QuranCorpusWbw>?
) :
    RecyclerView.Adapter<MyRootBreakRecyclerViewAdapter.ViewHolder>() {
    private lateinit var quranCorpusWbw: QuranCorpusWbw
    private lateinit var wordDetails: RootWordDetails
    private lateinit var mItemClickListener: OnItemClickListener
    private var arabicfontSize: Int = 0
    private var translationfontsize: Int = 0
    private var defaultfont: Boolean = false
    init {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab)

        arabicfontSize = sharedPreferences.getInt("pref_font_arabic_key", 18)
        translationfontsize = sharedPreferences.getInt("pref_font_englsh_key", 18)
        defaultfont = sharedPreferences.getBoolean("default_font", true)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rec_arabicroot_detail, parent, false)
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          wordDetails = rootWordDetails[position]
          quranCorpusWbw = corpusSurahWord!![position]
        val sb = StringBuilder()
        val spannableString = NewSetWordSpan(
            wordDetails.tagone, wordDetails.tagtwo, wordDetails.tagthree, wordDetails.tagfour, wordDetails.tagfive,
            wordDetails.araone!!, wordDetails.aratwo!!, wordDetails.arathree!!, wordDetails.arafour!!, wordDetails.arafive!!
        )
        //  sb.append(lughat.getSurah()).append("   ").append(lughat.getNamearabic()).append(lughat.getAyah()).append(" ").append(lughat.getArabic());
        sb.append(wordDetails.ayah).append("  ").append(wordDetails.namearabic).append("   ")
            .append(wordDetails.surah).append(" ").append(wordDetails.en)
        val sbs = SpannableString(sb)
        val charSequence = TextUtils.concat(spannableString, sb)
        // charSequence=TextUtils.concat(sb);
        //   sb.append(lughat.getSurah()).append(":").append(lughat.getAyah()).append(":").append(lughat.getArabic()).append("-").append(lughat.getAbjadname());
        holder.arabicroot_detail.text = charSequence
        setTextSizes(holder)
/*
        holder.arabicroot_detail.setOnLongClickListener(View.OnLongClickListener {


        })*/
    }


    private fun setTextSizes(holder:ViewHolder) {
        if (!defaultfont) {
            holder.arabicroot_detail.textSize = arabicfontSize.toFloat()
   
        }
    }


    override fun getItemCount(): Int {
        return rootWordDetails.size
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
            arabicroot_detail.setOnLongClickListener(View.OnLongClickListener {view
                val utils = Utils(QuranGrammarApplication.context!!)
                val verbCorpusRootWords =
                    utils.getQuranRoot(wordDetails.surah, wordDetails.ayah, wordDetails.wordno)
                if (verbCorpusRootWords!!.isNotEmpty() && verbCorpusRootWords[0]!!.tag == "V") {
                    //    vbdetail = ams.getVerbDetails();
                    print("check")
                }
                val corpusNounWord =
                    utils.getQuranNouns(wordDetails.surah, wordDetails.ayah, wordDetails.wordno)
                val verbCorpusRootWord =
                    utils.getQuranRoot(wordDetails.surah, wordDetails.ayah, wordDetails.wordno)
                val qm = refWordMorphologyDetails(
                    quranCorpusWbw.corpus,
                    corpusNounWord!!, verbCorpusRootWord!!
                )
                val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    QuranGrammarApplication.context!!
                )
                val isNightmode = sharedPreferences!!.getString("themepref", "dark").toString()
                val workBreakDown = qm.workBreakDown
                var color =
                    ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.background_color_light_brown)
                when (isNightmode) {
                    "dark", "blue", "green" -> color =
                        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.background_color)

                    "brown" -> color = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.neutral0)
                    "light" ->                             //  case "white":
                        color = ContextCompat.getColor(
                            QuranGrammarApplication.context!!,
                            R.color.background_color_light_brown
                        )
                }

                val builder: Tooltip.Builder = Tooltip.Builder(
                    view!!, R.style.ayah_translation
                )
                    .setCancelable(true)
                    .setDismissOnClick(false)
                    .setCornerRadius(20f)
                    .setGravity(Gravity.TOP)
                    .setArrowEnabled(true)
                 .setBackgroundColor(color)
                     .setText(workBreakDown)
                builder.show()

                return@OnLongClickListener true
            })
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, layoutPosition)
            }
        }



    }
}