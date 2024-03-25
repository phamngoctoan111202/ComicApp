package com.noatnoat.discover.data.datasource.database.model.search_model

import com.noatnoat.discover.domain.model.search_model.BiographyDomain

data class BiographyEntity(
    val en: String?,
    val zh: String?
) {
    fun toDomain(): BiographyDomain {
        return BiographyDomain(
            en = this.en,
            zh = this.zh
        )
    }
}
