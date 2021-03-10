package com.olucasmoro.movieapp.feature_album.presentation.moviesearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.feature_album.data.model.Search
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_album.domain.usecase.AlbumUseCase
import com.olucasmoro.movieapp.app.service.utils.Constants

class MovieSearchViewModel(private val repository: AlbumUseCase) : ViewModel() {

    fun searchMovies(str: String): LiveData<CallResults<List<Search>?>> =
        repository.searchMovie(str, Constants.API.API_KEY)

}