package com.vedantjha.browsemovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vedantjha.browsemovies.data.model.Movie
import com.vedantjha.browsemovies.data.repository.MovieRetrofitRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MoviePagingViewModel @Inject constructor(val repository: MovieRetrofitRepository): ViewModel() {
    val moviesList: LiveData<PagingData<Movie>> = repository.getMoviePageData().cachedIn(viewModelScope)

}

class MoviePagingViewModelFactory(private val repository: MovieRetrofitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviePagingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviePagingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }
}