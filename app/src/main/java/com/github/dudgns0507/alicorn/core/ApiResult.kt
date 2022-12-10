package com.github.dudgns0507.alicorn.core


sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Loading<T>(val data: T? = null) : ApiResult<T>()
    data class Failure(val exception: Exception?) : ApiResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<T> -> "Success[data=$data]"
            is Loading<T> -> "Loading"
            is Failure -> "Error[exception=$exception]"
        }
    }
}