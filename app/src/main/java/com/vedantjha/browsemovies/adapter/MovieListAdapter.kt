package com.vedantjha.browsemovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vedantjha.browsemovies.data.model.Movie
import com.vedantjha.browsemovies.databinding.MovieRecyclerItemBinding

class MovieListAdapter constructor(val clickListener: MovieItemClickListener) :
    ListAdapter<Movie, MovieListAdapter.MovieItemViewHolder>(MovieListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding =
            MovieRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    class MovieItemViewHolder constructor(private val binding: MovieRecyclerItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Movie?, clickListener: MovieItemClickListener) {
            (binding as MovieRecyclerItemBinding).apply {
                binding.movieItem = item
                binding.clickListener = clickListener
            }
        }
    }


}

class MovieListDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}

class MovieItemClickListener(val clickListener: (movie: Movie) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie)

}
