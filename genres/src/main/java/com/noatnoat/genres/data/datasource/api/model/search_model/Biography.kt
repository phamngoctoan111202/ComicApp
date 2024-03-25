package com.noatnoat.genres.data.datasource.api.model.search_model

import com.noatnoat.genres.data.datasource.database.model.search_model.BiographyEntity
import com.noatnoat.genres.domain.model.search_model.BiographyDomain


data class Biography(
    val en: String?,
    val zh: String?
) {
    fun toDomain(): BiographyDomain {
        return BiographyDomain(
            en = this.en,
            zh = this.zh
        )
    }

    fun toEntity(): BiographyEntity {
        return BiographyEntity(
            en = this.en,
            zh = this.zh
        )
    }

}
