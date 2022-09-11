package ru.axdar.data.news.remote

import kotlinx.coroutines.delay
import ru.axdar.data.main.NetworkClient
import ru.axdar.data.utils.runOperationCatching
import ru.axdar.data.utils.Result

class NewsRemoteDataSource {

    suspend fun getNews(): Result<NewsDto.Response, Throwable> {
        return runOperationCatching {
            delay(3000L)
            NetworkClient.create().getNews()
        }
    }
}