package com.example.radios.model

object MockDataProvider {



    fun getBreweryList(): List<Brewery> {
        val breweries = mutableListOf<Brewery>()

        for (i in 1..20) {
            val brewery = Brewery(
                i,
                "http://www.exceptionalapps.net/androidkurs/brlo.jpg",
                "Radio $i",
                Address("Schöneberger Straße", i, i, "Berlin")
            )
            breweries.add(brewery)
        }
        return breweries
    }

    fun getBreweryDetails(): Brewery {
        val openingTimes = mutableListOf<OpeningTime>()
        val reviews = mutableListOf<Review>()
        val services = mutableListOf<Service>()

        for (i in 1..6) {
            openingTimes.add(OpeningTime(i, "10:00", "22:00"))
            reviews.add(
                Review(
                    "Andreas Teufel",
                    5,
                    "2017-01-15T20:18:40Z",
                    "Vestibulum rutrum quam vitae fringilla tincidunt." +
                        "Suspendisse nec tortor urna. Unsere Wurzeln verstecken sich bereits in unserem.",
                    "Wonderful",
                )
            )
            services.add(Service("Credit card payment"))
        }
        openingTimes.add(OpeningTime(7, null, null))

        return Brewery(
            1,
            "http://www.exceptionalapps.net/androidkurs/brlo.jpg",
            "BRLO BRWHOUSE",
            Address("Schöneberger Straße", 16, 10693, "Berlin"),
            "Willkommen im BRLO BRWHOUSE: Brauerei, Restaurant," +
                " Bar und Biergarten direkt am Gleisdreieck und vor allem das Zuhause von BRLO," +
                " dem Berliner Craft Beer. Unsere Wurzeln verstecken sich bereits in unserem.",
            openingTimes,
            services,
            LatLng(48.12987, 11.569799),
            reviews
        )
    }
}