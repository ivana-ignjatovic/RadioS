package com.example.radios.base.model


data class Radio(
    val id: String,
    val name: String,
    val country: String,
    val favicon: String,
    val stream: String,
    val state: String,
    val language: String,
    val languagecode : String,
    val countrycode: String,
    val votes : Int,
    val homepage: String

)


