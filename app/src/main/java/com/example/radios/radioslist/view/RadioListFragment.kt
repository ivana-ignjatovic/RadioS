package com.example.radios.radioslist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.radios.R
import com.example.radios.base.ICoordinator
import com.example.radios.base.MainActivity
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.Radio
import com.example.radios.databinding.ActivityMainBinding

import com.example.radios.favorites.FavoritesFragment
import com.example.radios.radiosdetails.view.RadiosDetails
import com.example.radios.radioslist.recycler.RadioRVAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_radio_list.*
import kotlinx.android.synthetic.main.fragment_radio_list.view.*
import kotlinx.android.synthetic.main.fragment_radios_details.*
import kotlinx.android.synthetic.main.fragment_radios_details.view.*
import kotlinx.android.synthetic.main.item_radio.*
import kotlinx.android.synthetic.main.item_radio.view.*


open class RadioListFragment : Fragment() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio_list, container, false)
        //itemView.btnDelete.setVisibility(View.GONE)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRadios()

     }

     fun setupRecyclerView(radios: List<Radio>){
        radiolist.adapter= RadioRVAdapter(radios,this,false){radio ->
            (activity as ICoordinator).showDetailsFragment(radio)
        }
    }

     fun getRadios(){

        val thread = Thread(){
            val response = HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/bycountrycodeexact/RS")
            if (response is Result.Success){
                Log.d("response", response.data)
                val radios = RadioParser.parse(response.data)

                activity?.runOnUiThread(){
                    setupRecyclerView(radios)
                }
            }

        }
       thread.start()

    }
}