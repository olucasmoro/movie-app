package com.olucasmoro.movieapp.featured_album.domain.entity

data class Movie(
    var id: String,
    var original_title: String,
    var poster_path: String,
    var backdrop_path: String,
    val vote_average: String
)

data class MovieDetail(
    val id: String,
    val genres: ArrayList<Genre>,
    val poster_path: String,
    val backdrop_path: String,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val tagline: String,
    val vote_average: String
)

data class MovieResponse(
    val page: String,
    val total_pages: String,
    val results: ArrayList<Movie>
)