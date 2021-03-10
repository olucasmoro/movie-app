package com.olucasmoro.movieapp.feature_watchlist.data

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie
import com.olucasmoro.movieapp.feature_watchlist.data.source.WatchlistRemoteData
import com.olucasmoro.movieapp.feature_watchlist.domain.repository.WatchlistRepository

class WatchlistRepositoryImpl(
    private val remoteData: WatchlistRemoteData
) : WatchlistRepository {

    override fun getWatchlist(
        userId: Int,
        sessionId: String,
        apiKey: String
    ): LiveData<CallResults<List<Movie>?>> {
        return remoteData.getWatchlist(userId, sessionId, apiKey)
    }
}