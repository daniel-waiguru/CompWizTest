package io.compwiz.countrylister.data.models

sealed class StateWrapper<out T> {
    data class Success<out T>(val value: T) : StateWrapper<T>()
    data class Failure<T>(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorMessage: String?
    ) : StateWrapper<T>()
    object Loading : StateWrapper<Nothing>()
}