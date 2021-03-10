package com.olucasmoro.movieapp.feature_album.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_album.domain.usecase.AlbumUseCase
import com.olucasmoro.movieapp.app.service.utils.Constants

class MovieListViewModel(private val useCase: AlbumUseCase) :
    ViewModel() {

    val moviesPopular: LiveData<CallResults<List<Movie>?>> =
        useCase.getMovies(Constants.TYPE.POPULAR, Constants.API.API_KEY)

    val moviesTopRated: LiveData<CallResults<List<Movie>?>> =
        useCase.getMovies(Constants.TYPE.TOP_RATED, Constants.API.API_KEY)

    val moviesNowPlaying: LiveData<CallResults<List<Movie>?>> =
        useCase.getMovies(Constants.TYPE.NOW_PLAYING, Constants.API.API_KEY)

    val moviesUpcoming: LiveData<CallResults<List<Movie>?>> =
        useCase.getMovies(Constants.TYPE.UPCOMING, Constants.API.API_KEY)
}