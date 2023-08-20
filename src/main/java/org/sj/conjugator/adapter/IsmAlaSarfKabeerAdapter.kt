package org.sj.conjugator.adapter

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import org.sj.conjugator.interfaces.OnItemClickListener
import org.sj.conjugator.utilities.SharedPref

class IsmAlaSarfKabeerAdapter : RecyclerView.Adapter<IsmAlaSarfKabeerAdapter.ViewHolder> {
    private val context: Context
    private var sharedPreferences: SharedPreferences? = null
    var bookmarkpostion = 0
    var mItemClickListener: OnItemClickListener? = null

    //    private final Integer arabicTextColor;
    var mycontext: Context? = null
    private var madhi = ArrayList<String>()
    private var mazeedregular = false
    private val bookChapterno = 0
    private val bookVerseno = 0
    private val ayahNumber: Int? = null
    private val urdu_font_selection: String? = null
    private val arabic_font_size = 0
    private var arabicTypeface: Typeface? = null
    private val urdu_font_size = 0
    private val arabic_font_selection: String? = null
    private var sarfSagheer = ArrayList<ArrayList<*>>()
    private var isTraditional = false
    private var defaultfont = false

    constructor(lists: ArrayList<ArrayList<*>>, context: Context) {
        this.context = context
        sarfSagheer = lists
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
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
        isTraditional = SharedPref.SarfKabeerOthers()
        //      View view = LayoutInflater.from(parent.context!!).inflate(R.layout.sarfkabeercolumn, parent, false);
        val view: View
        view = if (isTraditional) {
            LayoutInflater.from(parent.context).inflate(R.layout.ismalasktraditional, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.ismalacolumkabeer, parent, false)
        }
        //  View view = LayoutInflater.from(parent.context!!).inflate(R.layout.thulathisarfsagheer, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ismalaf = sarfSagheer[0].size
        val ismalas = sarfSagheer[1].size
        //   if (ismalas == 9) {
        //   IsmAlamifalmifalatunNaqis(holder, 1);
        // } else {
        //     IsmAlamifalmifalatun(holder, 0);
        //  }
        IsmAlamifalmifal(holder, 0)
        IsmAlaMifalatun(holder, 1)
        IsmAlaMifaal(holder, 2)
        gcase(holder)
        ismalanumbers(holder)
        FontSIzeSelection(holder)
        SetTypeface(holder)
    }

    private fun gcase(holder: ViewHolder) {
        val sf = SharedPref(context)
        val language = sharedPreferences!!.getString("lan", "en")
        val array: Array<String>
        array = if (language == "en") {
            context.resources.getStringArray(R.array.encase)
        } else {
            context.resources.getStringArray(R.array.arcase)
        }
        if (isTraditional) {
            holder.nom.text = array[0]
            holder.acc.text = array[1]
            holder.gen.text = array[2]
            holder.nom1.text = array[0]
            holder.acc1.text = array[1]
            holder.gen1.text = array[2]
            holder.nom2.text = array[0]
            holder.acc2.text = array[1]
            holder.gen2.text = array[2]
        } else {
            holder.nom.text = array[0]
            holder.acc.text = array[1]
            holder.gen.text = array[2]
        }
    }

    private fun ismalanumbers(holder: ViewHolder) {
        val sf = SharedPref(context)
        val language = sharedPreferences!!.getString("lan", "en")
        val array: Array<String>
        array = if (language == "en") context.resources.getStringArray(R.array.ennumbers) else {
            context.resources.getStringArray(R.array.arnumbers)
        }
        if (isTraditional) {
            holder.sin1.text = array[0]
            holder.dual1.text = array[1]
            holder.plu1.text = array[2]
            holder.sin2.text = array[0]
            holder.dual2.text = array[1]
            holder.plu2.text = array[2]
            holder.sin3.text = array[0]
            holder.dual3.text = array[1]
            holder.plu3.text = array[2]
        } else {
            holder.sin1.text = array[0]
            holder.dual1.text = array[1]
            holder.plu1.text = array[2]
            holder.sin2.text = array[0]
            holder.dual2.text = array[1]
            holder.plu2.text = array[2]
            holder.sin3.text = array[0]
            holder.dual3.text = array[1]
            holder.plu3.text = array[2]
        }
    }

