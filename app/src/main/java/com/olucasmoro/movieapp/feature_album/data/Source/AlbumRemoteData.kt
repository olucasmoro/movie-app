package com.olucasmoro.movieapp.feature_album.data.Source

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults

interface AlbumRemoteData {
    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>>
}