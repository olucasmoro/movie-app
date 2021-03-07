package com.olucasmoro.movieapp.feature_album.data.remote

import androidx.lifecycle.liveData
import com.olucasmoro.movieapp.feature_album.data.remote.api.AlbumApiService
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import java.net.ConnectException

class AlbumRemoteDataImpl(private val apiService: AlbumApiService) : AlbumRemoteData {

    override fun getMovies(movieType: String, apiKey: String) = liveData {
        try {
            val response = apiService.getMovies(movieType, apiKey, "1")
            if (response.isSuccessful) {
                emit(CallResults.Success(data = response.body()?.results!!.toList()))
            } else {
                emit(CallResults.Error(exception = Exception("Falha ao buscar o endereco")))
            }
        } catch (e: ConnectException) {
            emit(CallResults.Error(exception = Exception("Falha na comunicação com API")))
        } catch (e: Exception) {
            emit(CallResults.Error(exception = e))
        }
    }
}