    private fun IsmAlaMifaal(holder: ViewHolder, position: Int) {
        val mifaalone = sarfSagheer[position][0].toString() //String mifaalone);
        val mifaaltwo = sarfSagheer[position][1].toString() //String mifaaltwo);
        val mifaalthree = "-" //String mifaalthree);
        val mifaalfour = sarfSagheer[position][2].toString() //String mifaalfour);
        val mifaalfive = sarfSagheer[position][3].toString() //String mifaalfive);
        val mifaalsix = "-" //String mifaalsix);
        val mifaalseven = sarfSagheer[position][4].toString() //String mifaalseven);
        val mifaaleight = sarfSagheer[position][5].toString() //String mifaaleight);
        val mifaalnine = "-" //mifaalnine);
        //     FontSIzeSelection(holder);
        SetTypeface(holder)
        holder.mifaalone.text = mifaalone
        holder.mifaaltwo.text = mifaaltwo
        holder.mifaalthree.text = mifaalthree
        holder.mifaalfour.text = mifaalfour
        holder.mifaalfive.text = mifaalfive
        holder.mifaalsix.text = mifaalsix
        holder.mifaalseven.text = mifaalseven
        holder.mifaaleight.text = mifaaleight
        holder.mifaalnine.text = mifaalnine
    }

    private fun IsmAlamifalmifalatunNaqis(holder: ViewHolder, position: Int) {
        val mifalatunone = sarfSagheer[position][0].toString() //String mifalatunone);
        val mifalatuntwo = sarfSagheer[position][1].toString() //String mifalatuntwo);
        val mifalatunthree = sarfSagheer[position][2].toString() //String mifalatunthree);
        val mifalatunfour = sarfSagheer[position][3].toString() //String mifalatunfour);
        val mifalatunfive = sarfSagheer[position][4].toString() //String mifalatunfive);
        val mifalatunsix = sarfSagheer[position][5].toString() //String mifalatunsix);
        val mifalatunseven = sarfSagheer[position][6].toString() //String mifalatunseven);
        val mifalatuneight = sarfSagheer[position][7].toString() //String mifalatuneight);
        val mifalatunnine = sarfSagheer[position][8].toString() //mifalatunnine);
        //      FontSIzeSelection(holder);
        SetTypeface(holder)
        holder.mifalatunone.text = mifalatunone
        holder.mifalatuntwo.text = mifalatuntwo
        holder.mifalatunthree.text = mifalatunthree
        holder.mifalatunfour.text = mifalatunfour
        holder.mifalatunfive.text = mifalatunfive
        holder.mifalatunsix.text = mifalatunsix
        holder.mifalatunseven.text = mifalatunseven
        holder.mifalatuneight.text = mifalatuneight
        holder.mifalatunnine.text = mifalatunnine
    }

    private fun IsmAlamifalmifal(holder: ViewHolder, position: Int) {
        val mifalone = sarfSagheer[position][0].toString() //String smifalone);
        val mifaltwo = sarfSagheer[position][1].toString() //String smifaltwo);
        val mifalthree = "-" //String smifalthree);
        val mifalfour = sarfSagheer[position][2].toString() //String smifalfour);
        val mifalfive = sarfSagheer[position][3].toString() //String smifalfive);
        val mifalsix = "-"
        val mifalseven = sarfSagheer[position][4].toString() //String smifalseven);
        val mifaleight = sarfSagheer[position][5].toString() //String smifaleight);
        val mifalnine = "-" //mifalnine);
        //    FontSIzeSelection(holder);
        SetTypeface(holder)
        //       FontSIzeSelection(holder);
        SetTypeface(holder)
        holder.mifalone.text = mifalone
        holder.mifaltwo.text = mifaltwo
        holder.mifalthree.text = mifalthree
        holder.mifalfour.text = mifalfour
        holder.mifalfive.text = mifalfive
        holder.mifalsix.text = mifalsix
        holder.mifalseven.text = mifalseven
        holder.mifaleight.text = mifaleight
        holder.mifalnine.text = mifalnine
    }

