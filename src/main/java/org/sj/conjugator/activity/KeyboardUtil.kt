package org.sj.conjugator.activity

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtil {
    /**
     * @param activity
     */
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        view.clearFocus()
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * @param activity the activity instance
     */
    fun showKeyboard(activity: Activity) {
        var view = activity.currentFocus
        if (view == null) view = View(activity)
        val context = view.context

        val mgr = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }
}