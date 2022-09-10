package ru.axdar.data.news.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.axdar.data.news.repository.News

@Entity(tableName = "news")
data class NewsEntity(@PrimaryKey @ColumnInfo(name = "id") val id: Int)

fun NewsEntity.toDomain() = News(this.id)