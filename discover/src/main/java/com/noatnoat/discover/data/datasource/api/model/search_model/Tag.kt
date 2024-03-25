package com.noatnoat.discover.data.datasource.api.model.search_model

import com.noatnoat.discover.data.datasource.database.model.search_model.TagEntity
import com.noatnoat.discover.domain.model.search_model.TagDomain

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
