package com.noatnoat.genres.data.datasource.database.model.chapter_model

import com.noatnoat.genres.domain.model.chapter_model.DataDomain

data class ChapterDataEntity(
    val attributes: AttributesEntity?,
    val id: String?,
    val relationships: List<RelationshipEntity?>,
    val type: String?
) {
    fun toDomain(): DataDomain {
        return DataDomain(
            attributesDomain = this.attributes?.toDomain(),
            id = this.id,
            relationshipDomains = this.relationships.map { it?.toDomain() },
            type = this.type
        )
    }
}
