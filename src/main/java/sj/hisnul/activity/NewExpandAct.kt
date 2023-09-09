package sj.hisnul.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ExpandableListView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import sj.hisnul.entity.hduanamesEnt
import sj.hisnul.fragments.AllDuaModel
import sj.hisnul.fragments.HDuaNamesfrag
import java.util.Collections
import java.util.Objects

class NewExpandAct : BaseActivity(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {
     lateinit var  dataheader: ArrayList<String>

    //  ExpandableListView expandableListView;
    private   var  previousGroup = -1
    private  lateinit var  subjects: HashMap<String?, ParentItem>
    private  lateinit var  parentItemsList: ArrayList<ParentItem>
    private  lateinit var  expandableListView: ExpandableListView
    private  lateinit var  childItemsList: ArrayList<ChildItem>
    private   var  hduanamesArrayList: ArrayList<hduanamesEnt> = ArrayList<hduanamesEnt>()
    private  lateinit var  customAdapter: CustomAdapter
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expand_act)
        //     setContentView(R.layout.activity_dua_group);
        val toolbar: Toolbar = findViewById(R.id.my_action_bar)
        // setSupportActionBar(toolbar);
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = getSupportActionBar()
        val shared: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.context)
        assert(actionbar != null)
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        val intent: Intent = getIntent()
        val bundle: Bundle? = intent.getExtras()
        //   String list=   bundle.getString("list");
        val dua_id: Int = bundle!!.getInt("dua_id")
        expandableListView = (findViewById(R.id.expandableListView) as ExpandableListView?)!!
        //  expandableListDetail = ExpandableListDataPump.getData();
        expandableListView = findViewById(R.id.expandableListView)
        //  iv_groupIndicator = findViewById(R.id.iv_groupIndicator);
        val viewmodel: AllDuaModel by viewModels()
        //  hduanamesArrayList = utils.getDunamesbyCatId(String.valueOf(dua_id));
      //  hduanamesArrayList = viewmodel.getDuaCATNAMES(dua_id.toString()) as ArrayList<hduanames>



        viewmodel.Duacatnames(dua_id.toString()).observe(this){ userlist->
            Collections.reverse(userlist)
            hduanamesArrayList= userlist as ArrayList<hduanamesEnt>
            dataheader = ArrayList()
            for (duanamesDetail in hduanamesArrayList) {
                dataheader!!.add(duanamesDetail.duaname)
            }
            subjects = HashMap()
            parentItemsList = ArrayList()
            childItemsList = ArrayList()
            loadDatas()
            displayList()
            // ska.setmutable(userlist)
        }


    }

    override fun onDestroy() {
        //   context = null
        finish()
        super.onDestroy()
    }



    private fun loadDatas() {
        for (hduanames in hduanamesArrayList) {
            addItem(hduanames.chapname!!, hduanames.duaname, hduanames.ID, hduanames.chap_id)
        }
        println("check")
    }

    private fun addItem(parentItemName: String, childItemName: String, id: String, chapterid: Int) {
        var parentItemName: String? = parentItemName
        val size = parentItemsList!!.size
        val groupPosition: Int
        if (parentItemName!!.isEmpty()) {
            parentItemName = parentItemsList!![size - 1].name
        }
        //check the hash map if the group already exists
        var parentItemObj = subjects!![parentItemName]
        //add the group if doesn't exists
        if (parentItemObj == null) {
            parentItemObj = ParentItem()
            parentItemObj.name = parentItemName
            parentItemObj.chapterid = chapterid
            subjects!![parentItemName] = parentItemObj
            parentItemsList!!.add(parentItemObj)
        }
        //get the children for the group
        childItemsList = parentItemObj.childList
        //size of the children list
        var listSize = childItemsList!!.size
        //add to the counter
        listSize++
        //create a new child and add that to the group
        val childItemObj = ChildItem()
        childItemObj.name = childItemName
        childItemObj.id = id
        childItemsList!!.add(childItemObj)
        parentItemObj.childList = childItemsList
        //find the group position inside the list
        groupPosition = parentItemsList!!.indexOf(parentItemObj)
    }

    private fun displayList() {
        //  loadData();
        customAdapter = CustomAdapter(this, parentItemsList)
        expandableListView.setAdapter(customAdapter)
        expandableListView.setOnGroupClickListener(ExpandableListView.OnGroupClickListener { parent: ExpandableListView?, v: View?, groupPosition: Int, id: Long ->
            previousGroup = if (expandableListView.isGroupExpanded(groupPosition)) {
                expandableListView.collapseGroup(groupPosition)
                -1
            } else {
                expandableListView.expandGroup(groupPosition)
                if (previousGroup != -1) {
                    expandableListView.collapseGroup(previousGroup)
                }
                groupPosition
            }
            true
        })
        expandableListView.setOnGroupExpandListener(ExpandableListView.OnGroupExpandListener { groupPosition: Int ->
            /* Auto Scrolling */expandableListView.setSelectedGroup(groupPosition)
        })
        expandableListView.setOnChildClickListener(ExpandableListView.OnChildClickListener { parent: ExpandableListView?, v: View?, groupPosition: Int, childPosition: Int, id: Long ->
            val chap_id = parentItemsList!![groupPosition].chapterid
            Objects.requireNonNull(getSupportActionBar())?.hide()
            val bundle1 = Bundle()
            bundle1.putInt("chap_id", chap_id)
            bundle1.putBoolean("cattwo", false)
            val fragvsi: Fragment = HDuaNamesfrag.newInstance()
            fragvsi.arguments = bundle1
            val transaction: FragmentTransaction = getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.replace(R.id.frame_container, fragvsi, "items")
            //     transaction.addToBackStack("setting");
            transaction.addToBackStack("items")
            transaction.commit()
            false
        })
    }

    override fun onResume() {
        super.onResume()
        Objects.requireNonNull(getSupportActionBar())?.show()
    }

    /*
    @Override
        public void onBackPressed() {
            super.onBackPressed();

            getSupportActionBar().show();

        }

   */
    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_search, menu)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView = menu.findItem(R.id.search).actionView as SearchView?
        searchView = menu.findItem(R.id.search).actionView as SearchView?
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()))
        searchView.queryHint = "Type something…"


        val sear = ContextCompat.getDrawable(this, R.drawable.custom_search_box)
        searchView.clipToOutline = true
        searchView.setBackgroundDrawable(sear)
        searchView.gravity = View.TEXT_ALIGNMENT_CENTER
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
        searchView.requestFocus()
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        /*
        if (id == R.id.backup) {
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                //additional code
            } else {
                getSupportFragmentManager().popBackStack();
            }

        }

 */return super.onOptionsItemSelected(item)
    }

    override fun onClose(): Boolean {
        customAdapter!!.filterData("")
        //  expandAll();
        return false
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        customAdapter!!.filterData(query)
        expandAll()
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        customAdapter!!.filterData(newText)
        expandAll()
        return false
    }

    private fun expandAll() {
        val count = customAdapter!!.groupCount
        for (i in 0 until count) {
            expandableListView.expandGroup(i)
        }
    }
}