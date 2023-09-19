package com.example.mushafconsolidated.ajroomiya

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.databinding.NewActivityAjroomiyaDetailBinding

class NewAjroomiyaDetailHostActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = NewActivityAjroomiyaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.new_activity_ajroomiya_detail)
        //     setContentView(R.layout.activity_dua_group);
        val toolbar: Toolbar = findViewById(R.id.toolbarmain)
        // setSupportActionBar(toolbar);
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val bundle1 = Bundle()
        bundle1.putInt("chap_id", 1)
        bundle1.putBoolean("cattwo", false)
        val fragvsi: Fragment = newAjroomiyaListFragment.newInstance()
        fragvsi.arguments = bundle1
        val transaction: FragmentTransaction = getSupportFragmentManager().beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.frame_container, fragvsi, "items")
        //     transaction.addToBackStack("setting");
        transaction.addToBackStack("items")
        transaction.commit()





    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_ajroomiya_detail)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}