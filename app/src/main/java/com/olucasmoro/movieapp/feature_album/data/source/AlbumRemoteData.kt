package com.olucasmoro.movieapp.feature_album.data.source

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.feature_album.data.model.Search
import com.olucasmoro.movieapp.app.service.model.CallResults

interface AlbumRemoteData {
    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>>
    fun getDetailMovie(movieId: Int, apiKey: String): LiveData<CallResults<MovieDetail?>>
    fun searchMovie(str: String, apiKey: String): LiveData<CallResults<List<Search>?>>
}