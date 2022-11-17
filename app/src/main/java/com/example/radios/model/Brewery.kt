package com.example.radios.model

data class OpeningTime(
    val day: Int,
    val open: String?,
    val close: String?
)

data class Service(val name: String)

data class Address(
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

data class Brewery(
    val id: Int,
    val image: String,
    val name: String,
    val address: Address,
    val description: String? = null,
    val openingTimes: List<OpeningTime>? = null,
    val services: List<Service>? = null,
    val latLong: LatLng? = null,
    val reviews: List<Review>? = null
)

