package com.example.radios.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.radios.R
import com.example.radios.base.DBHelper
import com.example.radios.base.MainActivity
import com.example.radios.base.model.MUser

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logInBtn: Button = view.findViewById(R.id.btnlogin)
        logInBtn.setOnClickListener() {
            //val db = DBHelper(context!!, null)
           // db.deleteALLUsers()
            (activity as MainActivity).showFragment(LogInFragment(), false)

        }
        val btn_create = view.findViewById<Button>(R.id.btncreate)
        val tv_name = view.findViewById<TextView>(R.id.enterName)
        val tv_surname = view.findViewById<TextView>(R.id.enterSurname)
        val tv_email = view.findViewById<TextView>(R.id.enterEmail)
        val tv_username = view.findViewById<TextView>(R.id.enterUsername)
        val tv_password = view.findViewById<TextView>(R.id.enterPassword)

        btn_create.setOnClickListener(){


            val username = tv_username.text.toString()
            val name = tv_name.text.toString()
            val surname = tv_surname.text.toString()
            val email = tv_email.text.toString()
            val password = tv_password.text.toString()

          //  val radioId = ""
            val user : MUser = MUser(username,name,surname,email,password)
            val db = DBHelper(requireContext())
            signUpUser(user,db)
        }

    }

    fun signUpUser(user : MUser ,db:DBHelper){
        val searchUser = db.getUserById(user.username)
        if(searchUser.username=="")
        {
            val user = MUser(user.username,user.name,user.surname,user.email,user.password)

            val status = db.insertUser(user)
            if(status >-1)
            {
                Toast.makeText(requireContext(), "Korisnik " + user.name +" " + user.surname + " je dodat u bazu podataka", Toast.LENGTH_LONG).show()
                (activity as MainActivity).showFragment(LogInFragment())
            }else{
                Toast.makeText(requireContext(), "Doslo je do greske prilikom unosa!", Toast.LENGTH_LONG).show()
            }
        }
        else throw Exception("Vec postoji korisnik sa unetim korisnickiim imenom!")
    }



}
