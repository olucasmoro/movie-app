package com.olucasmoro.movieapp.feature_album.presentation

import androidx.navigation.NavController
import com.olucasmoro.movieapp.feature_album.presentation.movielist.MovieListFragmentDirections

class Listeners {

    companion object {

        fun movieListToMovieDetail(findNavController: NavController) {
            findNavController.navigate(MovieListFragmentDirections.actionNavigationMovieListToMovieDetailFragment())
        }
    }
}