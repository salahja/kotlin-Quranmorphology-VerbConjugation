package com.example.mushafconsolidated.ajroomiya

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.OnUnhandledKeyEventListenerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.ajroomiya.placeholder.AjroomiyaRulecontents
import com.example.mushafconsolidated.databinding.FragmentAjroomiyaListBinding
import com.example.mushafconsolidated.databinding.GrammarruleListContentBinding

/**
 * A fragment representing a list of GrammarRules. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a [AjroomiyaDetailFragment] representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class AjroomiyaListFragment : Fragment() {
    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    var unhandledKeyEventListenerCompat =
        OnUnhandledKeyEventListenerCompat { v: View, event: KeyEvent ->
            if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                return@OnUnhandledKeyEventListenerCompat true
            } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                return@OnUnhandledKeyEventListenerCompat true
            }
            false
        }
    private var binding: FragmentAjroomiyaListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAjroomiyaListBinding.inflate(inflater, container, false)
        return binding!!.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)
        val recyclerView: RecyclerView = binding!!.ajroomiyaList
        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        //  View itemDetailFragmentContainer = view.findViewById(R.id.grammarrule_detail_nav_container);
        val itemDetailFragmentContainer =
            view.findViewById<View>(R.id.ajroomiya_detail_nav_container)

        /* Click Listener to trigger navigation based on if you have
         * a single pane layout or two pane layout
         */
        val onClickListener = View.OnClickListener { itemView: View ->
            val item = itemView.tag as GrammarRules
            val arguments = Bundle()
            arguments.putString(AjroomiyaDetailFragment.ARG_ITEM_ID, item.id.toString())
            //     if (itemDetailFragmentContainer != null) {
            //      Navigation.findNavController(itemDetailFragmentContainer)
            //            .navigate(R.id.fragment_grammarrule_detail, arguments);
            //   } else {
            findNavController(itemView).navigate(R.id.ajroomiya_detail_fragment, arguments)
        }

        /*
         * Context click listener to handle Right click events
         * from mice and trackpad input to provide a more native
         * experience on larger screen devices
         */
        val onContextClickListener = View.OnContextClickListener { itemView: View ->
            val item =
                itemView.tag as AjroomiyaRulecontents.PlaceholderItem
            Toast.makeText(
                itemView.context,
                "Context click of item " + item.id,
                Toast.LENGTH_LONG
            ).show()
            true
        }
        setupRecyclerView(recyclerView, onClickListener, onContextClickListener)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
        onContextClickListener: View.OnContextClickListener,
    ) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            AjroomiyaRulecontents.ITEMS,
            onClickListener,
            onContextClickListener
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private class SimpleItemRecyclerViewAdapter internal constructor(
        private val mValues: List<GrammarRules?>,
        private val mOnClickListener: View.OnClickListener,
        private val mOnContextClickListener: View.OnContextClickListener,
    ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding: GrammarruleListContentBinding = GrammarruleListContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val id = mValues[position]!!.id
            val s = id.toString()
            holder.mIdView.text = s
            //   holder.mContentView.setText(mValues.get(position).getHarf());
            holder.mContentView.text = mValues[position]!!.worddetails
            //    holder.mContentView.setText(HtmlCompat.fromHtml(mValues.get(position).getWorddetails() ,0));
            holder.itemView.tag = mValues[position]
            holder.itemView.setOnClickListener(mOnClickListener)
            holder.itemView.setOnContextClickListener(mOnContextClickListener)
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        internal inner class ViewHolder(binding: GrammarruleListContentBinding) :
            RecyclerView.ViewHolder(binding.getRoot()) {
            val mIdView: TextView
            val mContentView: TextView

            init {
                mIdView = binding.idText
                mContentView = binding.content
            }
        }
    }
}