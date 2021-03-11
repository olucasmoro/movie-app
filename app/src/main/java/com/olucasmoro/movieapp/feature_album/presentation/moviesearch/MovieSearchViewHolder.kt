package com.olucasmoro.movieapp.feature_album.presentation.moviesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.feature_album.data.model.Search
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.databinding.ItemSearchBinding
import com.squareup.picasso.Picasso

class MovieSearchViewHolder(private val itemBinding: ItemSearchBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Search, findNavController: NavController) {
        itemBinding.textTitle.text = movie.original_title

        val target: String = movie.poster_path

        itemBinding.textReleaseDate.text = movie.release_date
        itemBinding.textEvaluation.text = movie.vote_average

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + target).into(itemBinding.imagePoster)

        itemBinding.cardPoster.setOnClickListener {
            findNavController.navigate(
                MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment(
                    movie.id
                )
            )
        }
    }

    companion object {
        fun inflate(parent: ViewGroup): MovieSearchViewHolder {

            val view = ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return MovieSearchViewHolder(view)
        }
    }
}