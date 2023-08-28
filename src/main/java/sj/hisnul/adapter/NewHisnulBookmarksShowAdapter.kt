package sj.hisnul.adapter

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.utility.PreferenceUtil
import com.example.utility.QuranGrammarApplication
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.entity.hduanames

/**
 * RecyclerView.Adapter<BookmarksShowAdapter.ViewHolder>
 * Adapter class for the bookmarks list view
</BookmarksShowAdapter.ViewHolder> */
class NewHisnulBookmarksShowAdapter :
    RecyclerView.Adapter<NewHisnulBookmarksShowAdapter.ViewHolder?> {
    var mItemClickListener: OnItemClickListener? = null
    var BookmarksShowAdapterContext: Context? = null
    var bookmarkpostion = 0
    var bookMarkArrayList: ArrayList<hduanames>? = null
    var pref: PreferenceUtil? = null
    var holderposition = 0
    var bookmarid = 0
    val surahName: String? = null
    val bookChapterno = 0
    val bookVerseno = 0

    constructor() {}
    constructor(context: Context?) {
        BookmarksShowAdapterContext = context
    }

   /* fun setHolderposition(holderposition: Int) {
        this.holderposition = holderposition
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bookmark, parent, false)
        //   sendVerseClick=(SendVerseClick) getActivity();
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return bookMarkArrayList!!.size
    }
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catOne: hduanames = bookMarkArrayList!![position]
        holderposition=position

        val shared: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)

        //   final Integer arabicFontsize = Integer.valueOf(fonts);
        val empty: Boolean = catOne.chapname!!.isEmpty()
        if (!empty) {
            val sb = StringBuilder()
            sb.append(catOne.ID)
            holder.id.setText(sb)
            holder.duaname.setText(catOne.chapname)
            holder.duaname.setTextSize(18f)
        } else {
            holder.duaname.setVisibility(View.GONE)
            holder.id.setVisibility(View.GONE)
            holder.ivdelete.visibility = View.GONE
        }
    }

    /*val itemCount: Int
        get() = bookMarkArrayList!!.size*/

    fun getBookMarkArrayList(): List<hduanames>? {
        return bookMarkArrayList
    }



    fun removeItem(position: Int) {
        bookMarkArrayList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): Any {
        return bookMarkArrayList!![position]
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener // current clickListerner
    {
        val id: TextView
        val chapter: TextView
        val duaname: TextView
        val ivdelete: ImageView

        init {
            view.tag = this
            view.setOnClickListener(this)
            //    itemView.setTag(this);
            //     itemView.setOnClickListener(onItemClickListener);
            id = view.findViewById<TextView>(R.id.id)
            chapter = view.findViewById<TextView>(R.id.chapter)
            duaname = view.findViewById<TextView>(R.id.duaname)
            ivdelete = view.findViewById<ImageView>(R.id.ivdelete)
            chapter.setOnClickListener(this)
            id.setOnClickListener(this)
            ivdelete.setOnClickListener(this)
            id.setTag("id")
            ivdelete.tag = "delete"
            duaname.setTag("id")
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(v, getLayoutPosition())
            }
        }
    }

    companion object {
        private const val TAG = "BookmarksShowAdapter"
    }
}