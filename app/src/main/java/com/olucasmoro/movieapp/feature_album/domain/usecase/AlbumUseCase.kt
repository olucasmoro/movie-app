package com.olucasmoro.movieapp.feature_album.domain.usecase

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository

class AlbumUseCase(private val repository: AlbumRepository) {

    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>> {
        return repository.getMovies(movieType, apiKey)
    }
}