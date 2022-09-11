package ru.axdar.data.news.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.axdar.data.news.repository.News

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "author") val author: String = "",
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "content") val content: String = "",
    @ColumnInfo(name = "imageUrl") val imageUrl: String = "",
)

internal fun List<NewsEntity?>.toDomain() = this.map { entity ->
    News(
        id = entity?.id ?: "",
        author = entity?.author ?: "",
        title = entity?.title ?: "",
        content = entity?.content ?: "",
        imageUrl = entity?.imageUrl ?: "",
    )
}
