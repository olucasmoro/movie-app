package com.olucasmoro.movieapp.feature_watchlist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.databinding.ItemMovieBinding
import com.olucasmoro.movieapp.databinding.ItemMovieWatchlistBinding
import com.olucasmoro.movieapp.feature_album.presentation.movielist.MovieListFragmentDirections
import com.olucasmoro.movieapp.feature_album.presentation.movielist.MovieListViewHolder
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie
import com.squareup.picasso.Picasso

class WatchListViewHolder(private val itemBinding: ItemMovieWatchlistBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie, findNavController: NavController) {

        val target = movie.poster_path
        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).into(itemBinding.imagePoster)

        itemBinding.root.setOnClickListener {
            findNavController.navigate(
                WatchListFragmentDirections.actionWatchListFragmentToMovieDetailFragment(
                    movieId = movie.id, watchlist = "true"
                )
            )
        }
    }

    companion object {
        fun inflate(parent: ViewGroup): WatchListViewHolder {
            val view = ItemMovieWatchlistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return WatchListViewHolder(view)
        }
    }
}