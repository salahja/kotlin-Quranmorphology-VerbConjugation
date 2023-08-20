package com.example.mushafconsolidated.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.preference.PreferenceManager
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import java.util.Objects

class NounVerbOccuranceListAdapter(// private   HashMap<String, List<SpannableStringBuilder>> expandableListDetail;
    private val context: Context, private val expandableListTitle: List<String>,
    val expandNounVerses: LinkedHashMap<String, List<SpannableString?>>
) : BaseExpandableListAdapter() {
    override fun getChild(listPosition: Int, expandedListPosition: Int): SpannableString? {
        return Objects.requireNonNull(expandNounVerses[expandableListTitle[listPosition]])!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    @SuppressLint("InflateParams")
    override fun getChildView(
        listPosition: Int, expandedListPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View? {
        //  SpannableString expandedListText = (SpannableString) getChild(listPosition, expandedListPosition);
        var convertView = convertView
        val child = getChild(listPosition, expandedListPosition)
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_grammar_item, null)
        }
        val mequran =
            Typeface.createFromAsset(QuranGrammarApplication.context!!.getAssets(), "Taha.ttf")
        //  Typeface mequran = Typeface.createFromAsset(DarkThemeApplication.context!!.getAssets(), quranfont);
        val expandedListTextView = convertView
            ?.findViewById<TextView>(R.id.expandedListItem)
        val contains = false
        if (contains) {
            //setTextDirection(View.TEXT_DIRECTION_ANY_RTL)
            if (expandedListTextView != null) {
                expandedListTextView.textDirection = View.TEXT_DIRECTION_LTR
            }
            if (expandedListTextView != null) {
                expandedListTextView.text = HtmlCompat.fromHtml(child.toString(), 0)
            }
        } else {
            if (expandedListTextView != null) {
                expandedListTextView.text = HtmlCompat.fromHtml(child.toString(), 0)
            }
            //  expandedListTextView.setText((CharSequence) child);
        }
        if (expandedListTextView != null) {
            expandedListTextView.text = HtmlCompat.fromHtml(child.toString(), 0)
        }
        if (expandedListTextView != null) {
            expandedListTextView.setTypeface(mequran)
        }
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return Objects.requireNonNull(expandNounVerses[expandableListTitle[listPosition]])
            ?.size ?: 0
    }

    override fun getGroup(listPosition: Int): Any {
        return expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    @SuppressLint("InflateParams")
    override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View? {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertView
            ?.findViewById<TextView>(R.id.listTitle)
        if (listTitleTextView != null) {
            listTitleTextView.setTypeface(null, Typeface.BOLD)
        }
        val prefs =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val preferences = prefs.getString("theme", "dark")
        if (preferences == "dark" || preferences == "blue" || preferences == "green") {
            if (listTitleTextView != null) {
                listTitleTextView.setTextColor(Color.CYAN)
            }
        } else {
            if (listTitleTextView != null) {
                listTitleTextView.setTextColor(
                    ContextCompat.getColor(
                        QuranGrammarApplication.context!!,
                        R.color.burntamber
                    )
                )
            }
        }
        if (listTitleTextView != null) {
            listTitleTextView.textSize = 18f
        }
        if (listTitleTextView != null) {
            listTitleTextView.text = listTitle
        }
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}