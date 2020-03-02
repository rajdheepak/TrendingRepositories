package com.zestworks.data.model

sealed class Data<out T : Any> {
    data class Success<out T : Any>(val data: T) : Data<T>()
    data class Error(val errorMessage: String) : Data<Nothing>()
    object Empty : Data<Nothing>()
}