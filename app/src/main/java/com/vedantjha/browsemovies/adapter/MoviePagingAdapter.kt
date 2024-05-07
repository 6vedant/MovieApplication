package com.vedantjha.browsemovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vedantjha.browsemovies.data.model.Movie
import com.vedantjha.browsemovies.databinding.MovieRecyclerItemBinding

class MoviePagingAdapter(private val clickListener: MovieItemClickListener) :
    PagingDataAdapter<Movie, MoviePagingAdapter.MovieViewHolder>(MovieListDiffCallback()) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    class MovieViewHolder(
        private val binding: MovieRecyclerItemBinding
    ) :
        ViewHolder(binding.root) {
        fun bind(item: Movie?, clickListener: MovieItemClickListener) {
            item?.let {
                binding.movieItem = item
                binding.clickListener = clickListener
            }
        }
    }


}