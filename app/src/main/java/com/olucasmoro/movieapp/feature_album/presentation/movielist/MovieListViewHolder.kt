package com.olucasmoro.movieapp.featured_album.presentation.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.featured_album.data.remote.model.Movie
import com.olucasmoro.movieapp.featured_album.presentation.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_container_movie.view.*

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {

        itemView.tv_name_our_title.text = movie.original_title
        itemView.rating.text = movie.vote_average
        itemView.rating_bar.rating = movie.vote_average.toFloat() / 2.0f
        val target = movie.poster_path

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).resize(140, 170)
            .into(itemView.img_liview)

        itemView.setOnClickListener {

        } // Chamar o listener
    }

    companion object {
        //inflate
        fun create(parent: ViewGroup): MovieListViewHolder {

            // DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.item_container_movie)

            //DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.item_container_movie, parent, false)

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_container_movie, parent, false)
            return MovieListViewHolder(view)
        }
    }
}