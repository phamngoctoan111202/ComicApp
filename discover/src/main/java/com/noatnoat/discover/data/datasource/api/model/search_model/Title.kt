package com.noatnoat.discover.data.datasource.api.model.search_model

import com.noatnoat.discover.data.datasource.database.model.search_model.TitleEntity
import com.noatnoat.discover.domain.model.search_model.TitleDomain

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
