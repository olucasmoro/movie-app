package com.olucasmoro.movieapp.feature_album.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.domain.usecase.AlbumUseCase
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants

class MovieDetailViewModel(private val useCase: AlbumUseCase) : ViewModel() {

    fun detailMovie(movieId: Int): LiveData<CallResults<MovieDetail?>> =
        useCase.getDetailMovie(movieId, Constants.API.API_KEY)

    var isWatched: Boolean = false
    var isWatchlist: Boolean = false
}