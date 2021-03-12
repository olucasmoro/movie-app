package com.olucasmoro.movieapp.feature_album.domain.repository

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.feature_album.data.model.Search
import com.olucasmoro.movieapp.feature_album.data.model.WatchlistResponse

interface AlbumRepository {

    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>>

    fun getDetailMovie(movieId: Int, apiKey: String): LiveData<CallResults<MovieDetail?>>

    fun searchMovie(str: String, apiKey: String): LiveData<CallResults<List<Search>?>>

    fun addWatchlist(
        accountId: Int,
        sessionId: String,
        apiKey: String,
        movieId: Int
    ): LiveData<CallResults<WatchlistResponse?>>
}