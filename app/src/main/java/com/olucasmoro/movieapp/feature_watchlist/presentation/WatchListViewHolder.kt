package com.olucasmoro.movieapp.feature_watchlist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.databinding.ItemMovieBinding
import com.olucasmoro.movieapp.feature_album.presentation.movielist.MovieListViewHolder
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie
import com.squareup.picasso.Picasso

class WatchListViewHolder(private val itemBinding: ItemMovieBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie) {

        itemBinding.tvNameOurTitle.text = movie.original_title
        itemBinding.rating.text = movie.vote_average
        itemBinding.ratingBar.rating = movie.vote_average.toFloat() / 2.0f
        val target = movie.poster_path

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).resize(140, 170)
            .into(itemBinding.imgLiview)

        itemBinding.root.setOnClickListener {

        }
    }

    companion object {
        fun inflate(parent: ViewGroup): WatchListViewHolder {
            val view = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return WatchListViewHolder(view)
        }
    }
}