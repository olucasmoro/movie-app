package com.olucasmoro.movieapp.feature_user.data.local

data class User(
    val username: String,
    val name: String,
    val email: String,
    val password: String,
    val session_id: String
) {
    val id: Int? = null
}