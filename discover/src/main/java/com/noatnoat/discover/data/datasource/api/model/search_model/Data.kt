package com.noatnoat.discover.data.datasource.api.model.search_model

import com.noatnoat.discover.data.datasource.database.model.search_model.SearchDataEntity
import com.noatnoat.discover.domain.model.search_model.DataDomain

data class Data(
    val attributes: Attributes?,
    val id: String?,
    val relationships: List<Relationship?>,
    val type: String?
) {
    fun toDomain(): DataDomain {
        return DataDomain(
            attributesDomain = attributes?.toDomain(),
            id = id,
            relationshipDomains = relationships.map { it?.toDomain() },
            type = type
        )
    }

    fun toEntity(): SearchDataEntity {
        return SearchDataEntity(
            attributes = attributes?.toEntity(),
            id = id,
            relationships = relationships.map { it?.toEntity() },
            type = type
        )
    }


}
