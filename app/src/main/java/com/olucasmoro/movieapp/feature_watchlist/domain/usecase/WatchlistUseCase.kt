package com.olucasmoro.movieapp.feature_watchlist.domain.usecase

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie
import com.olucasmoro.movieapp.feature_watchlist.domain.repository.WatchlistRepository

class WatchlistUseCase(
    private val repository: WatchlistRepository
) {

    fun getWatchlist(
        userId: Int,
        sessionId: String,
        apiKey: String
    ): LiveData<CallResults<List<Movie>?>> {
        return repository.getWatchlist(userId, sessionId, apiKey)
    }
}