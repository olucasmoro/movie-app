package com.olucasmoro.movieapp.feature_album.data.source

import androidx.lifecycle.liveData
import com.olucasmoro.movieapp.feature_album.data.api.AlbumApiService
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Constants
import java.net.ConnectException

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
}