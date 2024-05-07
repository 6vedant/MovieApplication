package com.vedantjha.browsemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.vedantjha.browsemovies.data.repository.MovieRetrofitRepository
import com.vedantjha.browsemovies.viewmodel.MovieViewModel
import com.vedantjha.browsemovies.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val navController = findNavController(R.id.fragment)


    }
}