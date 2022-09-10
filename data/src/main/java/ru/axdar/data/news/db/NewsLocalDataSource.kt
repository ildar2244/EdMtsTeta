package ru.axdar.data.news.db

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.axdar.data.main.AppDatabase
import ru.axdar.data.utils.Result
import ru.axdar.data.utils.runOperationCatching

class NewsLocalDataSource(private val context: Context) {
    suspend fun getNews(): Result<NewsEntity, Throwable> {
        return runOperationCatching {
            delay(1000L)
            withContext(Dispatchers.IO) {
                AppDatabase.getDatabase(context).newsDao().getById(1) ?: NewsEntity(2)
            }
        }
    }
}
