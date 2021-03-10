package com.olucasmoro.movieapp.feature_album.domain.entity

sealed class CallResults<out R> {
    data class Success<out T>(val data: T?) : CallResults<T?>()
    data class Error(val exception: Exception) : CallResults<Nothing>()
}