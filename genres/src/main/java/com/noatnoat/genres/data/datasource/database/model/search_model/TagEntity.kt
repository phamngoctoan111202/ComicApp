package com.noatnoat.genres.data.datasource.database.model.search_model

import com.noatnoat.genres.domain.model.search_model.TagDomain

data class TagEntity(
    val attributes: AttributesXEntity?,
    val id: String?,
    val relationships: List<Any>?,
    val type: String?
) {
    fun toDomain(): TagDomain {
        return TagDomain(
            attributes = attributes?.toDomain(),
            id = id,
            relationships = relationships,
            type = type
        )
    }
}
