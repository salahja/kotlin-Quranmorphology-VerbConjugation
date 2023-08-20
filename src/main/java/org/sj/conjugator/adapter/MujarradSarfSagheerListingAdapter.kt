package org.sj.conjugator.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.google.android.material.chip.Chip
import org.sj.conjugator.interfaces.OnItemClickListener

class MujarradSarfSagheerListingAdapter :
    RecyclerView.Adapter<MujarradSarfSagheerListingAdapter.ViewHolder> {
    private val context: Context
    var rootcolor = 0
    var weaknesscolor = 0
    var wazancolor = 0
    var bookmarkpostion = 0
    var mItemClickListener: OnItemClickListener? = null

    //    private final Integer arabicTextColor;
    var mycontext: Context? = null
    var ismzaftitle = "(الْظَرْف:)"
    var ismalatitle = "( الآلَة:)"
    var alaheader = "اِسْم الآلَة"
    var zarfheader = "اِسْم الْظَرفْ"
    private var madhi = ArrayList<String>()
    private var mazeedregular = false
    private val bookChapterno = 0
    private val bookVerseno = 0
    private val ayahNumber: Int? = null
    private val urdu_font_selection: String? = null
    private val quran_arabic_font = 0
    private val urdu_font_size = 0
    private val arabic_font_selection: String? = null
    private var sarfSagheer = ArrayList<ArrayList<*>>()

    constructor(lists: ArrayList<ArrayList<*>>, context: Context) {
        this.context = context
        sarfSagheer = lists
    }

    constructor(mazeedregular: Boolean, sarfSagheer: ArrayList<*>, activity: FragmentActivity) {
        context = activity
        this.sarfSagheer = sarfSagheer as ArrayList<ArrayList<*>>
        this.mazeedregular = mazeedregular
    }

    constructor(
        madhi: ArrayList<String>,
        skabeer: ArrayList<ArrayList<*>>,
        activity: FragmentActivity
    ) {
        context = activity
        sarfSagheer = skabeer
        this.madhi = madhi
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //      View view = LayoutInflater.from(parent.context!!).inflate(R.layout.sarfkabeercolumn, parent, false);


        //   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mufradat_ayah_list_row, parent, false);
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mazeedsarfsagheer, parent, false);
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sarfsagheer, parent, false)


        //    View view = LayoutInflater.from(parent.context!!).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  final List sarf = sarfSagheer.get(position);
