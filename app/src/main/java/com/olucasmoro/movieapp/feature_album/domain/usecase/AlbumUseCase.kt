package com.olucasmoro.movieapp.feature_album.domain.usecase

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.feature_album.data.model.Search
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository
import io.reactivex.Observable

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
}