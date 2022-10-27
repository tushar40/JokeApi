package com.example.jokeapi.domain.model

sealed class ResultWrapper<out T> {
    data class OnSuccess<out T>(val data: T) : ResultWrapper<T>()
    data class OnNetworkError(val error: Throwable) : ResultWrapper<Nothing>()
    data class OnServerError(val error: Throwable, val code: Int, val errorMessage: String?) :
        ResultWrapper<Nothing>()

    object ShowServerDownScreen : ResultWrapper<Nothing>()
}

inline fun <T> ResultWrapper<T>.onSuccess(action: (value: T) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.OnSuccess) action(data)
    return this
}

inline fun <T> ResultWrapper<T>.onNetworkError(action: (error: Throwable) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.OnNetworkError) action(error)
    return this
}

inline fun <T> ResultWrapper<T>.onServerError(action: (error: Throwable, code: Int, errorMessage: String?) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.OnServerError) action(error, code, errorMessage)
    return this
}

inline fun <T> ResultWrapper<T>.showServerDownScreen(action: () -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.ShowServerDownScreen) action()
    return this
}