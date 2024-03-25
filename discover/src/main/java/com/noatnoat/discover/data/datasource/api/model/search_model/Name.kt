package com.noatnoat.discover.data.datasource.api.model.search_model

import com.noatnoat.discover.data.datasource.database.model.search_model.NameEntity
import com.noatnoat.discover.domain.model.search_model.NameDomain

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
