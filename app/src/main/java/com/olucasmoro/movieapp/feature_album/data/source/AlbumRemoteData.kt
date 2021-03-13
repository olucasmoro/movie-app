package com.olucasmoro.movieapp.feature_album.data.source

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_album.data.model.*

interface AlbumRemoteData {
    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>>
    fun getDetailMovie(movieId: Int, apiKey: String): LiveData<CallResults<MovieDetail?>>
    fun searchMovie(str: String, apiKey: String): LiveData<CallResults<List<Search>?>>
    fun addWatchlist(
        accountId: Int,
        sessionId: String,
        apiKey: String,
        movieId: Int
    )
}