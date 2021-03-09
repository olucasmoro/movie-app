package com.olucasmoro.movieapp.feature_album.presentation.movielist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie, findNavController: NavController) {

        itemView.tv_name_our_title.text = movie.original_title
        itemView.rating.text = movie.vote_average
        itemView.rating_bar.rating = movie.vote_average.toFloat() / 2.0f
        val target = movie.poster_path

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).resize(140, 170)
            .into(itemView.img_liview)

        itemView.setOnClickListener {
            findNavController.navigate(MovieListFragmentDirections.actionNavigationMovieListToMovieDetailFragment(movieId = movie.id))
        }
    }

    companion object {

        fun inflate(parent: ViewGroup): MovieListViewHolder {

            //DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.item_container_movie, parent, false)
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return MovieListViewHolder(view)
        }
    }
}