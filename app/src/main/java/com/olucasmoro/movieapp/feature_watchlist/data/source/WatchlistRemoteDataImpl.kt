package com.olucasmoro.movieapp.feature_watchlist.data.source

import androidx.lifecycle.liveData
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_watchlist.data.api.WatchlistApiService
import java.net.ConnectException

class WatchlistRemoteDataImpl(private val apiService: WatchlistApiService) : WatchlistRemoteData{

    override fun getWatchlist(userId: Int, sessionId: String, apiKey: String) = liveData {
        try {
            val response = apiService.getMovieWatchlist(userId, apiKey, sessionId)
            if (response.isSuccessful) {
                emit(CallResults.Success(data = response.body()?.results!!.toList()))
            } else {
                emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE)))
            }
        } catch (e: ConnectException) {
            emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE_CONNECTION)))
        } catch (e: Exception) {
            emit(CallResults.Error(exception = e))
        }
    }
}