//        final String[] array = (String[]) sarfSagheer.get(position).toArray();
        val shared = PreferenceManager.getDefaultSharedPreferences(context)
        val preferences = shared.getString("theme", "dark")
        val cweakness = 0
        val crootword = 0
        val cbabcolor: Int
        if (preferences == "dark") {
            rootcolor = Color.CYAN
            weaknesscolor = Color.YELLOW
            wazancolor = Color.BLUE
        } else {
            rootcolor = Color.RED
            weaknesscolor = Color.BLACK
            wazancolor = Color.RED
        }
        val zarf = StringBuilder()
        val ismala = StringBuilder()
        val amr = StringBuilder()
        val nahiamr = StringBuilder()
        val toArray = sarfSagheer[position].toTypedArray()
        val sharedPreferences =
            android.preference.PreferenceManager.getDefaultSharedPreferences(context)
        val arabic_font_selection =
            sharedPreferences.getString("Arabic_Font_Selection", "me_quran.ttf")
        val mequran = Typeface.createFromAsset(
            context.assets,
            arabic_font_selection
        )
        val length = toArray.size
        val prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(context)
        val arabicFontsize = prefs.getInt("arabicFontSizeEntryArray", 20)
        //   Integer arabicFontsize = SharedPref.arabicFontsize();
        //   arabicFontsize=45
        holder.ismalaheader.textSize = arabicFontsize.toFloat()
        holder.ismzarfheader.textSize = arabicFontsize.toFloat()
        holder.mamaroof.textSize = arabicFontsize.toFloat()
        holder.mumaroof.textSize = arabicFontsize.toFloat()
        holder.masdaro.textSize = arabicFontsize.toFloat()
        holder.masdart.textSize = arabicFontsize.toFloat()
        holder.ismfail.textSize = arabicFontsize.toFloat()
        holder.mamajhool.textSize = arabicFontsize.toFloat()
        holder.mumajhool.textSize = arabicFontsize.toFloat()
        holder.ismmafool.textSize = arabicFontsize.toFloat()
        holder.amr.textSize = arabicFontsize.toFloat()
        holder.nahiamr.textSize = arabicFontsize.toFloat()
        holder.babname.textSize = arabicFontsize.toFloat()
        holder.rootword.textSize = arabicFontsize.toFloat()
        holder.ismzarf.textSize = arabicFontsize.toFloat()
        holder.ismala.textSize = arabicFontsize.toFloat()
        holder.weaknessname.textSize = arabicFontsize.toFloat()
        holder.wazan.textSize = arabicFontsize.toFloat()
        holder.mamaroof.setTypeface(mequran)
        holder.mumaroof.setTypeface(mequran)
        //   holder.masdaro.setTypeface(mequran);
        // holder.masdart.setTypeface(mequran);
        holder.ismfail.setTypeface(mequran)
        holder.mamajhool.setTypeface(mequran)
        holder.mumajhool.setTypeface(mequran)
        holder.ismmafool.setTypeface(mequran)
        holder.amr.setTypeface(mequran)
        holder.nahiamr.setTypeface(mequran)
        holder.babname.setTypeface(mequran)
        //  holder.babname.setTextColor(Color.YELLOW);
        holder.rootword.setTypeface(mequran)
        //  holder.rootword.setTextColor(Color.BLUE);
        holder.ismzarf.setTypeface(mequran)
        holder.ismala.setTypeface(mequran)
        holder.weaknessname.setTypeface(mequran)
        //  holder.weaknessname.setTextColor(Color.GREEN);
        holder.babname.setTextColor(wazancolor)
        holder.rootword.setTextColor(rootcolor)
        holder.weaknessname.setTextColor(weaknesscolor)
        if (length == 13) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            holder.amr.text = toArray[6] as CharSequence
            holder.nahiamr.text = toArray[7] as CharSequence
            holder.ismzarfheader.text = zarfheader
            holder.ismalaheader.text = alaheader
            zarf.append(toArray[8] as CharSequence)
            holder.ismzarf.text = zarf
            holder.ismala.text = ismalatitle
            holder.weaknessname.text = toArray[11] as CharSequence
            holder.rootword.text = toArray[10] as CharSequence
            holder.babname.text = toArray[9] as CharSequence
        }
        if (length == 14) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            holder.amr.text = toArray[6] as CharSequence
            holder.nahiamr.text = toArray[7] as CharSequence
            holder.ismzarfheader.text = zarfheader
            holder.ismalaheader.text = alaheader
            zarf.append(toArray[8] as CharSequence)
            holder.ismzarf.text = zarf
            holder.ismalaheader.text = alaheader
            ismala.append(toArray[9] as CharSequence)
            holder.ismala.text = ismala
            holder.weaknessname.text = toArray[10] as CharSequence
            holder.rootword.text = toArray[11] as CharSequence
            holder.babname.text = toArray[12] as CharSequence
            holder.verify.text = toArray[13] as CharSequence
        }
        if (length == 15) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            holder.amr.text = toArray[6] as CharSequence
            holder.nahiamr.text = toArray[7] as CharSequence
            holder.ismzarfheader.text = zarfheader
            zarf.append(toArray[8] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[9] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[10] as CharSequence)
            holder.ismzarf.text = zarf
            holder.ismalaheader.text = alaheader
            ismala.append(toArray[11] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[12] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[13] as CharSequence)
            holder.ismala.text = ismala
            holder.rootword.text = toArray[14] as CharSequence
        }
        if (length == 17) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            holder.amr.text = toArray[6] as CharSequence
            holder.nahiamr.text = toArray[7] as CharSequence
            holder.ismzarfheader.text = zarfheader
            holder.ismalaheader.text = alaheader
            zarf.append(toArray[8] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[9] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[10] as CharSequence)
            holder.ismzarf.text = zarf
            ismala.append(toArray[11] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[12] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[13] as CharSequence)
            holder.ismala.text = ismala
            holder.weaknessname.text = toArray[14] as CharSequence
            holder.rootword.text = toArray[15] as CharSequence
            holder.wazan.text = toArray[16] as CharSequence
            //     holder.verify.setText((CharSequence) toArray[16]);
        }
        if (length == 18) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            holder.amr.text = toArray[6] as CharSequence
            holder.nahiamr.text = toArray[7] as CharSequence
            holder.ismzarfheader.text = zarfheader
            holder.ismalaheader.text = alaheader
            zarf.append(toArray[8] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[9] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[10] as CharSequence)
            holder.ismzarf.text = zarf
            ismala.append(toArray[11] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[12] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[13] as CharSequence)
            holder.ismala.text = ismala
            holder.weaknessname.text = toArray[14] as CharSequence
            holder.rootword.text = toArray[15] as CharSequence
            holder.wazan.text = toArray[16] as CharSequence
            holder.babno.text = toArray[16] as CharSequence
            holder.verify.text = toArray[17] as CharSequence
        }
        //thulathi regular
        if (length == 22) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            amr.append(toArray[6] as CharSequence)
            amr.append(",")
            amr.append(toArray[7] as CharSequence)
            amr.append(",")
            amr.append(toArray[8] as CharSequence)
            holder.amr.text = amr
            nahiamr.append(toArray[9] as CharSequence)
            nahiamr.append(",")
            nahiamr.append(toArray[10] as CharSequence)
            nahiamr.append(",")
            nahiamr.append(toArray[11] as CharSequence)
            holder.nahiamr.text = nahiamr
            holder.ismzarfheader.text = zarfheader
            holder.ismalaheader.text = alaheader
            zarf.append(toArray[12] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[13] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[14] as CharSequence)
            holder.ismzarf.text = zarf
            ismala.append(toArray[15] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[16] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[17] as CharSequence)
            holder.weaknessname.text = toArray[18] as CharSequence
            holder.rootword.text = toArray[19] as CharSequence
            holder.ismala.text = ismala
            holder.babname.text = toArray[20] as CharSequence
            holder.verify.text = toArray[21] as CharSequence
            //  holder.rootword.setText((CharSequence) toArray[15]);
        }
        //mahmooz mithal and lafeef originall 18
        if (length == 19) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            holder.amr.text = toArray[6] as CharSequence
            holder.nahiamr.text = toArray[7] as CharSequence
            holder.ismzarfheader.text = zarfheader
            holder.ismalaheader.text = alaheader
            zarf.append(toArray[8] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[9] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[10] as CharSequence)
            holder.ismzarf.text = zarf
            ismala.append(toArray[11] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[12] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[13] as CharSequence)
            holder.ismala.text = ismala
            //  holder.masdaro.setText((CharSequence) toArray[14]);
            //  holder.masdart.setText((CharSequence) toArray[14]);
            holder.rootword.text = toArray[15] as CharSequence
            holder.babname.text = toArray[16] as CharSequence
            holder.weaknessname.text = toArray[14] as CharSequence
            holder.verify.text = toArray[18] as CharSequence
        }
        if (length == 1) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            holder.amr.text = toArray[6] as CharSequence
            holder.nahiamr.text = toArray[7] as CharSequence
            holder.ismzarfheader.text = zarfheader
            holder.ismalaheader.text = alaheader
            zarf.append(toArray[8] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[9] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[10] as CharSequence)
            holder.ismzarf.text = zarf
            ismala.append(toArray[11] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[12] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[13] as CharSequence)
            holder.ismala.text = ismala
            holder.weaknessname.text = toArray[14] as CharSequence
            holder.rootword.text = toArray[15] as CharSequence
            holder.rootword.text = toArray[15] as CharSequence
            holder.verify.text = toArray[16] as CharSequence
        }
        //mudhaf
        if (length == 23) {
            holder.mamaroof.text = toArray[0] as CharSequence
            holder.mumaroof.text = toArray[1] as CharSequence
            holder.ismfail.text = toArray[2] as CharSequence
            holder.mamajhool.text = toArray[3] as CharSequence
            holder.mumajhool.text = toArray[4] as CharSequence
            holder.ismmafool.text = toArray[5] as CharSequence
            amr.append(toArray[6] as CharSequence)
            amr.append(",")
            amr.append(toArray[7] as CharSequence)
            amr.append(",")
            amr.append(toArray[8] as CharSequence)
            holder.amr.text = amr
            nahiamr.append(toArray[9] as CharSequence)
            nahiamr.append(",")
            nahiamr.append(toArray[10] as CharSequence)
            nahiamr.append(",")
            nahiamr.append(toArray[11] as CharSequence)
            holder.nahiamr.text = nahiamr
            holder.ismzarfheader.text = zarfheader
            holder.ismalaheader.text = alaheader
            zarf.append(toArray[12] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[13] as CharSequence)
            zarf.append(",")
            zarf.append(toArray[14] as CharSequence)
            holder.ismzarf.text = zarf
            ismala.append(toArray[15] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[16] as CharSequence)
            ismala.append(",")
            ismala.append(toArray[17] as CharSequence)
            holder.ismala.text = ismala
            holder.masdaro.text = toArray[18] as CharSequence
            holder.masdart.text = toArray[18] as CharSequence
            holder.rootword.text = toArray[19] as CharSequence
            holder.babname.text = toArray[20] as CharSequence
            holder.weaknessname.text = toArray[21] as CharSequence
            holder.wazan.text = toArray[22] as CharSequence
        }
        //     holder.masdaro.setText((CharSequence) toArray[12]);
        //     holder.masdart.setText((CharSequence) toArray[12]);
        //     TextView textView = (TextView) findViewById(R.id.textView);
        //     Spannable spanDark = new SpannableString(textView.getText());
        //     span.setSpan(new RelativeSizeSpan(0.8f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //     textView.setText(span);
        //     ismfail,masdaro,mumaroof,mamaroof,ismmafool,masdart,mumajhool,mamajhool,ismzarf,ismala;
        //  holder.ismfail.setText(o.);
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return sarfSagheer.size.toLong()
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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val amr: Chip
        val nahiamr: Chip
        val ismfail: Chip
        val mumaroof: Chip
        val mamaroof: Chip
        val ismmafool: Chip
        val mumajhool: Chip
        val mamajhool: Chip
        val ismzarf: Chip
        val ismala: Chip
        val verify: Chip
        val babno: TextView
        val ismalaheader: TextView
        val ismzarfheader: TextView
        val masdart: TextView
        val masdaro: TextView
        val babname: TextView
        val rootword: TextView
        val weaknessname: TextView
        val wazan: TextView

        init {
            //    itemView.setTag(this);
            //     itemView.setOnClickListener(onItemClickListener);
            babno = view.findViewById(R.id.babno)
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
            ismala = view.findViewById(R.id.ismaalatable)
            ismzarf = view.findViewById(R.id.zarftable)
            babname = view.findViewById(R.id.babno)
            rootword = view.findViewById(R.id.rootword)
            weaknessname = view.findViewById(R.id.weknessname)
            ismzarfheader = view.findViewById(R.id.ismzarfheader)
            ismalaheader = view.findViewById(R.id.ismalaheader)
            wazan = view.findViewById(R.id.wazan)
            verify = view.findViewById(R.id.conjugateall)
            mumajhool.tooltipText = "Click for Verb Conjugation"
            view.setOnClickListener(this)
            ismfail.setOnClickListener(this) //R.id.ismfail);
            mumaroof.setOnClickListener(this) //R.id.mumaroof);
            mamaroof.setOnClickListener(this) //R.id.mamaroof);
            ismmafool.setOnClickListener(this) //R.id.ismmafool);
            mumajhool.setOnClickListener(this) //R.id.mumajhool);
            mamajhool.setOnClickListener(this) //R.id.mamajhool);
            amr.setOnClickListener(this) //R.id.amr);
            nahiamr.setOnClickListener(this) //R.id.nahiamr);
            ismala.setOnClickListener(this) //R.id.ismaalatable);
            ismzarf.setOnClickListener(this) //R.id.zarftable);
            rootword.setOnClickListener(this) //R.id.weaknesstype);
            verify.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}