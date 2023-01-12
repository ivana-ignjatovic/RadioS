package com.example.radios.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.radios.R
import com.example.radios.databinding.ActivityMainBinding
import com.example.radios.fragments.LogInFragment
import com.example.radios.radiosdetails.view.RadiosDetails
import com.example.radios.radioslist.view.RadioListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_radio_list.view.*


class MainActivity : AppCompatActivity(), ICoordinator {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        showFragment(LogInFragment(),true)
        bottomNavigationView.visibility = View.GONE

    }

    fun showFragment(fragment: Fragment, addAsRoot:Boolean= false){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Constraint_Layout, fragment)
        if(!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun showDetailsFragment(radioId: String) {
        // showFragment(RadiosDetails.newInstance(radioId))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_nav_host_fragment, RadiosDetails.newInstance(radioId))
        transaction.addToBackStack(null)
        transaction.commit()

    }

}