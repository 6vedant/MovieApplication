package com.vedantjha.browsemovies.data.repository

import com.vedantjha.browsemovies.data.network.MovieApiService
import javax.inject.Inject

class MovieRetrofitRepository @Inject constructor(
    private val apiService: MovieApiService
) {
    suspend fun fetchMovies() = apiService.fetchMovies()

    suspend fun fetchMovieDetails(movieId: String) = apiService.getMovieDetails(movieId)
}