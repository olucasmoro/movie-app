package com.olucasmoro.movieapp.feature_user.data.local

data class User(

    val id: Int,
    val email: String,
    val name: String,
    val password: String,
    val session_id: String
)