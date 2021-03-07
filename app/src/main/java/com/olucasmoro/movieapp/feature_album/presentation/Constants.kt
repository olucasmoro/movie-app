package com.olucasmoro.movieapp.feature_album.presentation

class Constants private constructor() {

    object MOVIETYPE {
        const val NOW_PLAYING = "now_playing"
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
    }

    // Credentials
    object API {
        const val API_KEY = "b64a6b336c3652edc3c1c3459e02c9e6"
        const val BASE_URL = "https://api.themoviedb.org"
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w342/"
        const val LANGUAGE = "es-ES"
    }

    object DATABASE {
        const val DATABASE = "MovieLocalStorage.db"
    }

    object SHARED {
        const val PERSON_KEY = "personkey"
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object AUTHENTICATION {
        const val USER_ID = "user_id"
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val SESSION_ID = "session_id"
        const val EMAIL = "email"
        const val PROVIDER = "provider"
        const val IS_LOGGED = "is_logged"
    }

    object MENU {
        const val CHECKED_MENU = "checked_menu"
    }

    object DOTS {
        const val AMOUNT_DOTS_SLIDE = 4
        const val POSITION_LAST_DOTS = 2
    }

    object TIMER {
        const val SPLASH_TIMER: Long = 5000L
    }

    object TYPES {
        const val MOVIE = "Movie"
        const val MOVIE1 = "movie"
        const val MOVIE_SEARCH = "MovieSearch"
    }
}

enum class ProviderType {
    BASIC
}
