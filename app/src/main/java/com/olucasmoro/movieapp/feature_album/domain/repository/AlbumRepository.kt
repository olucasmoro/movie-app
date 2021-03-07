package com.olucasmoro.movieapp.feature_album.domain.repository

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.data.model.Movie

interface AlbumRepository {

    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<Movie>?>>
}