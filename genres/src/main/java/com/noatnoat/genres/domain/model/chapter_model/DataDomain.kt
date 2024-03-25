package com.noatnoat.genres.domain.model.chapter_model

data class DataDomain(
    val attributesDomain: AttributesDomain?,
    val id: String?,
    val relationshipDomains: List<RelationshipDomain?>,
    val type: String?
)