package com.example.radios.base.data

import android.util.Log
import com.example.radios.base.model.Radio
import org.json.JSONArray
import org.json.JSONException

object RadioParser {
    private const val TAG = "RadiosParser"
    fun parse(json :String): List<Radio>{

        val radios = mutableListOf<Radio>()
        try{


            val radiosJsonArray = JSONArray(json)

            for (i in 0 until radiosJsonArray.length()){
                val jsonRadio = radiosJsonArray.getJSONObject(i)
                Log.d("Json tag",jsonRadio.toString())
               // val id = jsonRadio.getString("stationuuid")
                val id = jsonRadio.getString("changeuuid")
                val name = jsonRadio.getString("name")
                val country = jsonRadio.getString("country")
                val favicon = jsonRadio.getString("favicon")
                val stream = jsonRadio.getString("url")
                val radio = Radio(id,name,country,stream)
                radios.add(radio)
            }
        }catch(e:JSONException){
            Log.e(TAG, e.toString())
        }

        return radios
    }
    fun parse2(json :String): Radio{

        lateinit var radio:Radio
        try{

            val radiosDJsonArray = JSONArray(json)


                val jsonRadio = radiosDJsonArray.getJSONObject(0)
                Log.d("Json tag",jsonRadio.toString())

                val name = jsonRadio.getString("name")
                val favicon = jsonRadio.getString("favicon")
                val country = jsonRadio.getString("country")
                val country_code = jsonRadio.getString("countrycode")
                val state = jsonRadio.getString("state")
                val language = jsonRadio.getString("language")
                val language_code = jsonRadio.getString("languagecodes")
                val votes = jsonRadio.getInt("votes")
//                val radio = RadioD(name,favicon,country,country_code,state,language,language_code,votes)
            radio = Radio("id",name,country, "stream")


        }catch(e:JSONException){
            Log.e(TAG, e.toString())
        }

        return radio
    }

}