package com.noatnoat.genres.data.datasource.api.model.chapter_model

import com.noatnoat.genres.domain.model.chapter_model.ChapterImgDomain


data class Chapter(
    val data: List<String?>,
    val dataSaver: List<String?>,
    val hash: String?
) {
    fun toDomain(): ChapterImgDomain {
        return ChapterImgDomain(
            data = this.data,
            dataSaver = this.dataSaver,
            hash = this.hash
        )
    }


}
