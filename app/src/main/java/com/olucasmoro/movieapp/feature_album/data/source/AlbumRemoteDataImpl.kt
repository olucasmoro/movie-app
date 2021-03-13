package com.olucasmoro.movieapp.feature_album.data.source

import androidx.lifecycle.liveData
import com.olucasmoro.movieapp.feature_album.data.api.AlbumApiService
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_album.data.model.WatchlistResponse
import java.net.ConnectException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRemoteDataImpl(private val apiService: AlbumApiService) : AlbumRemoteData {

    override fun getMovies(movieType: String, apiKey: String) = liveData {
        try {
            val response = apiService.getMovies(movieType, apiKey, Constants.API.PAGE)
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

    override fun getDetailMovie(movieId: Int, apiKey: String) = liveData {
        try {
            val response = apiService.getDetailMovie(movieId, apiKey)
            if (response.isSuccessful) {
                emit(CallResults.Success(data = response.body()))
            } else {
                emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE)))
            }
        } catch (e: ConnectException) {
            emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE_CONNECTION)))
        } catch (e: Exception) {
            emit(CallResults.Error(exception = e))
        }
    }

    override fun searchMovie(str: String, apiKey: String) = liveData {
        try {
            val response = apiService.getSearchMovie(apiKey, str)
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


    override fun addWatchlist(
        accountId: Int,
        sessionId: String,
        apiKey: String,
        movieId: Int
    ) {

        apiService.addToWatchlist(
            account_id = accountId,
            key = apiKey,
            session_id = sessionId,
            media_type = Constants.TYPE.MOVIE,
            media_id = movieId,
            true
        ).enqueue(object : Callback<WatchlistResponse> {
            override fun onResponse(
                call: Call<WatchlistResponse>,
                response: Response<WatchlistResponse>
            ) {
            }

            override fun onFailure(call: Call<WatchlistResponse>, t: Throwable) {}

        })

    }

}
