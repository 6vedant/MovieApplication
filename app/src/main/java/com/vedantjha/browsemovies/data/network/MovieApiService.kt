package com.vedantjha.browsemovies.data.network

import com.vedantjha.browsemovies.data.model.MovieResponse
import com.vedantjha.browsemovies.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("now_playing")
    suspend fun fetchMovies(
        @Query("api_key") api_key: String = Constant.ACCESS_TOKEN,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>


}