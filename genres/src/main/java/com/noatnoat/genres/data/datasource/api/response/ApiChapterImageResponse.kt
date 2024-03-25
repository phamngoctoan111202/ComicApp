package com.noatnoat.genres.data.datasource.api.response

import com.noatnoat.genres.data.datasource.api.model.chapter_model.Chapter
import com.noatnoat.genres.data.datasource.database.model.chapter_model.ChapterResponseImageEntity
import com.noatnoat.genres.domain.model.chapter_model.ChapterImgDomain

data class ApiChapterImageResponse(
    val baseUrl: String?,
    val chapter: Chapter?,
    val result: String?
) {
    fun toDomain(): ChapterImgDomain? {
        return chapter?.let {
            ChapterImgDomain(
                data = it.data,
                dataSaver = it.dataSaver,
                hash = it.hash
            )
        }
    }
    fun toEntity(chapterId: String): ChapterResponseImageEntity? {
        return chapter?.let {
            ChapterResponseImageEntity(
                chapterId = chapterId,
                data = it.data,
                dataSaver = it.dataSaver,
                hash = it.hash
            )
        }
    }

}
