package com.olucasmoro.movieapp.feature_album.presentation.moviesearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.feature_album.data.model.Search
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_search.view.*

class MovieSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Search, findNavController: NavController) {

        itemView.textTitle.text = movie.original_title

        val target: String = movie.poster_path //Poster

        itemView.textReleaseDate.text = movie.release_date
        itemView.textEvaluation.text = movie.vote_average

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).into(itemView.imagePoster)

        itemView.cardPoster.setOnClickListener {
            findNavController.navigate(
                MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment(
                    movie.id
                )
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieSearchViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_search, parent, false)
            return MovieSearchViewHolder(view)
        }
    }
}