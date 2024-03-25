package com.noatnoat.genres.domain.model.search_model

import com.noatnoat.genres.data.datasource.api.model.search_model.AttributesXX

data class RelationshipDomain(
    val attributes: AttributesXXDomain?,
    val id: String?,
    val related: String?,
    val type: String?
)