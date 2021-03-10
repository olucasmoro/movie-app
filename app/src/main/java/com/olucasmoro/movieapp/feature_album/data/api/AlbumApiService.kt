package com.olucasmoro.movieapp.feature_album.data.api

import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.feature_album.data.model.MovieResponse
import com.olucasmoro.movieapp.feature_album.data.model.SearchResponse
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

    @GET("3/movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") key: String
    ): Response<MovieDetail>

    @GET("3/search/movie")
    suspend fun getSearchMovie(
        @Query("api_key") key: String,
        @Query("query") query: String
    ): Response<SearchResponse>

}