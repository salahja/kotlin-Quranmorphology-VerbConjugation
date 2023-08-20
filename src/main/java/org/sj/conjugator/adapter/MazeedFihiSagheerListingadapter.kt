package org.sj.conjugator.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import com.google.android.material.chip.Chip
import org.sj.conjugator.adapter.MazeedFihiSagheerListingadapter.MufradatViewHolder
import org.sj.conjugator.interfaces.OnItemClickListener
import org.sj.conjugator.utilities.SharedPref

class MazeedFihiSagheerListingadapter : RecyclerView.Adapter<MufradatViewHolder> {
    private val context: Context
    var bookmarkpostion = 0
    var mItemClickListener: OnItemClickListener? = null

    //    private final Integer arabicTextColor;
    var mycontext: Context? = null
    private var mazeedregular = false
    private val bookChapterno = 0
    private val bookVerseno = 0
    private val ayahNumber: Int? = null
    private val urdu_font_selection: String? = null
    private val arabic_font_size = 0
    private val urdu_font_size = 0
    private val arabic_font_selection: String? = null
    private var sarfSagheer = ArrayList<ArrayList<*>>()
    private val ScreensizeName: String? = null

    constructor(lists: ArrayList<ArrayList<*>>, context: Context) {
        this.context = context
        sarfSagheer = lists
    }

