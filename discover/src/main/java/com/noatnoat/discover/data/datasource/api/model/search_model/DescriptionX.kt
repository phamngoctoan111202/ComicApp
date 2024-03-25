package com.noatnoat.discover.data.datasource.api.model.search_model

import com.noatnoat.discover.data.datasource.database.model.search_model.DescriptionXEntity
import com.noatnoat.discover.domain.model.search_model.DescriptionXDomain

class DescriptionX {
    fun toDomain(): DescriptionXDomain {
        return DescriptionXDomain()
    }

    fun toEntity(): DescriptionXEntity {
        return DescriptionXEntity()
    }
}
