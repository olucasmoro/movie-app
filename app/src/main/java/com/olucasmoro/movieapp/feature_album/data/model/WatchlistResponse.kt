package com.olucasmoro.movieapp.feature_album.data.model

import com.google.gson.annotations.SerializedName
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie

data class WatchlistResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("total_pages")
    val total_pages: Int,

    @SerializedName("results")
    val results: ArrayList<Movie>
)