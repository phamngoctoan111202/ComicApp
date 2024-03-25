package com.noatnoat.genres.data.datasource.database.model.chapter_model

import com.noatnoat.genres.domain.model.chapter_model.RelationshipDomain

data class RelationshipEntity(
    val id: String?,
    val type: String?
) {
    fun toDomain(): RelationshipDomain {
        return RelationshipDomain(
            id = this.id,
            type = this.type
        )
    }
}
