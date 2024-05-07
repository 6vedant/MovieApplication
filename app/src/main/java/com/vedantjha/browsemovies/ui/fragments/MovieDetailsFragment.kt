package com.vedantjha.browsemovies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.vedantjha.browsemovies.data.model.Movie
import com.vedantjha.browsemovies.databinding.FragmentMovieDetailsBinding
import com.vedantjha.browsemovies.databinding.FragmentMovieListBinding

class MovieDetailsFragment: Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        binding.movieItem = args.myArg

        return binding.root
    }
}