package com.vedantjha.browsemovies.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.vedantjha.browsemovies.R
import com.vedantjha.browsemovies.adapter.MovieItemClickListener
import com.vedantjha.browsemovies.adapter.MovieListAdapter
import com.vedantjha.browsemovies.adapter.MoviePagingAdapter
import com.vedantjha.browsemovies.data.repository.MovieRetrofitRepository
import com.vedantjha.browsemovies.databinding.FragmentMovieDetailsBinding
import com.vedantjha.browsemovies.databinding.FragmentMovieListBinding
import com.vedantjha.browsemovies.viewmodel.MoviePagingViewModel
import com.vedantjha.browsemovies.viewmodel.MoviePagingViewModelFactory
import com.vedantjha.browsemovies.viewmodel.MovieViewModel
import com.vedantjha.browsemovies.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment: Fragment() {

    @Inject lateinit var movieRetrofitRepository: MovieRetrofitRepository

    private lateinit var movieListAdapter: MoviePagingAdapter

    private val moviewViewModel: MovieViewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(movieRetrofitRepository)
    }

    private val moviePagingViewModel: MoviePagingViewModel by viewModels<MoviePagingViewModel> {
        MoviePagingViewModelFactory(movieRetrofitRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieListBinding.inflate(inflater, container, false)

//        CoroutineScope(Dispatchers.Main).launch {
//            delay(2000)
//            findNavController().navigate(R.id.action_movieListFragment_to_movieDetailsFragment)
//        }
        movieListAdapter = MoviePagingAdapter(MovieItemClickListener {
            Log.d("MovieData", it.toString())
            // pass data using NavigationDirections
            this.findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(it))
          //  this.findNavController().navigate(R.id.action_movieListFragment_to_movieDetailsFragment)
        })
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.movieRecyclerView.setHasFixedSize(true)
        binding.movieRecyclerView.adapter = movieListAdapter


        movieListAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    Toast.makeText(context, "loading data...", Toast.LENGTH_SHORT).show()

                }
                is LoadState.Error -> {
                    Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT).show()
                }
                is LoadState.NotLoading -> {

                }
            }
        }

        moviePagingViewModel.moviesList.observe(viewLifecycleOwner, Observer {
            Log.d("MovieData", it.toString())
            if(it == null) {
                Toast.makeText(context, "Data is null data: ", Toast.LENGTH_SHORT)
                    .show()
            } else {
                movieListAdapter.submitData(lifecycle, it)
                Toast.makeText(context, "Updated data: " + it.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        })

        return binding.root
    }
}