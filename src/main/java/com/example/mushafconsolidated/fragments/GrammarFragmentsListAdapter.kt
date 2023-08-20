package com.example.mushafconsolidated.fragments

import android.content.Context
import android.content.SharedPreferences
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
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.color
import com.example.mushafconsolidated.R.id
import com.example.utility.QuranGrammarApplication


 

class GrammarFragmentsListAdapter(
    private val context: Context, private val expandableListTitle: List<String>,
    private val expandableListDetail: HashMap<String, List<SpannableString>>
) : BaseExpandableListAdapter() {
    public override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return expandableListDetail.get(expandableListTitle.get(listPosition))!!.get(expandedListPosition)
    }

    public override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    public override fun getChildView(
        listPosition: Int, expandedListPosition: Int,
        isLastChild: Boolean, convertView: View, parent: ViewGroup
    ): View {
        var convertView: View = convertView
        val expandedListText: SpannableString  =
            getChild(listPosition, expandedListPosition) as SpannableString 
        if (convertView == null) {
            val layoutInflater: LayoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_grammar_item, null)
        }
        val mequran: Typeface =
            Typeface.createFromAsset(QuranGrammarApplication.context!!.getAssets(), "Roboto.ttf")
        val expandedListTextView: TextView =
            convertView.findViewById<View>(id.expandedListItem) as TextView
        expandedListTextView.setText(expandedListText)
        //    expandedListTextView.setTypeface(mequran);
        return convertView
    }

    public override fun getChildrenCount(listPosition: Int): Int {
        return expandableListDetail.get(expandableListTitle.get(listPosition))!!.size
    }

    public override fun getGroup(listPosition: Int): Any {
        return expandableListTitle.get(listPosition)
    }

    public override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    public override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    public override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView: View, parent: ViewGroup
    ): View {
        var convertView: View = convertView
        val listTitle: String = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater: LayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView: TextView = convertView
            .findViewById<View>(id.listTitle) as TextView
        val prefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context!!)
        val preferences: String? = prefs.getString("theme", "dark")
        val dark: Boolean =
            ((preferences == "dark") || (preferences == "blue") || (preferences == "green"))
        if (dark) {
            listTitleTextView.setTextColor(Color.CYAN)
        } else {
            listTitleTextView.setTextColor(
                ContextCompat.getColor(
                    QuranGrammarApplication.context!!,
                    color.burntamber
                )
            )
        }
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.setText(listTitle)
        return convertView
    }

    public override fun hasStableIds(): Boolean {
        return false
    }

    public override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}