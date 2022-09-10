package ru.axdar.data.news.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.axdar.data.news.db.NewsLocalDataSource
import ru.axdar.data.news.remote.NewsRemoteDataSource
import ru.axdar.data.utils.Result

class NewsRepository(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {
    fun getNews(): Flow<Result<News, Throwable>> {
        return flow {

        }
    }
    
}
