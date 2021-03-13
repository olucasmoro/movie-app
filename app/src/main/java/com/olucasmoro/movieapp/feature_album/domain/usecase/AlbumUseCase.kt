package com.olucasmoro.movieapp.feature_album.domain.usecase

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_album.data.model.*
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository

class AlbumUseCase(private val repository: AlbumRepository) {

    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>> =
        repository.getMovies(movieType, apiKey)

    fun getDetailMovie(movieId: Int, apiKey: String): LiveData<CallResults<MovieDetail?>> =
        repository.getDetailMovie(movieId, apiKey)

    fun searchMovie(str: String, apiKey: String): LiveData<CallResults<List<Search>?>> =
        repository.searchMovie(str, apiKey)

    fun addWatchlist(
        accountId: Int,
        sessionId: String,
        apiKey: String,
        movieId: Int
    ) = repository.addWatchlist(accountId, sessionId, apiKey, movieId)

}