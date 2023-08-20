package com.example.mushafconsolidatedimport

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mushafconsolidated.R
import com.google.android.material.button.MaterialButton

 
 
 
 
 
 
 
 
 
 
  
 
 
 
 
 
 

class q_SearchView constructor(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val open_search_button: View? = null
    private val close_search_button: View
    private val search_input_text: TextView? = null
    private val search_open_view: LinearLayout
    private val searchImf: ImageView
    private val etSearch: EditText
    var btnSelectBookText: MaterialButton
    var cbPartialMatch: CheckBox

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.q_search_view, this, true)
        searchImf = getRootView().findViewById(R.id.ibApply)
        etSearch = getRootView().findViewById(R.id.etSearch)
        btnSelectBookText = getRootView().findViewById(R.id.btnSelectBookText)
        cbPartialMatch = getRootView().findViewById(R.id.cbPartialMatch)
        close_search_button = getRootView().findViewById(R.id.llSearchBar)
        search_open_view = getRootView().findViewById(R.id.search_open_view)
        /*    open_search_button = getRootView().findViewById(R.id.open_search_button);
        close_search_button = getRootView().findViewById(R.id.close_search_button);
        search_input_text=getRootView().findViewById(R.id.search_input_text);
        search_open_view=getRootView().findViewById(R.id.search_open_view);*/searchImf.setOnClickListener(
            object : OnClickListener {
                public override fun onClick(v: View?) {
                    openSearch()
                }
            })
        close_search_button.setOnClickListener(object : OnClickListener {
            public override fun onClick(v: View?) {
                closeSearch()
            }
        })
    }

    private fun closeSearch() {
        val cx: Int = search_open_view.getWidth() / 2
        val cy: Int = search_open_view.getHeight() / 2

        // get the initial radius for the clipping circle
        val initialRadius: Float = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

        // create the animation (the final radius is zero)
        val anim: Animator = ViewAnimationUtils.createCircularReveal(
            search_open_view, cx, cy, initialRadius, 0f
        )

        // make the view invisible when the animation is done
        anim.addListener(object : AnimatorListenerAdapter() {
        })

        // start the animation
        anim.start()
        /* Animator circularConceal = ViewAnimationUtils.createCircularReveal(
                search_open_view,
                (open_search_button.getRight() + open_search_button.getLeft()) / 2,
                (open_search_button.getTop() + open_search_button.getBottom()) / 2,
                0f, open_search_button.getWidth() );
        circularConceal.setDuration(300)  ;
        circularConceal.start();
        circularConceal.addListener(new Animator.AnimatorListener() {

         //   override fun onAnimationRepeat(animation: Animator?) = Unit
         //   override fun onAnimationCancel(animation: Animator?) = Unit
         //   override fun onAnimationStart(animation: Animator?) = Unit
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                search_open_view.setVisibility(INVISIBLE);
                search_input_text.setText("");

                circularConceal.removeAllListeners();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Animator Unit;
            //    Animator animator=Unit;

            }
        });*/
    }

    private fun openSearch() {
        etSearch.setText("")
        searchImf.setVisibility(VISIBLE)
        etSearch.setVisibility(VISIBLE)
        btnSelectBookText.setVisibility(VISIBLE)
        cbPartialMatch.setVisibility(VISIBLE)
        val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(
            search_open_view,
            (open_search_button!!.getRight() + open_search_button.getLeft()) / 2,
            (open_search_button.getTop() + open_search_button.getBottom()) / 2,
            0f, open_search_button.getWidth().toFloat()
        )
        circularReveal.setDuration(300)
        circularReveal.start()
    }
}