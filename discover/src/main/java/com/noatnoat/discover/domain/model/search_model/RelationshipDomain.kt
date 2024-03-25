package com.noatnoat.discover.domain.model.search_model

import com.noatnoat.discover.data.datasource.api.model.search_model.AttributesXX

data class RelationshipDomain(
    val attributes: AttributesXXDomain?,
    val id: String?,
    val related: String?,
    val type: String?
)