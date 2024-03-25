package com.noatnoat.genres.data.datasource.api.response


import com.noatnoat.genres.data.datasource.api.model.chapter_model.Data
import com.noatnoat.genres.data.datasource.database.model.chapter_model.ChapterResponseEntity
import com.noatnoat.genres.domain.model.chapter_model.ChapterIdData

data class ApiChapterResponse(
    val data: List<Data>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int
) {
    fun toDomain(): ChapterIdData {
        return ChapterIdData(
            data = data.map { it.toDomain() },
            limit = limit,
            offset = offset,
            response = response,
            result = result,
            total = total
        )
    }

    fun toEntity(mangaId: String): ChapterResponseEntity {
        return ChapterResponseEntity(
            mangaId = mangaId,
            data = data.map { it.toEntity() },
            limit = limit,
            offset = offset,
            response = response,
            result = result,
            total = total
        )
    }

}

