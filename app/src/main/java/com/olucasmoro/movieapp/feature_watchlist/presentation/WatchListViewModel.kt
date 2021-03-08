package com.olucasmoro.movieapp.feature_watchlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie
import com.olucasmoro.movieapp.feature_watchlist.domain.usecase.WatchlistUseCase

class WatchListViewModel(private val useCase: WatchlistUseCase) : ViewModel() {

    fun watchlistMovies(userId: Int, sessionId: String): LiveData<CallResults<List<Movie>?>> =
        useCase.getWatchlist(userId, sessionId, Constants.API.API_KEY)
}