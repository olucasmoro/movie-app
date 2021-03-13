package com.olucasmoro.movieapp.feature_user.presentation.login

import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.feature_user.data.local.User
import com.olucasmoro.movieapp.feature_user.domain.usecase.UserUseCase

class LoginViewModel(
    private val useCase: UserUseCase
) : ViewModel() {

    fun checkUserFirebase(username: String, password: String): Int =
        useCase.checkUserFirebase(username, password)

    fun getUserFirebase(username: String): User =
        useCase.getUserFirebase(username)

    fun store(key: String, value: String) =
        useCase.store(key, value)

}