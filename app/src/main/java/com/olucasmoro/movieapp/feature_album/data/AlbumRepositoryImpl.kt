package com.olucasmoro.movieapp.feature_album.data

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.source.AlbumRemoteData
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository
import io.reactivex.Observable

class AlbumRepositoryImpl(
    private val remoteData: AlbumRemoteData
) : AlbumRepository {

    override fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>> {
        return remoteData.getMovies(movieType, apiKey)
    }

    override fun getDetailMovie(movieId: Int, apiKey: String): LiveData<CallResults<MovieDetail?>> {
        return remoteData.getDetailMovie(movieId, apiKey)
    }

}