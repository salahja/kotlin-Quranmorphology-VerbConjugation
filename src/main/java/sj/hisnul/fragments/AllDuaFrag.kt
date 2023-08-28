package sj.hisnul.fragments

import android.app.SearchManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R

import com.example.mushafconsolidated.Utils.Utils
import com.google.android.material.appbar.MaterialToolbar
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.adapter.CatAllAdapter
import sj.hisnul.entity.hduanames

class AllDuaFrag : Fragment(), SearchView.OnQueryTextListener {
     lateinit var  ska: CatAllAdapter
     lateinit var  recyclerView: RecyclerView
    private  lateinit var  searchView: SearchView
    private  lateinit var  queryTextListener: SearchView.OnQueryTextListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.activity_dua_group, container, false)
        //   View view = inflater.inflate(R.layout.rwz, container, falser
        recyclerView = view.findViewById<RecyclerView>(R.id.duaListView)
        var utils=Utils(context)
        val duall: ArrayList<hduanames> = utils.getAllList() as ArrayList<hduanames>
        ska = CatAllAdapter(duall, requireContext())

        setHasOptionsMenu(true)
        val toolbar: MaterialToolbar = view.findViewById<MaterialToolbar>(R.id.my_action_bar)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        recyclerView.setAdapter(ska)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(context))

        ska.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                //    ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
                //   hduanames hduanames = duall.get(position);
                val hduanames: hduanames = ska.getItem(position) as hduanames
                val did: String = hduanames.ID
                val chap_id: Int = hduanames.chap_id
                val bundle1 = Bundle()
                bundle1.putString("name", hduanames.chapname)
                bundle1.putInt("chap_id", chap_id)
                //  bundle.putString("ref",ref);
                val fragvsi: Fragment = HDuaNamesfrag.newInstance()
                fragvsi.arguments = bundle1
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.replace(R.id.frame_container, fragvsi, "items")
                //     transaction.addToBackStack("setting");
                transaction.addToBackStack("items")
                transaction.commit()
            }
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchManager: SearchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (searchItem != null) {
            searchView = (searchItem.actionView as SearchView?)!!
            val sear: Drawable = ContextCompat.getDrawable(requireContext(), R.drawable.custom_search_box)!!
            searchView!!.clipToOutline = true
            searchView!!.setBackgroundDrawable(sear)
            searchView!!.gravity = View.TEXT_ALIGNMENT_CENTER
            searchView!!.maxWidth = Int.MAX_VALUE
        }
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    //   Log.i("onQueryTextChange", newText);
                    ska.getFilter().filter(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    //    Log.i("onQueryTextSubmit", query);
                    ska.getFilter().filter(query)
                    return false
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) { // Not implemented here
            return false
        }
        searchView!!.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        ska.getFilter().filter(query)
        //  Utils.LogDebug("Submitted: "+query);
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        //    Utils.LogDebug("Changed: "+newText);
        return false
    }

    companion object {
        fun newInstance(): AllDuaFrag {
            return AllDuaFrag()
        }
    }
}