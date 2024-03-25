package com.noatnoat.discover.data.datasource.database.model.chapter_model

import com.noatnoat.discover.domain.model.chapter_model.ChapterImgDomain

data class ChapterEntity(
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
