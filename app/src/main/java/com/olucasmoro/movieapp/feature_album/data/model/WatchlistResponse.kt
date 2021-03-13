package com.olucasmoro.movieapp.feature_album.data.model

import com.google.gson.annotations.SerializedName
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie

data class WatchlistResponse(
    @SerializedName("success")
    val success: String,

    @SerializedName("status_code")
    val status_code: Int,

    @SerializedName("status_message")
    val status_message: String
)