package ru.axdar.data.main

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.axdar.data.news.remote.NewsApiService

object NetworkClient {

    fun create(): NewsApiService =
        Retrofit.Builder().client(OkHttpClient.Builder().addInterceptor(MockInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl("https://www.ya.ru/")
            .build().create(NewsApiService::class.java)

}