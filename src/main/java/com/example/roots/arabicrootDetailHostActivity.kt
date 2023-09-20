package com.example.roots

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import arabicrootListFragment
import com.example.Constant.WORDDETAILS
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import com.google.android.material.appbar.MaterialToolbar

class arabicrootDetailHostActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arabicroot_detail)
        val bundle: Bundle? = intent.extras
        val wordorverb: String? = bundle?.getString(WORDDETAILS)
        val arguments = Bundle()
        arguments.putString(WORDDETAILS, wordorverb)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar_layout)
        toolbar.title = "Root List"
        if (wordorverb == "word") {
            toolbar.title = "Root List(Nouns/Verbs"
        } else {
            toolbar.title = "Verb Root List"
        }
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
        val newCustomFragment = arabicrootListFragment.newInstance(wordorverb)
        newCustomFragment.arguments = bundle
        transaction.replace(R.id.nav_host_fragment_arabicroot_detail, newCustomFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        /*       NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_arabicroot_detail);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.
                Builder(navController.getGraph())
                .build();

        setSupportActionBar(binding.toolbarLayout);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.root_title);*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment_arabicroot_detail)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}