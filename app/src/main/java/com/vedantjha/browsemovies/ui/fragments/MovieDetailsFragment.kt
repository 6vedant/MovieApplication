package com.vedantjha.browsemovies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vedantjha.browsemovies.databinding.FragmentMovieDetailsBinding
import com.vedantjha.browsemovies.databinding.FragmentMovieListBinding

class MovieDetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }
}