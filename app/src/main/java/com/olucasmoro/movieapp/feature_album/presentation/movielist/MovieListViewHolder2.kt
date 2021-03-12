package com.olucasmoro.movieapp.feature_album.presentation.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.databinding.ItemMovieBinding
import com.olucasmoro.movieapp.databinding.ItemMovieLargeBinding
import com.squareup.picasso.Picasso

class MovieListViewHolder2(private val itemBinding: ItemMovieLargeBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie, findNavController: NavController) {
        itemBinding.textTitle2.text = movie.original_title
        itemBinding.textVoteCount2.text = movie.vote_average
        itemBinding.ratingBar2.rating = movie.vote_average.toFloat() / 2.0f
        val target = movie.poster_path

//        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).resize(140, 170)
//            .into(itemBinding.imagePoster)

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).into(itemBinding.imagePoster)

        itemBinding.root.setOnClickListener {
            findNavController.navigate(
                MovieListFragmentDirections.actionNavigationMovieListToMovieDetailFragment(
                    movieId = movie.id
                )
            )
        }
    }

    companion object {

        fun inflate(parent: ViewGroup): MovieListViewHolder2 {
            val view = ItemMovieLargeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_movie, parent, false)
            return MovieListViewHolder2(view)
        }
    }
}