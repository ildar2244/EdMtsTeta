package ru.axdar.data.news.remote

import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApiService {

    @GET("news?category=automobile")
    @Headers("Content-Type:application/json; charset=utf-8;")
    suspend fun getNews(): NewsDto.Response
}
