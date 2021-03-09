package com.olucasmoro.movieapp.feature_user.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.api.UserApiService
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import java.net.ConnectException

class UserRemoteDataImpl(private val apiService: UserApiService) : UserRemoteData {

    override fun getToken(apiKey: String) = liveData {
        try {
            val response = apiService.createToken(apiKey)
            if (response.isSuccessful) {
                emit(CallResults.Success(data = response.body()))
            } else {
                emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE)))
            }
        } catch (e: ConnectException) {
            emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE_CONNECTION)))
        } catch (e: Exception) {
            emit(CallResults.Error(exception = e))
        }
    }

    override fun createSessionWithLogin(
        apiKey: String,
        username: String,
        password: String,
        requestToken: String
    ) = liveData {
        try {
            val response =
                apiService.createSessionWithLogin(apiKey, username, password, requestToken)
            if (response.isSuccessful) {
                emit(CallResults.Success(data = response.body()))
            } else {
                emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE)))
            }
        } catch (e: ConnectException) {
            emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE_CONNECTION)))
        } catch (e: Exception) {
            emit(CallResults.Error(exception = e))
        }
    }

    override fun createSession(
        apiKey: String,
        requestToken: String
    ) = liveData {
        try {
            val response =
                apiService.createSession(apiKey, requestToken)
            if (response.isSuccessful) {
                emit(CallResults.Success(data = response.body()))
            } else {
                emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE)))
            }
        } catch (e: ConnectException) {
            emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE_CONNECTION)))
        } catch (e: Exception) {
            emit(CallResults.Error(exception = e))
        }
    }
}