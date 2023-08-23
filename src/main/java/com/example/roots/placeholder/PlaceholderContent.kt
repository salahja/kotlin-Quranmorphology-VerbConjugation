package com.example.roots.placeholder

import com.example.mushafconsolidated.Entities.qurandictionary
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils.Utils
import com.example.utility.QuranGrammarApplication

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {
    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()
    val root = ArrayList<String>()
    private const val COUNT = 25

    init {
        // Add some sample items.
        for (i in 0..27) {
            addItem(createPlaceholderItem(i))
        }
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        val array: Array<String> = QuranGrammarApplication.context!!.getResources()
            .getStringArray(R.array.arabicletters)
        // return new PlaceholderItem(String.valueOf(position), "Item " + position, makeDetails(position));
        return PlaceholderItem(
            position.toString(),
            array[position], makeDetailso(position, array[position], ArrayList()), ArrayList()
        )
    }

    private fun makeDetails(position: Int): ArrayList<String> {

        //    StringBuilder builder = new StringBuilder();
        //   builder.append("Details about Item: ").append(position);
        val details = ArrayList<String>()
        for (i in 0 until position) {
            details.add("details about item")
        }
        //  return builder.toString();
        return details
    }

    private fun makeDetailso(
        position: Int,
        s: String,
        strings: ArrayList<String>
    ): ArrayList<String> {
        val builder = StringBuilder()
        val searchs=s+'%';
        //%
        val letter: ArrayList<qurandictionary> = Utils.getByfirstletter(searchs) as ArrayList<qurandictionary>
        for (qurandictionary in letter) {
            strings.add(qurandictionary.rootarabic)
            //    builder.append(qurandictionary.getRootarabic()).append("\n");
        }


        //   ArrayList<qurandictionary> letter = Utils.getRootwordsbyLetter("غ");
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            //          builder.append("\nMore details information here.");
        }
        return strings
    }

    /**
     * A placeholder item representing a piece of content.
     */
    class PlaceholderItem(
        val id: String,
        val content: String,
        val details: ArrayList<String>,
        rootsArrayList: ArrayList<String>
    ) {
        var rootsArrayList = ArrayList<String>()

        init {
            this.rootsArrayList = rootsArrayList
        }

        override fun toString(): String {
            return content
        }
    }

    class roots private constructor(var rootword: String)
}