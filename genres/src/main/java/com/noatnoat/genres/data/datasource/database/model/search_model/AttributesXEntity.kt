package com.noatnoat.genres.data.datasource.database.model.search_model

import com.noatnoat.genres.domain.model.search_model.AttributesXDomain

data class AttributesXEntity(
    val description: DescriptionXEntity,
    val group: String,
    val nameEntity: NameEntity,
    val version: Int
) {
    fun toDomain(): AttributesXDomain {
        return AttributesXDomain(
            description = description.toDomain(),
            group = group,
            nameDomain = nameEntity.toDomain(),
            version = version
        )
    }
}
