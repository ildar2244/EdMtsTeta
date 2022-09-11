package ru.axdar.data.main

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.axdar.data.news.remote.NewsApiService
import java.util.concurrent.TimeUnit

object NetworkClient {

    private const val BASE_URL = "https://inshorts.deta.dev/"

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            /*.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })*/
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    fun create(): NewsApiService =
        Retrofit.Builder()
            .client(makeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(NewsApiService::class.java)

}