    private fun IsmAlaMifalatun(holder: ViewHolder, position: Int) {
        val mifalatunone = sarfSagheer[position][0].toString() //String mifalatunone);
        val mifalatuntwo = sarfSagheer[position][1].toString() //String mifalatuntwo);
        val mifalatunthree = "-" //String mifalatunthree);
        val mifalatunfour = sarfSagheer[position][2].toString() //String mifalatunfour);
        val mifalatunfive = sarfSagheer[position][3].toString() //String mifalatunfive);
        val mifalatunsix = "-" //String mifalatunsix);
        val mifalatunseven = sarfSagheer[position][4].toString() //String mifalatunseven);
        val mifalatuneight = sarfSagheer[position][5].toString() //String mifalatuneight);
        val mifalatunnine = "-" //mifalatunnine);
        //    FontSIzeSelection(holder);
        SetTypeface(holder)
        //    FontSIzeSelection(holder);
        SetTypeface(holder)
        holder.mifalatunone.text = mifalatunone
        holder.mifalatuntwo.text = mifalatuntwo
        holder.mifalatunthree.text = mifalatunthree
        holder.mifalatunfour.text = mifalatunfour
        holder.mifalatunfive.text = mifalatunfive
        holder.mifalatunsix.text = mifalatunsix
        holder.mifalatunseven.text = mifalatunseven
        holder.mifalatuneight.text = mifalatuneight
        holder.mifalatunnine.text = mifalatunnine
    }

