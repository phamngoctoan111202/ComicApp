package com.noatnoat.discover.data.datasource.database.model.search_model

import com.noatnoat.discover.domain.model.search_model.DataDomain

data class SearchDataEntity(
    val attributes: AttributesEntity?,
    val id: String?,
    val relationships: List<RelationshipEntity?>,
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
}
