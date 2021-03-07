package com.olucasmoro.movieapp.feature_album.data.remote

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.remote.model.Movie
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults

interface AlbumRemoteData {
    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>>
}