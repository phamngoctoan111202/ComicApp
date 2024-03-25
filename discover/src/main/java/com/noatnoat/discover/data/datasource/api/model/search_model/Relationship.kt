package com.noatnoat.discover.data.datasource.api.model.search_model

import com.noatnoat.discover.data.datasource.database.model.search_model.RelationshipEntity
import com.noatnoat.discover.domain.model.search_model.RelationshipDomain

data class Relationship(
    val attributes: AttributesXX?,
    val id: String?,
    val related: String?,
    val type: String?
) {
    fun toDomain(): RelationshipDomain {
        return RelationshipDomain(
            id = id,
            related = related,
            type = type,
            attributes = attributes?.toDomain()
        )
    }

    fun toEntity(): RelationshipEntity {
        return RelationshipEntity(
            id = id,
            related = related,
            type = type,
            attributes = attributes?.toEntity()
        )
    }
}