package com.olucasmoro.movieapp.feature_user.data.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("api_key")
    val api_key: String
)

data class TokenResponse(
    @SerializedName("success")
    val success: String,

    @SerializedName("expires_at")
    val expires_at: String,

    @SerializedName("request_token")
    val request_token: String
)
