package com.olucasmoro.movieapp.feature_album.data.remote.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String
)