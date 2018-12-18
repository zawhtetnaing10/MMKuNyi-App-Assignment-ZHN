package com.zawhtetnaing.mmkunyi_app_assignment_zhn.result

sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: List<T>) : Result<T>()

    class Error(val message: String) : Result<Nothing>()
}