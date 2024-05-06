package com.vedantjha.browsemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.vedantjha.browsemovies.data.repository.MovieRetrofitRepository
import com.vedantjha.browsemovies.viewmodel.MovieViewModel
import com.vedantjha.browsemovies.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var movieRetrofitRepository: MovieRetrofitRepository

    private val moviewViewModel: MovieViewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(movieRetrofitRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        moviewViewModel.moviesListLiveData.observe(this, Observer {
            Log.d("MovieData", it.toString())
            Toast.makeText(applicationContext, "Updated data: " + it.toString(), Toast.LENGTH_SHORT)
                .show()
        })


    }
}