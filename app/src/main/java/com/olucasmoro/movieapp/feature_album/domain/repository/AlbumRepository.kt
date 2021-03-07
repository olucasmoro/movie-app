package com.olucasmoro.movieapp.feature_album.domain.repository

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults

interface AlbumRepository {

    fun getMovies(movieType: String, apiKey: String): LiveData<CallResults<List<com.olucasmoro.movieapp.feature_album.data.remote.model.Movie>?>>
}