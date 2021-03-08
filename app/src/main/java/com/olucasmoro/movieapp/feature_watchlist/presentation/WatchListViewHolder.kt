package com.olucasmoro.movieapp.feature_watchlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie2.view.*

class WatchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {

        val target = movie.poster_path

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).resize(135, 210)
            .into(itemView.img_liview)

        itemView.setOnClickListener {

        }
    }

    companion object {
        fun inflate(parent: ViewGroup): WatchListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie2, parent, false)
            return WatchListViewHolder(view)
        }
    }
}