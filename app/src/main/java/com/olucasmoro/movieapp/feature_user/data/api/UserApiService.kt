package com.olucasmoro.movieapp.feature_user.data.api

import com.olucasmoro.movieapp.feature_user.data.local.User
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.*

interface UserApiService {

    @GET("3/authentication/token/new")
    suspend fun createToken(
        @Query("api_key") key: String,
    ): Response<TokenResponse>

    @POST("3/authentication/session/new")
    @FormUrlEncoded
    suspend fun createSession(
        @Query("api_key") key: String,
        @Field("request_token") token: String
    ): Response<SessionResponse>

    @GET("3/account")
    suspend fun getUserDetail(
        @Query("api_key") key: String,
        @Query("session_id") session: String
    ): Response<User>
}