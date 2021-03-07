package com.olucasmoro.movieapp.featured_album.data.remote.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String
)