    constructor(mazeedregular: Boolean, sarfSagheer: ArrayList<*>, activity: FragmentActivity) {
        context = activity
        this.sarfSagheer = sarfSagheer as ArrayList<ArrayList<*>>
        this.mazeedregular = mazeedregular
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MufradatViewHolder {
        //   View view = LayoutInflater.from(parent.context!!).inflate(R.layout.mufradat_ayah_list_row, parent, false);
        //    View view = LayoutInflater.from(parent.context!!).inflate(R.layout.mazeedsarfsagheer, parent, false);
        //    View view = LayoutInflater.from(parent.context!!).inflate(R.layout.thulathisarfsagheer, parent, false);


        //   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mufradat_ayah_list_row, parent, false);
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mazeedsarfsagheer, parent, false);
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sarfsagheer, parent, false)
        //    View view = LayoutInflater.from(parent.context!!).inflate(R.layout.thulathisarfsagheer, parent, false);
        return MufradatViewHolder(view)
    }

    override fun onBindViewHolder(holder: MufradatViewHolder, position: Int) {
        //  final List sarf = sarfSagheer.get(position);
//        final String[] array = (String[]) sarfSagheer.get(position).toArray();
        //    final Object[] toArray = sarfSagheer.get(position).toArray();
        val toArray = sarfSagheer[position]
        val length = toArray.size
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val arabic_font_selection =
            sharedPreferences.getString("Arabic_Font_Selection", "me_quran.ttf")
        val mequran = Typeface.createFromAsset(
            context.assets,
            arabic_font_selection
        )
        val fontsize = SharedPref.arabicFontsize()

        //   final Typeface mequran = Typeface.createFromAsset(context.getAssets(), "me_quran.ttf");
        // final Typeface mequran = Typeface.createFromAsset(context.getAssets(), "NooreHuda.ttf");
        //    final Typeface mequran = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());
        //  final Typeface mequran = Typeface.createFromAsset(context.getAssets(), "Monteserrat.ttf");
        holder.conjugate.text = "Conjugate All"
        holder.conjugate.textSize = fontsize.toFloat()
        holder.conjugate.typeface = mequran
        holder.ismalaheader.visibility = View.GONE
        holder.ismala.visibility = View.GONE
        //  SharedPreferences sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!);
        val width = sharedPreferences.getString("width", "compactWidth")
        val arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
        if (length == 11) {
            val ind = 0
            var s: String
            holder.rootword.text = toArray[2] as CharSequence
            holder.weaknessname.text = toArray[0] as CharSequence
            holder.babno.text = toArray[1] as CharSequence
            holder.mamaroof.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumaroof.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.amr.text = toArray[6] as CharSequence
            holder.nahiamr.text = toArray[7] as CharSequence
            holder.ismfail.text = toArray[8] as CharSequence
            holder.ismmafool.text = toArray[9] as CharSequence
            //  holder.ismala.setText((CharSequence) toArray.get(10));
            //  holder.ismzarf.setText((CharSequence) toArray.get(11));
//        final CharSequence  cs9 = (CharSequence) toArray.get(9);
            //    final CharSequence  cs10 = (CharSequence) toArray.get(10);
            //    FontSizeSelection(holder);
            //  final String fontSelection = SharedPref.arabicFontSelection();
            holder.mamaroof.textSize = fontsize.toFloat()
            holder.mumaroof.textSize = fontsize.toFloat()
            holder.masdaro.textSize = fontsize.toFloat()
            holder.masdart.textSize = fontsize.toFloat()
            holder.ismfail.textSize = fontsize.toFloat()
            holder.mamajhool.textSize = fontsize.toFloat()
            holder.mumajhool.textSize = fontsize.toFloat()
            holder.ismmafool.textSize = fontsize.toFloat()
            holder.amr.textSize = fontsize.toFloat()
            holder.nahiamr.textSize = fontsize.toFloat()
            holder.babno.textSize = fontsize.toFloat()
            holder.babno.setTextColor(Color.YELLOW)
            holder.rootword.textSize = fontsize.toFloat()
            holder.rootword.setTextColor(Color.BLUE)
            holder.weaknessname.textSize = fontsize.toFloat()
            holder.weaknessname.setTextColor(Color.GREEN)
            holder.mamaroof.typeface = mequran
            holder.mumaroof.typeface = mequran
            holder.masdaro.typeface = mequran
            holder.masdart.typeface = mequran
            holder.ismfail.typeface = mequran
            holder.mamajhool.typeface = mequran
            holder.mumajhool.typeface = mequran
            holder.ismmafool.typeface = mequran
            holder.amr.typeface = mequran
            holder.nahiamr.typeface = mequran
            holder.babno.typeface = mequran
            holder.babno.setTextColor(Color.YELLOW)
            holder.rootword.typeface = mequran
            holder.rootword.setTextColor(Color.BLUE)
            holder.weaknessname.typeface = mequran
            holder.weaknessname.setTextColor(Color.GREEN)
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.masdaro.text = toArray[8] as CharSequence
            holder.masdart.text = toArray[8] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            holder.ismzarf.text = toArray[5] as CharSequence
            FontSizeSelection(holder)
        }
    }

    private fun FontSizeSelection(holder: MufradatViewHolder) {
        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )

        val width = sharedPreferences.getString("width", "compactWidth")
        val fontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
        if (width == "mediumWidth" || width == "expandedWidth") {
            holder.mamaroof.textSize = fontsize.toFloat()
            holder.mumaroof.textSize = fontsize.toFloat()
            holder.masdaro.textSize = fontsize.toFloat()
            holder.masdart.textSize = fontsize.toFloat()
            holder.ismfail.textSize = fontsize.toFloat()
            holder.mamajhool.textSize = fontsize.toFloat()
            holder.mumajhool.textSize = fontsize.toFloat()
            holder.ismmafool.textSize = fontsize.toFloat()
            holder.amr.textSize = fontsize.toFloat()
            holder.nahiamr.textSize = fontsize.toFloat()
            holder.babno.textSize = fontsize.toFloat()
            holder.babno.setTextColor(Color.YELLOW)
            holder.rootword.textSize = fontsize.toFloat()
            holder.rootword.setTextColor(Color.BLUE)
            holder.weaknessname.textSize = fontsize.toFloat()
            holder.weaknessname.setTextColor(Color.GREEN)
        }
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return sarfSagheer[position].size.toLong()
    }

    fun getItem(position: Int): Any {
        return sarfSagheer[position]
    }

    override fun getItemCount(): Int {
        return sarfSagheer.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    fun setVerbArrayList(sarfsagheer: ArrayList<ArrayList<*>>) {
        sarfSagheer = sarfsagheer
    }

    inner class MufradatViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        //  public final TextView ayah_number;
        val amr: Chip
        val nahiamr: Chip
        val ismfail: Chip
        val masdaro: Chip
        val mumaroof: Chip
        val mamaroof: Chip
        val ismalaheader: Chip
        val ismala: Chip
        val ismmafool: Chip
        val masdart: Chip
        val mumajhool: Chip
        val mamajhool: Chip
        val ismzarf: Chip
        val babno: Chip
        val conjugate: Chip
        val rootword: Chip
        val weaknessname: Chip

        init {
            ismalaheader = view.findViewById(R.id.ismalaheader)
            ismala = view.findViewById(R.id.ismaalatable)
            ismfail = view.findViewById(R.id.ismfail)
            masdaro = view.findViewById(R.id.masdar)
            mumaroof = view.findViewById(R.id.mumaroof)
            mamaroof = view.findViewById(R.id.mamaroof)
            ismmafool = view.findViewById(R.id.ismmafool)
            masdart = view.findViewById(R.id.masdar2)
            mumajhool = view.findViewById(R.id.mumajhool)
            mamajhool = view.findViewById(R.id.mamajhool)
            amr = view.findViewById(R.id.amr)
            nahiamr = view.findViewById(R.id.nahiamr)
            conjugate = view.findViewById(R.id.conjugateall)
            ismzarf = view.findViewById(R.id.zarftable)
            babno = view.findViewById(R.id.babno)
            rootword = view.findViewById(R.id.rootword)
            weaknessname = view.findViewById(R.id.weknessname)
            mumajhool.tooltipText = "Click for Verb Conjugation"
            view.setOnClickListener(this)
            //    ismfail.setOnClickListener(this);//R.id.ismfail);
//
            //   mumaroof.setOnClickListener(this);//R.id.mumaroof);
            //  mamaroof.setOnClickListener(this);//R.id.mamaroof);
            //  ismmafool.setOnClickListener(this);//R.id.ismmafool);
            //  mumajhool.setOnClickListener(this);//R.id.mumajhool);
            // // mamajhool.setOnClickListener(this);//R.id.mamajhool);
            //   amr.setOnClickListener(this);//R.id.amr);
            //  nahiamr.setOnClickListener(this);//R.id.nahiamr);
            // ismzarf.setOnClickListener(this);//R.id.zarftable);
            conjugate.setOnClickListener(this) //R.id.zarftable);
            //view.setOnClickListener(this); // current clickListerner
            view.setOnClickListener(this) // current clickListerner
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}