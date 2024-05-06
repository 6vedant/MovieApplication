package com.vedantjha.browsemovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vedantjha.browsemovies.data.repository.MovieRetrofitRepository
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRetrofitRepository: MovieRetrofitRepository
) : ViewModel() {
    private val _moviesLiveData = MutableLiveData<Movie>
}