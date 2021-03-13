package com.olucasmoro.movieapp.feature_album.data.api

import com.olucasmoro.movieapp.feature_album.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

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

    @POST("3/account/{account_id}/watchlist")
    @FormUrlEncoded
    fun addToWatchlist(
        @Path("account_id") account_id: Int,
        @Query("api_key") key: String,
        @Query("session_id") session_id: String,
        @Field("media_type") media_type: String,
        @Field("media_id") media_id: Int,
        @Field("watchlist") watchlist: Boolean
    ): Call<WatchlistResponse>

}