package com.noatnoat.genres.data.datasource.database.model.search_model

import com.noatnoat.genres.domain.model.search_model.TitleDomain

data class TitleEntity(
    val en: String?
) {
    fun toDomain(): TitleDomain {
        return TitleDomain(
            en = en
        )
    }
}
