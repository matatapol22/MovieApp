package com.bignerdranch.android.movieapp.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?apikey=YOUR_API_KEY")
    suspend fun searchMovies(@Query("s") title: String): MovieResponse
}

data class MovieResponse(
    val Search: List<MovieItem>
)

data class MovieItem(
    val Title: String,
    val Year: String,
    val Poster: String
)