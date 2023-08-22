package com.example.mushafconsolidated.fragments

 
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.mushafconsolidated.Activity.QuranGrammarAct
import com.example.mushafconsolidated.Adapters.CollectionShowAdapter
import com.example.mushafconsolidated.DAO.BookMarksPojo
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils.Utils
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener

import com.example.utility.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar













/**
 * Created by Dev. M. Hussein on 5/9/2017.
 */
class CollectionFrag constructor() : Fragment() {
    var coordinatorLayout: CoordinatorLayout? = null
    var layoutManager: RecyclerView.LayoutManager? = null
   private lateinit var collectionShowAdapter: CollectionShowAdapter
    private lateinit var mRecview: RecyclerView
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //    View root!!View = inflater.inflate(R.layout.activity_collection, container, false);
        val view: View = inflater.inflate(R.layout.fragment_bookmark_collection, container, false)
        val utils: Utils = Utils(getActivity())
        val bookMarksNew: List<BookMarksPojo?>? = utils.collectionC

        //  List<BookMarks> bookmarks = new DatabaseAccess().getBookmarks();
        collectionShowAdapter = CollectionShowAdapter(getActivity())
        mRecview = view.findViewById(R.id.recyclerViewAdapterTranslation)
        coordinatorLayout = view.findViewById(R.id.coordinatorLayoutbookmark)
        layoutManager = LinearLayoutManager(getActivity())
        mRecview.setLayoutManager(layoutManager)
        //    bookmarksShowAdapter.setBookMarkArrayList((ArrayList<String>) bookmarstringarray);

        collectionShowAdapter.bookMarkArrayList=bookMarksNew

        mRecview.setAdapter(collectionShowAdapter)
        //    mRecview.setLayoutManager(new LinearLayoutManager(getActivity()));
        enableSwipeToDeleteAndUndo()
        return view
        //  return root!!View;
    }

    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback: SwipeToDeleteCallback =
            object : SwipeToDeleteCallback(getActivity()) {
                public override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                    //    final int position = viewHolder.getAdapterPosition();
                    //  final String item = mAdapter.getData().get(position);
                    //   mAdapter.removeItem(position);
                    val position: Int = viewHolder.getAdapterPosition()
                    val item: BookMarksPojo? =
                        collectionShowAdapter!!.bookMarkArrayList!!.get(position)
                    //   final int code = item.hashCode();
                    collectionShowAdapter!!.getItemId(position)
                    collectionShowAdapter!!.removeItem(position)
                    val snackbar: Snackbar = Snackbar
                        .make(
                            (coordinatorLayout)!!,
                            "Item was removed from the list.",
                            Snackbar.LENGTH_LONG
                        )
                    snackbar.setAction("UNDO", object : View.OnClickListener {
                        public override fun onClick(view: View?) {
                            //     bookmarksShowAdapter.restoreItem(item, position);
                            mRecview!!.scrollToPosition(position)
                        }
                    })
                    snackbar.setActionTextColor(Color.CYAN)
                    snackbar.show()
                    val itemId: Long = collectionShowAdapter!!.getItemId(position)
                    val bookmarkid: Int = collectionShowAdapter!!.bookmarid
                    collectionShowAdapter!!.bookChapterno
                    //      bookmarksShowAdapter.getBookMarkArrayList(bookmarkid)
                    //  Utils butils = new Utils(getActivity());
                    //  butils.deleteBookmarks(bookmarid);
                    if (item != null) {
                        Utils.Companion.deleteCollection(item.header)
                    }
                }
            }
        val itemTouchhelper: ItemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(mRecview)
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectionShowAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            public override fun onItemClick(v: View?, position: Int) {
                val bmark: BookMarksPojo =
                    collectionShowAdapter!!.getItem(position) as BookMarksPojo
                val dataBundle: Bundle = Bundle()
                dataBundle.putInt(Constant.SURAH_ID, bmark.getChapterno().toInt())
                dataBundle.putInt(Constant.AYAHNUMBER, bmark.getVerseno().toInt())
                dataBundle.putString(Constant.SURAH_ARABIC_NAME, bmark.getSurahname())
                dataBundle.putString("type", bmark.header)
                //                dataBundle.putInt(VERSESCOUNT,bmark.getVersescount());
                //VersesFragment frag = new VersesFragment();
                //   frag.setArguments(dataBundle);
                val header: String? = bmark.header
                var fragment: Fragment?
                val readingintent: Intent = Intent(getActivity(), QuranGrammarAct::class.java)
                readingintent.putExtra(Constant.MUFRADATFRAGTAG, false)
                readingintent.putExtra(Constant.CHAPTER, bmark.getChapterno().toInt())
                readingintent.putExtra(Constant.AYAH_ID, bmark.getVerseno().toInt())
                readingintent.putExtra(Constant.CHAPTERORPART, true)
                readingintent.putExtra(Constant.SURAH_ARABIC_NAME, bmark.getSurahname())
                readingintent.putExtra(Constant.WBW, true)
                readingintent.putExtra("type", bmark.header)
                startActivity(readingintent)
            }
        })
    }
}