package com.noatnoat.discover.data.datasource.api.model.search_model

import com.noatnoat.discover.data.datasource.database.model.search_model.AttributesXEntity
import com.noatnoat.discover.domain.model.search_model.AttributesXDomain

data class AttributesX(
    val description: DescriptionX,
    val group: String,
    val name: Name,
    val version: Int
) {
    fun toDomain(): AttributesXDomain {
        return AttributesXDomain(
            description = description.toDomain(),
            group = group,
            nameDomain = name.toDomain(),
            version = version
        )
    }

    fun toEntity(): AttributesXEntity {
        return AttributesXEntity(
            description = description.toEntity(),
            group = group,
            nameEntity = name.toEntity(),
            version = version
        )
    }
}
