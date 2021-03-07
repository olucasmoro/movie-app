package com.olucasmoro.movieapp.featured_album.data.remote

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.featured_album.data.remote.model.Movie
import com.olucasmoro.movieapp.featured_album.domain.entity.CallResults

interface AlbumRemoteData {
    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>>
}