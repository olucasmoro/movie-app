package com.olucasmoro.movieapp.feature_album.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: String,
    val total_pages: String,
    val results: ArrayList<Movie>
)

class Movie(
    @SerializedName("id")
    var id: String,

    @SerializedName("original_title")
    var original_title: String,

    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("backdrop_path")
    var backdrop_path: String,

    @SerializedName("vote_average")
    val vote_average: String
)

data class MovieDetail(

    @SerializedName("id")
    val id: String,

    @SerializedName("genres")
    val genres: ArrayList<Genre>,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("backdrop_path")
    val backdrop_path: String,

    @SerializedName("original_title")
    val original_title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("vote_average")
    val vote_average: String
)