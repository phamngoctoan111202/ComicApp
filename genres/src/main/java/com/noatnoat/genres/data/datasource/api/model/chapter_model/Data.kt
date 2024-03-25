package com.noatnoat.genres.data.datasource.api.model.chapter_model

import com.noatnoat.genres.data.datasource.database.model.chapter_model.ChapterDataEntity
import com.noatnoat.genres.domain.model.chapter_model.DataDomain


data class Data(
    val attributes: Attributes?,
    val id: String?,
    val relationships: List<Relationship?>,
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

    fun toEntity(): ChapterDataEntity {
        return ChapterDataEntity(
            attributes = this.attributes?.toEntity(),
            id = this.id,
            relationships = this.relationships.map { it?.toEntity() },
            type = this.type
        )
    }

}
