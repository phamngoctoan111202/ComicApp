package com.noatnoat.genres.data.datasource.database.model.search_model

import com.noatnoat.genres.domain.model.search_model.RelationshipDomain

data class RelationshipEntity(
    val attributes: AttributesXXEntity?,
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
}