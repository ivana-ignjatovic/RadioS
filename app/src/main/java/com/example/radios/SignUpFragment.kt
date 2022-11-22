package com.example.radios

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

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
        val tv_country = view.findViewById<TextView>(R.id.enterCountry)
        btn_create.setOnClickListener(){
            val db = DBHelper(context!!, null)
            val username = tv_username.text.toString()
            val name = tv_name.text.toString()
            val surname = tv_surname.text.toString()
            val email = tv_email.text.toString()
            val password = tv_password.text.toString()
            val country = tv_country.text.toString()

            val user = MUser(username,name,surname,email,password,country)
            val status = db.insertUser(user)
            if(status >-1)
            {
                Toast.makeText(context!!, "Korisnik " + name +" " + surname + " je dodat u bazu podataka", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context!!, "Doslo je do greske prilikom unosa!", Toast.LENGTH_LONG).show()
            }
        }

    }



}
