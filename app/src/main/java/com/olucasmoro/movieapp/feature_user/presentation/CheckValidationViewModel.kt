package com.olucasmoro.movieapp.feature_user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.domain.usecase.UserUseCase

class CheckValidationViewModel(
    private val useCase: UserUseCase
) : ViewModel() {

    fun createSession(requestToken: String): LiveData<CallResults<SessionResponse?>> =
        useCase.createSession(Constants.API.API_KEY, requestToken)

    fun saveUserFirebase(
        username: String,
        name: String,
        email: String,
        password: String,
        session_id: String
    ) =
        useCase.saveUserFirebase(username, name, email, password, session_id)

    fun get(key: String): String = useCase.get(key)

    fun store(key: String, value: String) = useCase.store(key, value)

}