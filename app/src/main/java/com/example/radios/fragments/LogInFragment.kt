package com.example.radios.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.radios.R
import com.example.radios.base.DBHelper
import com.example.radios.base.MainActivity
import com.example.radios.radioslist.view.RadioListFragment


class LogInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signInBtn: Button = view.findViewById(R.id.buttonSignIn)
        signInBtn.setOnClickListener() {
            (activity as MainActivity).showFragment(SignUpFragment(), false)
        }
        val btn_login = view.findViewById<Button>(R.id.btnlogin)
        val tv_username = view.findViewById<EditText>(R.id.enterUsername)
        val tv_password = view.findViewById<EditText>(R.id.enterPassword)


        btn_login.setOnClickListener() {

            val db = DBHelper(requireContext(), null)
            val user = db.getUserById(tv_username.text.toString())
            Log.d("Log user",user.toString())
            if(user.username.toString()==tv_username.text.toString() && user.password.toString()==tv_password.text.toString())
                {
                    (activity as MainActivity).showFragment(RadioListFragment(), false)

                }
            else{
                    Toast.makeText(requireContext(), "Doslo je do greske prilikom unosa!", Toast.LENGTH_LONG).show()
                }
            }

        }


    }
