package com.noatnoat.genres.data.datasource.api.model.search_model

import com.noatnoat.genres.data.datasource.database.model.search_model.TitleEntity
import com.noatnoat.genres.domain.model.search_model.TitleDomain


data class Title(
    val en: String?
) {
    fun toDomain(): TitleDomain {
        return TitleDomain(
            en = en
        )
    }

    fun toEntity(): TitleEntity {
        return TitleEntity(
            en = en
        )
    }
}
