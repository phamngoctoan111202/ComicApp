package com.noatnoat.genres.data.datasource.api.model.search_model

import com.noatnoat.genres.data.datasource.database.model.search_model.TagEntity
import com.noatnoat.genres.domain.model.search_model.TagDomain

data class Tag(
    val attributes: AttributesX?,
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

    fun toEntity(): TagEntity {
        return TagEntity(
            attributes = attributes?.toEntity(),
            id = id,
            relationships = relationships,
            type = type
        )
    }

}
