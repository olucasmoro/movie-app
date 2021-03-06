package com.olucasmoro.movieapp.app.service.utils

class Constants private constructor() {

    object MESSAGE {
        const val FAILURE = "Search failed "
        const val FAILURE_CONNECTION = "Failure to connection"
    }

    object TYPE {
        const val MOVIE = "movie"
        const val NOW_PLAYING = "now_playing"
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
    }

    object API {
        const val PAGE = "1"
        const val API_KEY = "YOUR_API_KEY"
        const val BASE_URL = "https://api.themoviedb.org"
        const val BASE_URL_AUTHENTICATION = "https://www.themoviedb.org/authenticate/"
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w342/"
    }

    object AUTHENTICATION {
        const val USER_ID = "user_id"
        const val USERNAME = "username"
        const val NAME = "name"
        const val PASSWORD = "password"
        const val SESSION_ID = "session_id"
        const val EMAIL = "email"
        const val TOKEN = "token"
    }

    object FIREBASE {
        const val WRONG_PASSWORD = 0
        const val SUCCESS = 1
        const val USER_NOT_FOUND = 2
        const val ERROR = 3
        const val NAME_DATABASE = "users"
    }

    object TIMER {
        const val SPLASH_TIMER: Long = 3500L
    }
}