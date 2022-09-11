package ru.axdar.news

import ru.axdar.data.news.repository.News

sealed class NewsState {
    object Loading: NewsState()
    data class Error(val throwable: Throwable): NewsState()
    data class Content(val newsList: List<News>): NewsState()
}
