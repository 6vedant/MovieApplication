package com.vedantjha.browsemovies.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.vedantjha.browsemovies.data.model.Movie
import com.vedantjha.browsemovies.data.network.MovieApiService
import com.vedantjha.browsemovies.paging.MoviePagingSource
import javax.inject.Inject

class MovieRetrofitRepository @Inject constructor(
    private val apiService: MovieApiService
) {
    suspend fun fetchMovies() = apiService.fetchMovies()

     fun getMoviePageData(): LiveData<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 200),
        pagingSourceFactory = {MoviePagingSource(apiService)}
    ).liveData
}