package com.example.radios.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.radios.R
import com.example.radios.fragments.LogInFragment
import com.example.radios.radiosdetails.view.RadiosDetails


class MainActivity : AppCompatActivity(), ICoordinator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(LogInFragment(),true)

        val db:DBHelper = DBHelper(this)
        val mydb = db.writableDatabase
    }

    fun showFragment(fragment: Fragment, addAsRoot:Boolean= false){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Constraint_Layoutt, fragment)
        if(!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showDetailsFragment(radioId: String) {
         showFragment(RadiosDetails.newInstance(radioId))
    }
    fun refreshFragment(context: Context?,fragment: Fragment){
        context?.let {
            val fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
            fragmentManager?.let {
                val currentFragment = fragmentManager.findFragmentById(R.id.favoriteslist)
                currentFragment?.let {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.detach(it)
                    fragmentTransaction.attach(it)
                    fragmentTransaction.commit()
                }
            }
        }
    }
}