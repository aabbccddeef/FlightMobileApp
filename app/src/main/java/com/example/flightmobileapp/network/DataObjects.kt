package com.example.flightmobileapp.network

import com.squareup.moshi.Json

data class MoviesList(
    val results: List<NetworkMovie>
)

data class NetworkMovie(
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    val title: String,
    val overview: String
)

data class Command(
    val aileron: Double,
    val rudder: Double,
    val elevator: Double,
    val throttle: Double
)