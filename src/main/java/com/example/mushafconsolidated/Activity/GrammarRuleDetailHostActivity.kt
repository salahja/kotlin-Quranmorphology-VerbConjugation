package com.example.mushafconsolidated.Activityimport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController

import com.example.mushafconsolidated.Activity.QuranGrammarAct
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.databinding.ActivityGrammarruleDetailBinding


class GrammarRuleDetailHostActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGrammarruleDetailBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root!!)
        val navHostFragment = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_grammarrule_detail) as NavHostFragment?)!!
        val navController = navHostFragment.navController
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(navController.graph)
            .build()
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupWithNavController(toolbar, navController)
    }

    override fun onBackPressed() {
        val `in` = Intent(this, QuranGrammarAct::class.java)
        startActivity(`in`)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment_grammarrule_detail)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}