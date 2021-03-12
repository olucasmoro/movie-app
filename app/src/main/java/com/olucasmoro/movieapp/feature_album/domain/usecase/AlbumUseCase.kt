package com.olucasmoro.movieapp.feature_album.domain.usecase

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.feature_album.data.model.Search
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_album.data.model.WatchlistResponse
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository

class AlbumUseCase(private val repository: AlbumRepository) {

    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>> {
        return repository.getMovies(movieType, apiKey)
    }

    fun getDetailMovie(movieId: Int, apiKey: String): LiveData<CallResults<MovieDetail?>> {
        return repository.getDetailMovie(movieId, apiKey)
    }

    fun searchMovie(str: String, apiKey: String): LiveData<CallResults<List<Search>?>> {
        return repository.searchMovie(str, apiKey)
    }

    fun addWatchlist(
        accountId: Int,
        sessionId: String,
        apiKey: String,
        movieId: Int
    ): LiveData<CallResults<WatchlistResponse?>> {
        return repository.addWatchlist(accountId, sessionId, apiKey, movieId)
    }
}