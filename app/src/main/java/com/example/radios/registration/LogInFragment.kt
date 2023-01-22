package com.example.radios.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.radios.R
import com.example.radios.base.DBHelper
import com.example.radios.base.MainActivity
import com.example.radios.databinding.ActivityMainBinding


class LogInFragment : Fragment() {
    object username {
        var un = ""
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db: DBHelper = DBHelper(requireContext())
        val mydb = db.writableDatabase

        val signInBtn: Button = view.findViewById(R.id.buttonSignIn)
        signInBtn.setOnClickListener() {
            (activity as MainActivity).showFragment(SignUpFragment(), false)
        }
        val btn_login = view.findViewById<Button>(R.id.btnlogin)
        val tv_username = view.findViewById<EditText>(R.id.enterUsername)
        val tv_password = view.findViewById<EditText>(R.id.enterPassword)


        btn_login.setOnClickListener() {
            val user = db.getUserById(tv_username.text.toString())
            Log.d("Log user", user.toString())
            if(tv_username.text.toString()=="" || tv_password.text.toString()==""){
                Toast.makeText(
                    requireContext(),
                    "Morate uneti sva polja",
                    Toast.LENGTH_LONG).show()
            }
           else if(tv_username.text.toString()=="" && tv_password.text.toString()==""){
                Toast.makeText(
                    requireContext(),
                    "Morate uneti sva polja",
                    Toast.LENGTH_LONG).show()
            }
            else if (user.username == tv_username.text.toString() && user.password == tv_password.text.toString()) {
                username.un = user.username
                db.close()
                binding = ActivityMainBinding.inflate(layoutInflater)
                (activity as MainActivity).setContentView(binding.root)
                navController = Navigation.findNavController(
                    activity as MainActivity,
                    R.id.activity_main_nav_host_fragment
                )
                NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

            }
            else {
                Toast.makeText(
                    requireContext(),
                    "Doslo je do greske prilikom unosa!",
                    Toast.LENGTH_LONG
                ).show()
                db.close()
            }
        }
    }


}
