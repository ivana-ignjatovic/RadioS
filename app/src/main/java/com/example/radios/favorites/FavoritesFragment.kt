package com.example.radios.favorites

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.radios.R
import com.example.radios.base.DBHelper
import com.example.radios.base.ICoordinator
import com.example.radios.base.MainActivity
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.Radio
import com.example.radios.base.model.RadiosUsers
import com.example.radios.fragments.LogInFragment
import com.example.radios.radiosdetails.view.RadiosDetails
import com.example.radios.radioslist.recycler.RadioRVAdapter
import com.example.radios.radioslist.view.RadioListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.item_radio.*
import kotlinx.android.synthetic.main.item_radio.view.*

class FavoritesFragment : Fragment() {
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavorites()


    }

    fun setupRecycleView(radios: List<Radio>){

        favoriteslist.adapter= RadioRVAdapter(radios, this,true){radio ->
            (activity as ICoordinator).showDetailsFragment(radio)
            //(activity as MainActivity).refreshFragment(this.context,FavoritesFragment())
        }
        (favoriteslist.adapter as RadioRVAdapter).notifyDataSetChanged()
    //    btnSave.setBackgroundResource(R.drawable.ic_star)

    }

     fun getFavorites() {
        val db = DBHelper(requireContext())
        var favbyyser: List<RadiosUsers> = db.getALLFavorites()
       favbyyser = favbyyser.filter { it.username == LogInFragment.username.un }

        val thread = Thread(){
            val response = HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/bycountrycodeexact/RS")
            if (response is Result.Success){
                Log.d("response", response.data)
                var radios = RadioParser.parse(response.data)
               // lateinit var favorites: List <Radio>
                    val favId:List<String> = favbyyser.map { it.radios}
                   radios= radios.filter { favId.contains(it.id)}

                //Log.d("IsEmpty",favorites.toString())
                activity?.runOnUiThread(){
                    setupRecycleView(radios)
                }

                //Log.d("UserLog","Radios receivde ${radios}")
            }

        }
        thread.start()
    }


    }
