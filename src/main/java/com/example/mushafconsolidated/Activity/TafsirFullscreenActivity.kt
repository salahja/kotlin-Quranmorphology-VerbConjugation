package com.example.mushafconsolidated.Activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import com.example.Constant.AYAH_ID
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.google.android.material.appbar.MaterialToolbar

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class TafsirFullscreenActivity : BaseActivity() {
    private val mHideHandler = Handler(Looper.myLooper()!!)
    private val mContentView: View? = null
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar
        if (Build.VERSION.SDK_INT >= 30) {
            mContentView!!.windowInsetsController!!.hide(
                WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
            )
        } else {
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView!!.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }
    private val mControlsView: View? = null

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    //  private ActivityTafsirFullscreenBinding binding;
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tafsir_fullscreen)
        val bundle: Bundle? = getIntent().getExtras()
        val sura = bundle?.getInt(SURAH_ID)
        val ayah = bundle?.getInt(AYAH_ID)
        val surahname = bundle?.getString(SURAH_ARABIC_NAME)
        val utils = Utils(this)
        val materialToolbar: MaterialToolbar = findViewById(R.id.toolbarmain)
        setSupportActionBar(materialToolbar)
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (materialToolbar != null) {
            setSupportActionBar(materialToolbar)
            getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        }
        val viewmodel : QuranVIewModel by viewModels()

        viewmodel.getsurahayahVerses(sura!!, ayah!!).observe(this, Observer {
         //   val list: List<QuranEntity?>? = Utils.getsurahayahVerses(sura!!, ayah!!)
            val actionBar: ActionBar? = getSupportActionBar()
            val sourcelable: TextView = findViewById(R.id.tvSourceLabel)
            val tafsir: TextView = findViewById(R.id.tvTafsir)
            val translation: TextView = findViewById(R.id.tvTranslation)
            val tvaryah: TextView = findViewById(R.id.tvData)
            val button: Button = findViewById(R.id.detailsbutton)
            button.text = "$sura:$ayah $surahname"
            sourcelable.text = "Arabic Ayah"
            val tafsir_kathir = it?.get(0)?.tafsir_kathir
            val tafsir_kathirs = tafsir_kathir?.replace("<b>", "")
            val tafsir_kathissr = tafsir_kathirs?.replace("</b>", "")
            tafsir.text = tafsir_kathissr
            translation.text = it!![0]!!.translation
            tvaryah.text = it[0]!!.qurantext
        })

    }

    override fun onBackPressed() {
        // code here to show dialog
        super.onBackPressed() // optional depending on your needs
        this.finish()
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [.AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [.AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}