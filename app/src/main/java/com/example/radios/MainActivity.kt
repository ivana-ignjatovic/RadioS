package com.example.radios

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(LogInFragment(),true)

    }
    fun showFragment(fragment: Fragment, addAsRoot:Boolean= false){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Constraint_Layoutt, fragment)
        if(!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }
}