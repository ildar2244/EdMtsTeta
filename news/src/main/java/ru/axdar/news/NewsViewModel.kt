package ru.axdar.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.axdar.data.news.repository.NewsRepository
import ru.axdar.data.utils.doOnError
import ru.axdar.data.utils.doOnSuccess

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Loading)

    val state = _state.asStateFlow()

    init {
        getNews()
    }

    fun refreshData() {
        _state.value = NewsState.Loading
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            repository.getNews().collect {
                it.doOnError { error ->
                    _state.emit(NewsState.Error(error))
                }.doOnSuccess { news ->
                    if (news.isEmpty()) {
                        _state.emit(NewsState.Loading)
                    } else {
                        _state.emit(NewsState.Content(news))
                    }
                }
            }
        }
    }

}