package com.noatnoat.genres.data.datasource.api.model.search_model

import com.noatnoat.genres.data.datasource.database.model.search_model.NameEntity
import com.noatnoat.genres.domain.model.search_model.NameDomain

data class Name(
    val en: String?
) {
    fun toDomain(): NameDomain {
        return NameDomain(
            en = en
        )
    }

    fun toEntity(): NameEntity {
        return NameEntity(
            en = en
        )
    }
}
