package com.olucasmoro.movieapp.feature_album.data.remote.api

import com.olucasmoro.movieapp.feature_album.data.remote.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumApiService {

    @GET("3/movie/{movie_type}")
    suspend fun getMovies(
            @Path("movie_type") movieType: String?,
            @Query("api_key") key: String,
            @Query("page") page: String
    ): Response<MovieResponse>

}