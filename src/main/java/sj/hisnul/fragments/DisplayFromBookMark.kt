package sj.hisnul.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.google.android.material.appbar.MaterialToolbar
import sj.hisnul.adapter.SelectedDuaViewAdapter
import sj.hisnul.entity.hduadetailsEnt
import java.util.Collections

class DisplayFromBookMark : Fragment() {
    val subheaders = ArrayList<String>()
    val duacoll: ArrayList<ArrayList<hduadetailsEnt>> = ArrayList()
     lateinit var  sadapter: SelectedDuaViewAdapter

    //called by allduarag and  catwofrag retrival by the chaptername in hdunames
     lateinit var  recyclerView: RecyclerView
    private  lateinit var  lists: String
    private  lateinit var  name: String
    private   var  fromcatwo = false
    private var chap_id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (arguments != null) {
            name = requireArguments().getString("name").toString()
            chap_id = requireArguments().getInt("chap_id")
            fromcatwo = requireArguments().getBoolean("cattwp")
        }
        val utils = Utils(activity)
        if (chap_id != -1) {
            val viewmodel:AllDuaModel by viewModels()
            viewmodel.Duadetailsbychapter(chap_id).observe(this){
                // val dd: ArrayList<hduanames> = utils.getdualistbychapter(chap_id) as ArrayList<hduanames>
                for (hduanames in it) {
                    val duaItems: ArrayList<hduadetailsEnt> = utils.gethDuadetailsitems(hduanames.ID) as ArrayList<hduadetailsEnt>
                    duacoll.add(duaItems)
                    subheaders.add(hduanames.duaname)
                }
            }


        } else {
            Toast.makeText(activity, "Chapter Id not Found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.dunamefragview, container, false)
        val viewmodel:AllDuaModel by viewModels()
        setHasOptionsMenu(true)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarmain)
        val actionBa = (activity as AppCompatActivity?)!!.actionBar
        //  ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        actionBa?.setDisplayHomeAsUpEnabled(true)
        recyclerView = view.findViewById<RecyclerView>(R.id.dunamerec)
        val utils = Utils(context)
        toolbar.title = name
        toolbar.inflateMenu(R.menu.menu_bookmark)
        toolbar.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.bookmark -> {

                  //  val dunamesbyid: List<hduanames> =utils.getdualistbychapter(chap_id)

                    viewmodel.Duadetailsbychapter(chap_id).observe(viewLifecycleOwner){ userlist->
                        Collections.reverse(userlist)


                        val gookstat = userlist?.get(0)?.fav
                        if (gookstat == 0) {
                            viewmodel.update(1,chap_id)
                            //  val up = utils.updateFav(1, chap_id)!!

                        } else {
                            //   val upd = utils.updateFav(0, chap_id)!!
                            viewmodel.update(0,chap_id)
                        }
                    }
                  //


                    return@setOnMenuItemClickListener true
                }

                R.id.backup -> {
                    val count =
                        requireActivity().supportFragmentManager.backStackEntryCount
                    if (count == 0) {
                        val supportActionBar =
                            (activity as AppCompatActivity?)!!.supportActionBar
                        //additional code
                    } else {
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                    return@setOnMenuItemClickListener true
                }

                R.id.action_share -> {
                    Toast.makeText(context, "First share item", Toast.LENGTH_SHORT).show()
                    //       setuptoolbar();
                    return@setOnMenuItemClickListener true
                }
            }
            Toast.makeText(activity, "tool", Toast.LENGTH_SHORT).show()
            false
        }
        if (chap_id != -1) {
            sadapter = SelectedDuaViewAdapter(duacoll, context, name, subheaders)
            recyclerView.setAdapter(sadapter)
        }
        //AconSarfSagheerAdapter sk=new AconSarfSagheerAdapter(ar, MainActivity.this);
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(layoutManager)
        //  recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view
    }


    private fun RefreshActivity() {
        Log.e(TAG, "onClick called")
        val intent: Intent = requireActivity().intent
        val parentActivityRef: String? = intent.getStringExtra("PARENT_ACTIVITY_REF")
        intent.putExtra("tabposition", 1)
        requireActivity().overridePendingTransition(0, 0)
        startActivity(intent)
        requireActivity().finish()

    }

    companion object {
        private const val TAG = "PermissionDemo"
        fun newInstance(): DisplayFromBookMark {
            return DisplayFromBookMark()
        }
    }
}