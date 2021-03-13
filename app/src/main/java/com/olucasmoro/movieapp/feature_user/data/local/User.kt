package com.olucasmoro.movieapp.feature_user.data.local

data class User(
    val username: String
) {
    var name: String = ""
    var email: String = ""
    var password: String = ""
    var sessionId: Int? = null
    var id: Int? = null
}