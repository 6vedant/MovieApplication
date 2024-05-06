package com.vedantjha.browsemovies.data.network

import com.vedantjha.browsemovies.data.model.MovieDetails
import com.vedantjha.browsemovies.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("now_playing")
    suspend fun fetchMovies(): Response<MovieResponse>

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetails>
}