    private fun FontSIzeSelection(holder: ViewHolder) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )

        val width = sharedPreferences.getString("width", "compactWidth")
        defaultfont = sharedPreferences.getBoolean("default_font", true)
        val arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key", 20)
        if (!defaultfont) {
            if (width == "mediumWidth" || width == "expandedWidth") {
                if (isTraditional) {
                    holder.nom.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.acc.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.gen.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.nom1.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.acc1.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.gen1.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.nom2.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.acc2.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.gen2.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin1.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual1.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu1.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin2.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual2.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu2.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin3.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual3.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu3.textSize = arabicFontsize.toFloat() //(array[2]);
                } else {
                    holder.nom.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.acc.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.gen.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin1.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual1.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu1.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin2.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual2.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.plu2.textSize = arabicFontsize.toFloat() //(array[2]);
                    holder.sin3.textSize = arabicFontsize.toFloat() //(array[0]);
                    holder.dual2.textSize = arabicFontsize.toFloat() //(array[1]);
                    holder.dual3.textSize = arabicFontsize.toFloat() //(array[2]);
                }
            }
        }
        if (!defaultfont) {
            holder.mifalone.textSize = arabicFontsize.toFloat() //(mifalone);
            holder.mifaltwo.textSize = arabicFontsize.toFloat() //(mifaltwo);
            holder.mifalthree.textSize = arabicFontsize.toFloat() //(mifalthree);
            holder.mifalfour.textSize = arabicFontsize.toFloat() //(mifalfour);
            holder.mifalfive.textSize = arabicFontsize.toFloat() //(mifalfive);
            holder.mifalsix.textSize = arabicFontsize.toFloat() //(mifalsix);
            holder.mifalseven.textSize = arabicFontsize.toFloat() //(mifalseven);
            holder.mifaleight.textSize = arabicFontsize.toFloat() //(mifaleight);
            holder.mifalnine.textSize = arabicFontsize.toFloat() //(mifalnine);
            holder.mifalatunone.textSize = arabicFontsize.toFloat() //(mifalatunone);
            holder.mifalatuntwo.textSize = arabicFontsize.toFloat() //(mifalatuntwo);
            holder.mifalatunthree.textSize = arabicFontsize.toFloat() //(mifalatunthree);
            holder.mifalatunfour.textSize = arabicFontsize.toFloat() //(mifalatunfour);
            holder.mifalatunfive.textSize = arabicFontsize.toFloat() //(mifalatunfive);
            holder.mifalatunsix.textSize = arabicFontsize.toFloat() //(mifalatunsix);
            holder.mifalatunseven.textSize = arabicFontsize.toFloat() //(mifalatunseven);
            holder.mifalatuneight.textSize = arabicFontsize.toFloat() //(mifalatuneight);
            holder.mifalatunnine.textSize = arabicFontsize.toFloat() //(mifalatunnine);
            holder.mifaalone.textSize = arabicFontsize.toFloat() //(mifaalone);
            holder.mifaaltwo.textSize = arabicFontsize.toFloat() //(mifaaltwo);
            holder.mifaalthree.textSize = arabicFontsize.toFloat() //(mifaalthree);
            holder.mifaalfour.textSize = arabicFontsize.toFloat() //(mifaalfour);
            holder.mifaalfive.textSize = arabicFontsize.toFloat() //(mifaalfive);
            holder.mifaalsix.textSize = arabicFontsize.toFloat() //(mifaalsix);
            holder.mifaalseven.textSize = arabicFontsize.toFloat() //(mifaalseven);
            holder.mifaaleight.textSize = arabicFontsize.toFloat() //(mifaaleight);
            holder.mifaalnine.textSize = arabicFontsize.toFloat() //(mifaalnine);
        }
    }

    private fun SetTypeface(holder: ViewHolder) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )

        //  String theme = sharedPreferences.getString("theme", 1);
        val indictive = sharedPreferences.getString("Arabic_Font_Selection", "kitab.ttf")
        arabicTypeface = Typeface.createFromAsset(context.assets, indictive)
        holder.nom.typeface = arabicTypeface //(array[0]);
        holder.acc.typeface = arabicTypeface //(array[1]);
        holder.gen.typeface = arabicTypeface //(array[2]);
        holder.sin1.typeface = arabicTypeface // (array[0]);
        holder.dual1.typeface = arabicTypeface // (array[1]);
        holder.plu1.typeface = arabicTypeface // (array[2]);
        holder.sin2.typeface = arabicTypeface // (array[0]);
        holder.dual2.typeface = arabicTypeface // (array[1]);
        holder.plu2.typeface = arabicTypeface // (array[2]);
        holder.sin3.typeface = arabicTypeface // (array[0]);
        holder.dual2.typeface = arabicTypeface // (array[1]);
        holder.dual3.typeface = arabicTypeface // (array[2]);
        holder.mifalone.typeface = arabicTypeface //(mifalone);
        holder.mifaltwo.typeface = arabicTypeface //(mifaltwo);
        holder.mifalthree.typeface = arabicTypeface //(mifalthree);
        holder.mifalfour.typeface = arabicTypeface //(mifalfour);
        holder.mifalfive.typeface = arabicTypeface //(mifalfive);
        holder.mifalsix.typeface = arabicTypeface //(mifalsix);
        holder.mifalseven.typeface = arabicTypeface //(mifalseven);
        holder.mifaleight.typeface = arabicTypeface //(mifaleight);
        holder.mifalnine.typeface = arabicTypeface //(mifalnine);
        holder.mifalatunone.typeface = arabicTypeface //(mifalatunone);
        holder.mifalatuntwo.typeface = arabicTypeface //(mifalatuntwo);
        holder.mifalatunthree.typeface = arabicTypeface //(mifalatunthree);
        holder.mifalatunfour.typeface = arabicTypeface //(mifalatunfour);
        holder.mifalatunfive.typeface = arabicTypeface //(mifalatunfive);
        holder.mifalatunsix.typeface = arabicTypeface //(mifalatunsix);
        holder.mifalatunseven.typeface = arabicTypeface //(mifalatunseven);
        holder.mifalatuneight.typeface = arabicTypeface //(mifalatuneight);
        holder.mifalatunnine.typeface = arabicTypeface //(mifalatunnine);
        holder.mifaalone.typeface = arabicTypeface //(mifaalone);
        holder.mifaaltwo.typeface = arabicTypeface //(mifaaltwo);
        holder.mifaalthree.typeface = arabicTypeface //(mifaalthree);
        holder.mifaalfour.typeface = arabicTypeface //(mifaalfour);
        holder.mifaalfive.typeface = arabicTypeface //(mifaalfive);
        holder.mifaalsix.typeface = arabicTypeface //(mifaalsix);
        holder.mifaalseven.typeface = arabicTypeface //(mifaalseven);
        holder.mifaaleight.typeface = arabicTypeface //(mifaaleight);
        holder.mifaalnine.typeface = arabicTypeface //(mifaalnine);
    }

    override fun getItemId(position: Int): Long {
        //  Surah surah = surahArrayList.get(position);
        return sarfSagheer.size.toLong()
    }

    fun getItem(position: Int): Any {
        return sarfSagheer[position]
    }

    override fun getItemCount(): Int {
        //  return sarfSagheer.size();
        return 1
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
        //  public final TextView ayah_number;
        val madhihua: TextView
        val madhihuma: TextView
        val madhihum: TextView
        val madhihia: TextView
        val madhihumaf: TextView
        val madhihunna: TextView
        val madhianta: TextView
        val madhiantuma: TextView
        val madhiantum: TextView
        val madhianti: TextView
        val madhiantumaf: TextView
        val madhiantunna: TextView
        val madhiana: TextView
        val madhinahnu: TextView
        val muzhua: TextView
        val muzhuma: TextView
        val muzhum: TextView
        val muzhia: TextView
        val muzhumaf: TextView
        val muzhunna: TextView
        val muzanta: TextView
        val muzantuma: TextView
        val muzantum: TextView
        val muzanti: TextView
        val muzantumaf: TextView
        val muzantunna: TextView
        val muzana: TextView
        val muznahnu: TextView
        val madimajhua: TextView
        val madimajhuma: TextView
        val madimajhum: TextView
        val madimajhia: TextView
        val madimajhumaf: TextView
        val madimajhunna: TextView
        val madimajanta: TextView
        val madimajantuma: TextView
        val madimajantum: TextView
        val madimajanti: TextView
        val madimajantumaf: TextView
        val madimajantunna: TextView
        val madimajana: TextView
        val madimajnahnu: TextView
        val muzmajhua: TextView
        val muzmajhuma: TextView
        val muzmajhum: TextView
        val muzmajhia: TextView
        val muzmajhumaf: TextView
        val muzmajhunna: TextView
        val muzmajanta: TextView
        val muzmajantuma: TextView
        val muzmajantum: TextView
        val muzmajanti: TextView
        val muzmajantumaf: TextView
        val muzmajantunna: TextView
        val muzmajana: TextView
        val muzmajnahnu: TextView
        val amranta: TextView
        val amrantuma: TextView
        val amrantum: TextView
        val amranti: TextView
        val amrantumaf: TextView
        val amrantunna: TextView
        val nahiamranta: TextView
        val nahiamrantuma: TextView
        val nahiamrantum: TextView
        val nahiamranti: TextView
        val nahiamrantumaf: TextView
        val nahiamrantunna: TextView

        // imafmnine
        val isone: TextView
        val istwo: TextView
        val isthree: TextView
        val isfour: TextView
        val isfive: TextView
        val issix: TextView
        val isseven: TextView
        val iseight: TextView
        val isnine: TextView
        val ismfemone: TextView
        val ismfemtwo: TextView
        val ismfemthree: TextView
        val ismfemfour: TextView
        val ismfemfive: TextView
        val ismfemsix: TextView
        val ismfemseven: TextView
        val ismfemeight: TextView
        val ismfemnine: TextView
        val imafone: TextView
        val imaftwo: TextView
        val imafthree: TextView
        val imaffour: TextView
        val imaffive: TextView
        val imafsix: TextView
        val imafseven: TextView
        val imafeight: TextView
        val imafnine: TextView
        val imafoolfemone: TextView
        val imafoolfemtwo: TextView
        val imafoolfemthree: TextView
        val imafoolfemfour: TextView
        val imafoolfemfive: TextView
        val imafoolfemsix: TextView
        val imafoolfemseven: TextView
        val imafoolfemeight: TextView
        val imafoolfemnine: TextView
        val mifalone: TextView
        val mifaltwo: TextView
        val mifalthree: TextView
        val mifalfour: TextView
        val mifalfive: TextView
        val mifalsix: TextView
        val mifalseven: TextView
        val mifaleight: TextView
        val mifalnine: TextView
        val mifalatunone: TextView
        val mifalatuntwo: TextView
        val mifalatunthree: TextView
        val mifalatunfour: TextView
        val mifalatunfive: TextView
        val mifalatunsix: TextView
        val mifalatunseven: TextView
        val mifalatuneight: TextView
        val mifalatunnine: TextView
        val mifaalone: TextView
        val mifaaltwo: TextView
        val mifaalthree: TextView
        val mifaalfour: TextView
        val mifaalfive: TextView
        val mifaalsix: TextView
        val mifaalseven: TextView
        val mifaaleight: TextView
        val mifaalnine: TextView
        val mafalunone: TextView
        val mafaluntwo: TextView
        val mafalunthree: TextView
        val mafalunfour: TextView
        val mafalunfive: TextView
        val mafalunsix: TextView
        val mafalunseven: TextView
        val mafaluneight: TextView
        val mafalunnine: TextView
        val mifalunone: TextView
        val mifaluntwo: TextView
        val mifalunthree: TextView
        val mifalunfour: TextView
        val mifalunfive: TextView
        val mifalunsix: TextView
        val mifalunseven: TextView
        val mifaluneight: TextView
        val mifalunnine: TextView
        val sin1: TextView
        val dual1: TextView
        val plu1: TextView
        val sin2: TextView
        val dual2: TextView
        val plu2: TextView
        val sin3: TextView
        val dual3: TextView
        val plu3: TextView
        val nom: TextView
        val acc: TextView
        val gen: TextView
        val nom1: TextView
        val acc1: TextView
        val gen1: TextView
        val nom2: TextView
        val acc2: TextView
        val gen2: TextView

        init {
            nom = view.findViewById(R.id.nominative)
            acc = view.findViewById(R.id.accusative)
            gen = view.findViewById(R.id.genitive)
            nom1 = view.findViewById(R.id.nominative1)
            acc1 = view.findViewById(R.id.accusative1)
            gen1 = view.findViewById(R.id.genitive1)
            nom2 = view.findViewById(R.id.nominative2)
            acc2 = view.findViewById(R.id.accusative2)
            gen2 = view.findViewById(R.id.genitive2)
            sin1 = view.findViewById(R.id.singular1)
            dual1 = view.findViewById(R.id.dual1)
            plu1 = view.findViewById(R.id.plural1)
            sin2 = view.findViewById(R.id.singular2)
            dual2 = view.findViewById(R.id.dual2)
            plu2 = view.findViewById(R.id.plural2)
            sin3 = view.findViewById(R.id.singular3)
            dual3 = view.findViewById(R.id.dual3)
            plu3 = view.findViewById(R.id.plural3)
            madhihua = view.findViewById(R.id.madhihua)
            madhihuma = view.findViewById(R.id.madhihuma)
            madhihum = view.findViewById(R.id.madihum)
            madhihia = view.findViewById(R.id.madhihia)
            madhihumaf = view.findViewById(R.id.madhihumaf)
            madhihunna = view.findViewById(R.id.madihunna)
            madhianta = view.findViewById(R.id.madhianta)
            madhiantuma = view.findViewById(R.id.madhiantuma)
            madhiantum = view.findViewById(R.id.madhiantum)
            madhianti = view.findViewById(R.id.madhianti)
            madhiantunna = view.findViewById(R.id.madhiantumaf)
            madhiantumaf = view.findViewById(R.id.madhiantunna)
            madhiana = view.findViewById(R.id.madhiana)
            madhinahnu = view.findViewById(R.id.madhinahnu)
            muzhua = view.findViewById(R.id.muzhua)
            muzhuma = view.findViewById(R.id.muzhuma)
            muzhum = view.findViewById(R.id.muzhum)
            muzhia = view.findViewById(R.id.muzhia)
            muzhumaf = view.findViewById(R.id.muzhumaf)
            muzhunna = view.findViewById(R.id.muzhunna)
            muzanta = view.findViewById(R.id.muzanta)
            muzantuma = view.findViewById(R.id.muzantuma)
            muzantum = view.findViewById(R.id.muzantum)
            muzanti = view.findViewById(R.id.muzanti)
            muzantumaf = view.findViewById(R.id.muzantumaf)
            muzantunna = view.findViewById(R.id.muzantunna)
            muzana = view.findViewById(R.id.muzana)
            muznahnu = view.findViewById(R.id.muznahnu)
            //
            madimajhua = view.findViewById(R.id.madimajhua)
            madimajhuma = view.findViewById(R.id.madimajhuma)
            madimajhum = view.findViewById(R.id.madimajhum)
            madimajhia = view.findViewById(R.id.madimajhia)
            madimajhumaf = view.findViewById(R.id.madimajhumaf)
            madimajhunna = view.findViewById(R.id.madimajhunna)
            madimajanta = view.findViewById(R.id.madimajanta)
            madimajantuma = view.findViewById(R.id.madimajantuma)
            madimajantum = view.findViewById(R.id.madimajantum)
            madimajanti = view.findViewById(R.id.madimajanti)
            madimajantumaf = view.findViewById(R.id.madimajantumaf)
            madimajantunna = view.findViewById(R.id.madimajantunna)
            madimajana = view.findViewById(R.id.madimajana)
            madimajnahnu = view.findViewById(R.id.madimajnahnu)
            ///muzmajhool
            muzmajhua = view.findViewById(R.id.muzmajhua)
            muzmajhuma = view.findViewById(R.id.muzmajhuma)
            muzmajhum = view.findViewById(R.id.muzmajhum)
            muzmajhia = view.findViewById(R.id.muzmajhia)
            muzmajhumaf = view.findViewById(R.id.muzmajhumaf)
            muzmajhunna = view.findViewById(R.id.muzmajhunna)
            muzmajanta = view.findViewById(R.id.muzmajanta)
            muzmajantuma = view.findViewById(R.id.muzmajantuma)
            muzmajantum = view.findViewById(R.id.muzmajantum)
            muzmajanti = view.findViewById(R.id.muzmajanti)
            muzmajantumaf = view.findViewById(R.id.muzmajantumaf)
            muzmajantunna = view.findViewById(R.id.muzmajantunna)
            muzmajana = view.findViewById(R.id.muzmajana)
            muzmajnahnu = view.findViewById(R.id.muzmajnahnu)
            //
            amranta = view.findViewById(R.id.amranta)
            amrantuma = view.findViewById(R.id.amrantuma)
            amrantum = view.findViewById(R.id.amrantum)
            amranti = view.findViewById(R.id.amranti)
            amrantumaf = view.findViewById(R.id.amrantumaf)
            amrantunna = view.findViewById(R.id.amrantunna)
            nahiamranta = view.findViewById(R.id.nahiamranta)
            nahiamrantuma = view.findViewById(R.id.nahiamrantuma)
            nahiamrantum = view.findViewById(R.id.nahiamrantum)
            nahiamranti = view.findViewById(R.id.nahiamranti)
            nahiamrantumaf = view.findViewById(R.id.nahiamrantumaf)
            nahiamrantunna = view.findViewById(R.id.nahiamrantunna)
            //ismfael masculine
            ismfemone = view.findViewById(R.id.ismfemone)
            ismfemtwo = view.findViewById(R.id.ismfemtwo)
            ismfemthree = view.findViewById(R.id.ismfemthree)
            ismfemfour = view.findViewById(R.id.ismfemfour)
            ismfemfive = view.findViewById(R.id.ismfemfive)
            ismfemsix = view.findViewById(R.id.ismfemsix)
            ismfemseven = view.findViewById(R.id.ismfemseven)
            ismfemeight = view.findViewById(R.id.ismfemeight)
            ismfemnine = view.findViewById(R.id.ismfemnine)
            //
            isone = view.findViewById(R.id.isone)
            istwo = view.findViewById(R.id.istwo)
            isthree = view.findViewById(R.id.isthree)
            isfour = view.findViewById(R.id.isfour)
            isfive = view.findViewById(R.id.isfive)
            issix = view.findViewById(R.id.issix)
            isseven = view.findViewById(R.id.isseven)
            iseight = view.findViewById(R.id.iseight)
            isnine = view.findViewById(R.id.isnine)
            //ismmafoolmasculine
            imafone = view.findViewById(R.id.imafone)
            imaftwo = view.findViewById(R.id.imaftwo)
            imafthree = view.findViewById(R.id.imafthree)
            imaffour = view.findViewById(R.id.imaffour)
            imaffive = view.findViewById(R.id.imaffive)
            imafsix = view.findViewById(R.id.imafsix)
            imafseven = view.findViewById(R.id.imafseven)
            imafeight = view.findViewById(R.id.imafeight)
            imafnine = view.findViewById(R.id.imafnine)
            //ismmafoolfeb
            imafoolfemone = view.findViewById(R.id.imafoolfemone)
            imafoolfemtwo = view.findViewById(R.id.imafoolfemtwo)
            imafoolfemthree = view.findViewById(R.id.imafoolfemthree)
            imafoolfemfour = view.findViewById(R.id.imafoolfemfour)
            imafoolfemfive = view.findViewById(R.id.imafoolfemfive)
            imafoolfemsix = view.findViewById(R.id.imafoolfemsix)
            imafoolfemseven = view.findViewById(R.id.imafoolfemseven)
            imafoolfemeight = view.findViewById(R.id.imafoolfemeight)
            imafoolfemnine = view.findViewById(R.id.imafoolfemnine)
            mifalone = view.findViewById(R.id.mifalone)
            mifaltwo = view.findViewById(R.id.mifaltwo)
            mifalthree = view.findViewById(R.id.mifalthree)
            mifalfour = view.findViewById(R.id.mifalfour)
            mifalfive = view.findViewById(R.id.mifalfive)
            mifalsix = view.findViewById(R.id.mifalsix)
            mifalseven = view.findViewById(R.id.mifalseven)
            mifaleight = view.findViewById(R.id.mifaleight)
            mifalnine = view.findViewById(R.id.mifalnine)
            mifalatunone = view.findViewById(R.id.mifalatunone)
            mifalatuntwo = view.findViewById(R.id.mifalatuntwo)
            mifalatunthree = view.findViewById(R.id.mifalatunthree)
            mifalatunfour = view.findViewById(R.id.mifalatunfour)
            mifalatunfive = view.findViewById(R.id.mifalatunfive)
            mifalatunsix = view.findViewById(R.id.mifalatunsix)
            mifalatunseven = view.findViewById(R.id.mifalatunseven)
            mifalatuneight = view.findViewById(R.id.mifalatuneight)
            mifalatunnine = view.findViewById(R.id.mifalatunnine)
            mifaalone = view.findViewById(R.id.mifaalone)
            mifaaltwo = view.findViewById(R.id.mifaaltwo)
            mifaalthree = view.findViewById(R.id.mifaalthree)
            mifaalfour = view.findViewById(R.id.mifaalfour)
            mifaalfive = view.findViewById(R.id.mifaalfive)
            mifaalsix = view.findViewById(R.id.mifaalsix)
            mifaalseven = view.findViewById(R.id.mifaalseven)
            mifaaleight = view.findViewById(R.id.mifaaleight)
            mifaalnine = view.findViewById(R.id.mifaalnine)
            mafalunone = view.findViewById(R.id.mafalunone)
            mafaluntwo = view.findViewById(R.id.mafaluntwo)
            mafalunthree = view.findViewById(R.id.mafalunthree)
            mafalunfour = view.findViewById(R.id.mafalunfour)
            mafalunfive = view.findViewById(R.id.mafalunfive)
            mafalunsix = view.findViewById(R.id.mafalunsix)
            mafalunseven = view.findViewById(R.id.mafalunseven)
            mafaluneight = view.findViewById(R.id.mafaluneight)
            mafalunnine = view.findViewById(R.id.mafalunnine)
            mifalunone = view.findViewById(R.id.mifalunone)
            mifaluntwo = view.findViewById(R.id.mifaluntwo)
            mifalunthree = view.findViewById(R.id.mifalunthree)
            mifalunfour = view.findViewById(R.id.mifalunfour)
            mifalunfive = view.findViewById(R.id.mifalunfive)
            mifalunsix = view.findViewById(R.id.mifalunsix)
            mifalunseven = view.findViewById(R.id.mifalunseven)
            mifaluneight = view.findViewById(R.id.mifaluneight)
            mifalunnine = view.findViewById(R.id.mifalunnine)
            view.setOnClickListener(this) // current clickListerner
            view.setOnClickListener(this) // current clickListerner
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, layoutPosition)
            }
        }
    }
}