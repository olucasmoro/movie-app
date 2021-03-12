package com.olucasmoro.movieapp.feature_watchlist.data.api

import com.olucasmoro.movieapp.feature_watchlist.data.model.WatchlistResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface WatchlistApiService {

    @GET("3/account/{account_id}/watchlist/movies")
    suspend fun getMovieWatchlist(
        @Path("account_id") account_id: Int,
        @Query("api_key") key: String,
        @Query("session_id") session_id: String
    ): Response<WatchlistResponse>
    
}