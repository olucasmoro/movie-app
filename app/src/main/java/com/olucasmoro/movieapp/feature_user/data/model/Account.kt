package com.olucasmoro.movieapp.feature_user.data.model

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("id")
    val id: Int,

    @SerializedName("username")
    val username: String
)