package com.olucasmoro.movieapp.featured_album.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.featured_album.data.remote.model.Movie
import com.olucasmoro.movieapp.featured_album.domain.entity.CallResults
import com.olucasmoro.movieapp.featured_album.domain.usecase.AlbumUseCase
import com.olucasmoro.movieapp.featured_album.presentation.Constants

class MovieListViewModel(private val useCase: AlbumUseCase) :
    ViewModel() {

    fun moviesPopular(): LiveData<CallResults<List<Movie>?>> =
        useCase.getMovies(Constants.MOVIETYPE.POPULAR, Constants.API.API_KEY)

    fun moviesTopRated(): LiveData<CallResults<List<Movie>?>> =
        useCase.getMovies(Constants.MOVIETYPE.TOP_RATED, Constants.API.API_KEY)

    fun moviesNowPlaying(): LiveData<CallResults<List<Movie>?>> =
        useCase.getMovies(Constants.MOVIETYPE.NOW_PLAYING, Constants.API.API_KEY)

    fun moviesUpcoming(): LiveData<CallResults<List<Movie>?>> =
        useCase.getMovies(Constants.MOVIETYPE.UPCOMING, Constants.API.API_KEY)
}