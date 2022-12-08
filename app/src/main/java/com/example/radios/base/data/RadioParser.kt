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
                val id = jsonRadio.getString("stationuuid")
                val name = jsonRadio.getString("name")
                val country = jsonRadio.getString("country")
                val favicon = jsonRadio.getString("favicon")
                val stream = jsonRadio.getString("url")
                val state = jsonRadio.getString("state")
                val language = jsonRadio.getString("language")
                val languagecode = jsonRadio.getString("languagecodes")
                val countrycode = jsonRadio.getString("countrycode")
                val votes = jsonRadio.getInt("votes")
                val homepage = jsonRadio.getString("homepage")
                val radio = Radio(id,name,country,favicon,stream,state,language,languagecode,countrycode,votes,homepage)
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
                val id = jsonRadio.getString("stationuuid")
                val name = jsonRadio.getString("name")
                val country = jsonRadio.getString("country")
                val favicon = jsonRadio.getString("favicon")
                val stream = jsonRadio.getString("url")
                val state = jsonRadio.getString("state")
                val language = jsonRadio.getString("language")
                val languagecode = jsonRadio.getString("languagecodes")
                val countrycode = jsonRadio.getString("countrycode")
                val votes = jsonRadio.getInt("votes")
                val homepage = jsonRadio.getString("homepage")
                radio = Radio(id,name,country,favicon,stream,state,language,languagecode,countrycode,votes,homepage)


        }catch(e:JSONException){
            Log.e(TAG, e.toString())
        }

        return radio

    }

}