package com.zestworks.data.model

sealed class LCE<out T : Any> {
    object YetToMakeRequest : LCE<Nothing>()
    object Loading : LCE<Nothing>()
    data class Content<out T : Any>(val data: T) : LCE<T>()
    data class Error(val errorMessage: String) : LCE<Nothing>()
}