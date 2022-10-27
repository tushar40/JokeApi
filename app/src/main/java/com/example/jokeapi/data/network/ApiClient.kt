package com.example.jokeapi.data.network

import com.example.jokeapi.utils.Constants
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private var api: APIInterface? = null

    fun getApi(): APIInterface {
        if (api == null) {
            val retrofit = getRetrofit()
            api = retrofit.create(APIInterface::class.java)
        }

        return api!!
    }

    private fun getRetrofit(): Retrofit {
        val baseURL = Constants.BASE_URL
        val client =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(baseURL)
            .client(getHttpClient())

        return client.build()
    }

    private fun getHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

        return client.build()
    }
}