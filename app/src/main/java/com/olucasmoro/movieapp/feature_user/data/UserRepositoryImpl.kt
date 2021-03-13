package com.olucasmoro.movieapp.feature_user.data

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_user.data.local.User
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import com.olucasmoro.movieapp.feature_user.data.source.UserLocalData
import com.olucasmoro.movieapp.feature_user.data.source.UserRemoteData
import com.olucasmoro.movieapp.feature_user.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteData: UserRemoteData,
    private val localData: UserLocalData
) : UserRepository {

    override fun getToken(apiKey: String): LiveData<CallResults<TokenResponse?>> {
        return remoteData.getToken(apiKey)
    }

    override fun createSession(
        apiKey: String,
        requestToken: String
    ): LiveData<CallResults<SessionResponse?>> {
        return remoteData.createSession(apiKey, requestToken)
    }

    override fun saveUserFirebase(
        username: String,
        name: String,
        email: String,
        password: String,
        session_id: String
    ) {
        return remoteData.saveUserFirebase(username, name, email, password, session_id)
    }

    override fun checkUserFirebase(username: String, password: String): Int =
        remoteData.checkUserFirebase(username, password)

    override fun getUserFirebase(username: String): User =
        remoteData.getUserFirebase(username)

    override fun store(key: String, value: String) {
        localData.store(key, value)
    }

    override fun get(key: String): String =
        localData.get(key)

}