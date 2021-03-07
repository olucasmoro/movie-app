package com.olucasmoro.movieapp.feature_album.data.Source

import androidx.lifecycle.liveData
import com.olucasmoro.movieapp.feature_album.data.api.AlbumApiService
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
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
}