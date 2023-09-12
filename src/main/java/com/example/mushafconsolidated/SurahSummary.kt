package com.example.mushafconsolidated

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.surahsummary
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.NumberFormat
import java.util.Locale


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * FontQuranListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */
class SurahSummary constructor() : BottomSheetDialogFragment() {
    var mItemClickListener: OnItemClickListener? = null

    // private ColorSchemeAdapter colorSchemeAdapter;
    var textView: TextView? = null
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.namesadapter, container, false)
        val bundle: Bundle? = getArguments()
        val webView: WebView = view.findViewById<View>(R.id.title) as WebView
        //  WebSettingsCompat.setForceDark(webView.settings, FORCE_DARK_ON);
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(
                webView.getSettings(),
                WebSettingsCompat.FORCE_DARK_ON
            )
        }
        val html: String = ("<html>\n" +
                "<head>\n" +
                "<style type=\"text/css\">\n" +
                "@font-face {\n" +
                "    font-family: IndoPak,arial;\n" +
                "    src: url(\"file:///android_asset/me_quran.ttf\")\n" +
                "}\n" +
                " body {\n" +
                "\n" +
                "  margin: 5%;\n" +
                "\n" +
                "}\n" +
                "h3   {color: blue;}\n" +
                "\n" +
                "div { \n" +
                "\n" +
                "  text-align: center;\n" +
                "  font-size: 20px;\n" +
                "}\n" +
                "h1,h2,h2 { \n" +
                "\n" +
                "  text-align: center;\n" +
                "  font-size: 25px;\n" +
                "}\n" +
                "\n" +
                ".ayah{color:#330000;" +
                "font-size:25px;" +
                "font-family:IndoPak,arial;}" +
                ".arabic-text{\n" +
                "  font-family: IndoPak, arial;\n" +
                "  \n" +
                "  border: 2px solid black;\n" +
                "  margin: 20px;\n" +
                "  padding: 20px;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>")
        val close: String = "</body>\n" +
                "</html>"
        assert(bundle != null)
        val item_count: Int = bundle!!.getInt("item_count")
        val utils: Utils = Utils(getActivity())
        val viewmodel: QuranVIewModel by viewModels()
        viewmodel.getSurahSummary(item_count).observe(this, Observer {
          //  val surahSummary: ArrayList<surahsummary> = utils.getSurahSummary(item_count) as ArrayList<surahsummary>
            //   String  ayah = getVersesDetails(item_count, surahSummary);
            var sum: String =       it.get(0).summary
            sum = sum.replace("God".toRegex(), "Allah(SWT)")
            var odiv: String = "<div>"
            val cdiv: String = "</div>"
            sum = sum.replace("\\.".toRegex(), "<br>")
            odiv = odiv + sum + cdiv
            //   String concat = html.concat(odiv).concat(ayah.toString()).concat(close);
            val concat: String = html + odiv + close
            webView.loadDataWithBaseURL(null, concat, "text/html", "utf-8", null)
        })

        return view
    }

    companion object {
        val TAG: String = "SURAH"

        // TODO: Customize parameter argument names
        private val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(data: Int): SurahSummary {
            val fragment: SurahSummary = SurahSummary()
            val args: Bundle = Bundle()
            args.putInt(SurahSummary.Companion.ARG_OPTIONS_DATA, data)
            fragment.setArguments(args)
            return fragment
        }

        private fun getVersesDetails(
            item_count: Int,
            surahSummary: ArrayList<surahsummary>
        ): StringBuilder {
            val versesrange: String = surahSummary.get(0).versesrange
            var wbwentities: List<QuranEntity> = ArrayList()
                var header: StringBuilder  =    java.lang.StringBuilder ()
            val wb: ArrayList<List<QuranEntity>> = ArrayList()
            val split: Array<String> =
                versesrange.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            for (s: String in split) {
                val split1: Array<String> =
                    s.split("-".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                var s1: Int = split1.get(0).trim({ it <= ' ' }).toInt()
                val from: Int = split1.get(0).trim({ it <= ' ' }).toInt()
                val s2: Int = split1.get(1).trim({ it <= ' ' }).toInt()
                while (s1 <= s2) {
                    wbwentities = Utils.Companion.getsurahayahVerses(item_count, s1) as List<QuranEntity>
                    header = java.lang.StringBuilder()
                    header.append("From Verse").append(":").append(from).append(" to ").append(s2)
                        .append(",").append(from).append("-").append(s2)
                    wbwentities.get(0).erabspnabble=SpannableStringBuilder.valueOf(header)

                //    wbwentities[0].setErabspnabble(SpannableStringBuilder.valueOf(header))
                    wb.add(wbwentities)
                    s1++
                }
            }
            val ayah: StringBuilder  = StringBuilder ()
            ayah.append("<div class='ayah' >")
            for (list: List<QuranEntity> in wb) {
                val str: String = list.get(0).erabspnabble.toString()
                val split1: Array<String> =
                    str.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                var surahaya: Array<String>
                var from: Int = 0
                var to: Int = 0
                try {
                    surahaya = split1.get(1).split("-".toRegex()).dropLastWhile({ it.isEmpty() })
                        .toTypedArray()
                    from = surahaya.get(0).toInt()
                    to = surahaya.get(1).toInt()
                } catch (e: ArrayIndexOutOfBoundsException) {
                }
                val vno: Int = list.get(0).ayah
                val nf: NumberFormat = NumberFormat.getInstance(Locale.forLanguageTag("AR"))
                val s: String = "\uFD3E" + nf.format(vno.toLong()) + "\uFD3F"
                if (list.get(0).ayah == from) {
                    ayah.append("<h3>").append(split1.get(0)).append("</h3>")
                }
                ayah.append(
                    s + list.get(0).qurantext + "<br>" + list.get(0).translation + "<br>"
                )
                if (list.get(0).ayah == to) {
                    ayah.append("<hr>")
                }
            }
            ayah.append("</div>")
            return ayah
        }
    }
}