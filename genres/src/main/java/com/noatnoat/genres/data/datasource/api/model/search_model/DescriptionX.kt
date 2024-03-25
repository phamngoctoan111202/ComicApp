package com.noatnoat.genres.data.datasource.api.model.search_model

import com.noatnoat.genres.data.datasource.database.model.search_model.DescriptionXEntity
import com.noatnoat.genres.domain.model.search_model.DescriptionXDomain


class DescriptionX {
    fun toDomain(): DescriptionXDomain {
        return DescriptionXDomain()
    }

    fun toEntity(): DescriptionXEntity {
        return DescriptionXEntity()
    }
}
