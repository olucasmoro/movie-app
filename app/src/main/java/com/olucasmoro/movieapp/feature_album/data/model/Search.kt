package com.olucasmoro.movieapp.feature_album.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results")
    val results: ArrayList<Search>
)

data class Search(

    @SerializedName("profile_path")
    val profile_path: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("original_title")
    val original_title: String,

    @SerializedName("media_type")
    val media_type: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("original_name")
    var original_name: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("vote_average")
    val vote_average: String
)