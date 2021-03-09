package com.olucasmoro.movieapp.feature_user.data.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("api_key")
    val api_key: String,

    @SerializedName("request_token")
    val request_token: String
)

data class SessionResponse(
    @SerializedName("success")
    val success: String,

    @SerializedName("session_id")
    val session_id: String
)