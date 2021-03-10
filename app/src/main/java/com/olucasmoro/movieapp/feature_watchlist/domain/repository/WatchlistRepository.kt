package com.olucasmoro.movieapp.feature_watchlist.domain.repository

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie

interface WatchlistRepository {

    fun getWatchlist(
        userId: Int,
        sessionId: String,
        apiKey: String
    ): LiveData<CallResults<List<Movie>?>>
}