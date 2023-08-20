package com.example.mushafconsolidatedimport

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.utility.CorpusUtilityorig
import com.example.utility.QuranGrammarApplication.Companion.context

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
class ParticleColorScheme constructor() : BottomSheetDialogFragment() {
    var mItemClickListener: OnItemClickListener? = null
    var textView: TextView? = null
    private var colorSchemeAdapter: ColorSchemeAdapter? = null
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.colorschemelayout, container, false)
    }




    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //  recyclerView.setLayoutManager(new LinearLayoutManager(context!!));
        val mLayoutManager: GridLayoutManager = GridLayoutManager(getActivity(), 2)
        // parentRecyclerView = view.findViewById(R.id.juzRecyclerView);
        val recyclerView: RecyclerView = view.findViewById(R.id.colorrecview)
        recyclerView.setLayoutManager(mLayoutManager)
        val spanhash: Map<String?, ForegroundColorSpan>
        spanhash = CorpusUtilityorig.stringForegroundColorSpanMap
        textView = view.findViewById(R.id.Colortv)
        val particle: java.util.ArrayList<String?>? =null
        particle?.add("PN = \"Proper Noun(اسم علم)\",")
        particle?.add("ADJ = \"Adjective(صفة)")
        particle?.add("V =Verb(فعل)")
        particle?.add("N =Noun")
        particle?.add(" PRON = Pronouns(ضمير)")
        particle?.add("DEM = Demonstrative Pronoun(اسم اشارة)")
        particle?.add(" REL =  Relative Pronoun(اسم موصول)")
        particle?.add("T =  Time Adverb(ظرف زمان)")
        particle?.add("  LOC =  Location Adverb(ظرف مكان)")
        particle?.add("DET  determiner()")
        particle?.add("EMPH  Emphatic lām prefix(لام التوكيد) ")
        particle?.add("IMPV  Imperative lāmprefix(لام الامر)")
        particle?.add("PRP  Purpose lāmprefix(لام التعليل)")
        particle?.add("CONJ  Coordinating conjunction(حرف عطف)")
        particle?.add("SUB  	Subordinating conjunction(حرف مصدري)")
        particle?.add("ACC  	Accusative particle(حرف نصب)")
        particle?.add("AMD  	Amendment particle(حرف استدراك)	")
        particle?.add("ANS  	Answer particle	(حرف جواب)")
        particle?.add("AVR  	Aversion particle	(حرف ردع)")
        particle?.add("CAUS  Particle of cause	(حرف سببية)")
        particle?.add("CERT  Particle of certainty	(حرف تحقيق)")
        particle?.add("CIRC  Circumstantial particle	(حرف حال)")
        particle?.add("COM  	Comitative particle	(واو المعية)")
        particle?.add("COND  Conditional particle(حرف شرط)")
        particle?.add("EQ  	Equalization particle(حرف تسوية)")
        particle?.add("EXH  	Exhortation particle(حرف تحضيض)")
        particle?.add("EXL  	Explanation particle(حرف تفصيل)")
        particle?.add("EXP  	Exceptive particle	(أداة استثناء)")
        particle?.add("FUT  	Future particle	(حرف استقبال)")
        particle?.add("INC  	Inceptive particle	(حرف ابتداء)")
        particle?.add("INT  	Particle of interpretation(حرف تفسير)")
        particle?.add("INTG  Interogative particle	(حرف استفهام)")
        particle?.add("NEG  	Negative particle(حرف نفي)")
        particle?.add("PREV  Preventive particle	(حرف كاف)")
        particle?.add("PRO  	Prohibition particle(حرف نهي)")
        particle?.add("REM  	Resumption particle	(حرف استئنافية)")
        particle?.add("RES  	Restriction particle(أداة حصر)")
        particle?.add("RET  	Retraction particle	(حرف اضراب)")
        particle?.add("RSLT  Result particle(حرف واقع في جواب الشرط)")
        particle?.add("SUP  	Supplemental particle	(حرف زائد)")
        particle?.add("SUR  	Surprise particle	(حرف فجاءة)")
        particle?.add("VOC  	Vocative particle	(حرف نداء)")
        particle?.add("INL  	Quranic initials(	(حروف مقطعة	")
        val details: ArrayList<String> = ArrayList()
        val sample: String = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
        colorSchemeAdapter = ColorSchemeAdapter(spanhash, particle)
        recyclerView.setAdapter(colorSchemeAdapter)
        colorSchemeAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            public override fun onItemClick(v: View?, position: Int) {
                //      int checkedRadioButtonId = textView.getCheckedRadioButtonId();
                //Toast.makeText(context!!, "", Toast.LENGTH_SHORT).show();
            }
        })
    }

    private class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup?) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.coloradapter, parent, false)),
        View.OnClickListener {

        val text: TextView

        init {
            // TODO: Customize the item layout
            //  super(inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));
            text = itemView.findViewById(R.id.Colortv)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }


    }

    private class ColorSchemeAdapter :
        RecyclerView.Adapter<ParticleColorScheme.ViewHolder> {
        private var particle: ArrayList<String?>? = null
        private var mItemCount: String? = null
        private var spanhash: Map<String?, ForegroundColorSpan>? = null
        private var mItemClickListener: OnItemClickListener? = null

        internal constructor(itemCount: String?) {
            mItemCount = itemCount
        }

        constructor(
            spanhash: Map<String?, ForegroundColorSpan>,
            particle: java.util.ArrayList<String?>?
        ) {
            this.spanhash = spanhash
            this.particle = particle
        }





        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
            val sample = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
            val mequran = Typeface.createFromAsset(context!!.getAssets(), "me_quran.ttf")
            val qalam = Typeface.createFromAsset(context!!.getAssets(), "AlQalam.ttf")
            val s = particle!![position]
            val split = s?.split("\\s".toRegex())?.dropLastWhile { it.isEmpty() }
                ?.toTypedArray()
            val foregroundColorSpan = spanhash!![split?.get(0)]
            val sp = SpannableString(s)


            sp.setSpan(foregroundColorSpan, 0, s!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.text.setText(sp)
            holder.text.setTextSize(20f)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            return particle!!.size
        }

        fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
            this.mItemClickListener = mItemClickListener
        }
    }

    companion object {
        // TODO: Customize parameter argument names
        private val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(): ParticleColorScheme {
            val fragment: ParticleColorScheme = ParticleColorScheme()
            return fragment
        }
    }
}