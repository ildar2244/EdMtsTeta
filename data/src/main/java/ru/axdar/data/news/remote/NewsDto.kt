package ru.axdar.data.news.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.axdar.data.news.repository.News

class NewsDto {
    @Parcelize
    class Request : Parcelable

    @Parcelize
    data class Response(
        @SerializedName("data") val data: List<ResponseParamData>
    ) : Parcelable

    @Parcelize
    data class ResponseParamData(
        @SerializedName("id") val id: String,
        @SerializedName("author") val author: String = "",
        @SerializedName("title") val title: String = "",
        @SerializedName("content") val content: String = "",
        @SerializedName("imageUrl") val imageUrl: String = "",
    ) : Parcelable
}

internal fun NewsDto.Response.toDomain(): List<News> {
    return this.data.map { dto ->
        News(
            id = dto.id,
            author = dto.author,
            title = dto.title,
            content = dto.content,
            imageUrl = dto.imageUrl,
        )
    }
}