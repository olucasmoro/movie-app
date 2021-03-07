package com.olucasmoro.movieapp.feature_album.data.remote

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.remote.model.Movie
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository

class AlbumRepositoryImpl(
    private val remoteData: AlbumRemoteData
) : AlbumRepository {

    override fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>> {
        return remoteData.getMovies(movieType, apiKey)
    }

}