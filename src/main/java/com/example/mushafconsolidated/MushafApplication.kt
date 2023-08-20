package com.example.mushafconsolidatedimport

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate














 





class MushafApplication constructor() : Application() {
    fun setAppContext(mAppContext: Context) {
        MushafApplication.Companion.appContext = mAppContext
    }

    public override fun onCreate() {
        super.onCreate()
        MushafApplication.Companion.instance = this
        setAppContext(getApplicationContext())
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        var instance: MushafApplication? = null
            get() {
                return MushafApplication.Companion.instance
            }
        var appContext: Context? = null
            get() {
                return MushafApplication.Companion.appContext
            }

        init {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}