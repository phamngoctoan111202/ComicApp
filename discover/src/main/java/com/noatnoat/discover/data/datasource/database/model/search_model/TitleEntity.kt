package com.noatnoat.discover.data.datasource.database.model.search_model

import com.noatnoat.discover.domain.model.search_model.TitleDomain

data class TitleEntity(
    val en: String?
) {
    fun toDomain(): TitleDomain {
        return TitleDomain(
            en = en
        )
    }
}
