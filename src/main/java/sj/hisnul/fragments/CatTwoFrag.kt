package sj.hisnul.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R

import com.example.mushafconsolidated.Utils.Utils
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.activity.NewExpandAct
import sj.hisnul.adapter.CatTwoAdapter
import sj.hisnul.entity.hcategory

class CatTwoFrag : Fragment() {
    lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val view: View = inflater.inflate(R.layout.frag_catwo_drawble, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recview)
        val utils = Utils(requireContext())

        val duagrouptwo: ArrayList<hcategory> = utils.hcategory
        val adapter = CatTwoAdapter(duagrouptwo, requireContext())

        val layoutManager: GridLayoutManager
        layoutManager = GridLayoutManager(activity, 3)

        /*
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
             //   return position == 0 ? 3 : 1;
            }
        });

      */recyclerView.setHasFixedSize(true)
        // recyclerView.setLayoutManager(new LinearLayoutManager(context!!));
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(adapter)
        adapter.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val catTwo: hcategory = duagrouptwo[position]
                if (!catTwo.title.isEmpty()) {
                    val intent = Intent(activity, NewExpandAct::class.java)
                    intent.putExtra("PARENT_ACTIVITY_REF", "ParentActivityIsA")
                    startActivity(intent)
                    val catid: Int = catTwo.id
                    intent.putExtra("dua_id", catid)
                    startActivity(intent)
                }
            }
        })
        return view
    }

    companion object {
        fun newInstance(): CatTwoFrag {
            return CatTwoFrag()
        }
    }
}