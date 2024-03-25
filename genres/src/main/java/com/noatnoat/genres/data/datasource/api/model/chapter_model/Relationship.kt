package com.noatnoat.genres.data.datasource.api.model.chapter_model

import com.noatnoat.genres.data.datasource.database.model.chapter_model.RelationshipEntity
import com.noatnoat.genres.domain.model.chapter_model.RelationshipDomain


data class Relationship(
    val id: String?,
    val type: String?
) {
    fun toDomain(): RelationshipDomain {
        return RelationshipDomain(
            id = this.id,
            type = this.type
        )
    }

    fun toEntity(): RelationshipEntity {
        return RelationshipEntity(
            id = this.id,
            type = this.type
        )
    }
}
