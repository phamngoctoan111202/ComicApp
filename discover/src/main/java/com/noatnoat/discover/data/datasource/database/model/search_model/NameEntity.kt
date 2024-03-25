package com.noatnoat.discover.data.datasource.database.model.search_model

import com.noatnoat.discover.domain.model.search_model.NameDomain

data class NameEntity(
    val en: String?
) {
    fun toDomain(): NameDomain {
        return NameDomain(
            en = en
        )
    }
}
