package com.vedantjha.browsemovies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vedantjha.browsemovies.data.model.Movie
import com.vedantjha.browsemovies.data.repository.MovieRetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRetrofitRepository: MovieRetrofitRepository
) : ViewModel() {
    private val _moviesLiveData = MutableLiveData<List<Movie>>()

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val newItem = withContext(Dispatchers.IO) {
                    movieRetrofitRepository.fetchMovies()
                }
                _moviesLiveData.postValue(newItem.body()?.results)
                Log.e("MovieData", " Data: "+ newItem.toString())
            } catch (e: Exception) {
                Log.e("MovieData", "Error fetching details: ${e.message}")
            }
        }
    }


}

class MovieViewModelFactory(private val repository: MovieRetrofitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }
}