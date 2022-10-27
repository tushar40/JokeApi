package com.example.jokeapi.utils

import androidx.annotation.StringRes
import com.example.jokeapi.MainApplication
import com.example.jokeapi.R
import com.example.jokeapi.domain.model.ResultWrapper
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection

fun getStringResource(@StringRes resId: Int, vararg arg: Any?): String = MainApplication.INSTANCE.resources.getString(resId, *arg)

suspend fun <T>makeApiCall(call: suspend () -> Response<T>) = flow {
    try {
        val response = call()
        val statusCode = response.code()

        if (statusCode == HttpURLConnection.HTTP_OK) {
            emit(ResultWrapper.OnSuccess(response.body()!!))
        } else {
            if (statusCode == HttpURLConnection.HTTP_UNAVAILABLE) {
                emit(ResultWrapper.ShowServerDownScreen)
            } else {
                emit(
                    ResultWrapper.OnServerError(
                        HttpException(response),
                        statusCode,
                        getStringResource(R.string.server_error_message)
                    ))
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        emit(ResultWrapper.OnNetworkError(e))
    }
}
