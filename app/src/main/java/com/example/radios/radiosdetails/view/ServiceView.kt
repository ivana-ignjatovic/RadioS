package com.example.radios.radiosdetails.view
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.example.radios.R
import com.example.radios.base.model.Radio
import kotlinx.android.synthetic.main.fragment_radios_details.view.*

class ServiceView (context: Context): LinearLayout(context)  {
    private val view = View.inflate(context, R.layout.item_radio, this)

    fun bind(radio: Radio){
        view.name.text=radio.name
        view.country.text=radio.country
//        view.countrycode.text=radio.country_code
//        view.language2.text=radio.language
//        view.languagecode2.text=radio.language_code
//        view.state2.text=radio.state
//        view.votes2.text=radio.votes.toString()
    }
}