package com.olucasmoro.movieapp.feature_album.data

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.source.AlbumRemoteData
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.feature_album.data.model.Search
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_album.data.model.WatchlistResponse
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository

class AlbumRepositoryImpl(
    private val remoteData: AlbumRemoteData
) : AlbumRepository {

    override fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>> {
        return remoteData.getMovies(movieType, apiKey)
    }

    override fun getDetailMovie(movieId: Int, apiKey: String): LiveData<CallResults<MovieDetail?>> {
        return remoteData.getDetailMovie(movieId, apiKey)
    }

    override fun searchMovie(str: String, apiKey: String): LiveData<CallResults<List<Search>?>> {
        return remoteData.searchMovie(str, apiKey)
    }

    override fun addWatchlist(
        accountId: Int,
        sessionId: String,
        apiKey: String,
        movieId: Int
    ): LiveData<CallResults<WatchlistResponse?>> {
        return  remoteData.addWatchlist(accountId, sessionId, apiKey, movieId)
    }
}