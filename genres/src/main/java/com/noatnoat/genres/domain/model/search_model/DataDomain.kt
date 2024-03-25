package com.noatnoat.genres.domain.model.search_model

data class DataDomain(
    val attributesDomain: AttributesDomain?,
    val id: String?,
    val relationshipDomains: List<RelationshipDomain?>,
    val type: String?
)