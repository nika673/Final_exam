package com.example.Final_Project.domain.model

sealed class Result<out T> {

    class Success<T>(val data: T) : Result<T>()

    class Error(val e: Exception) : Result<Nothing>()
}
