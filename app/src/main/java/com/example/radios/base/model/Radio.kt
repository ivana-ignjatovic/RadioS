package com.example.radios.base.model

data class OpeningTime(
    val day: Int,
    val open: String?,
    val close: String?
)

data class Service(val name: String)
/*
data class Country(
    val street: String,
    val number: Int,
    val zipCode: Int,
    val city: String
)

data class LatLng(
    val latitude: Double,
    val longitude: Double
)

data class Review(
    val customer: String,
    val rating: Int,
    val date: String,
    val description: String,
    val reviewName: String
)
*/
data class Radio(
    //val id: String,
    val name: String,
    val country: String,
    //val favicon: String,
    val stream: String,
    /*val homepage: String,

    val country: String,
    val state: String,
    val language: String,*/

)

