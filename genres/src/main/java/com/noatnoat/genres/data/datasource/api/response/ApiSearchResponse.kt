package com.noatnoat.genres.data.datasource.api.response

import com.noatnoat.genres.data.datasource.api.model.search_model.Data
import com.noatnoat.genres.data.datasource.database.model.search_model.SearchDataEntity
import com.noatnoat.genres.data.datasource.database.model.search_model.SearchResponseEntity
import com.noatnoat.genres.domain.model.search_model.DataDomain
import com.noatnoat.genres.domain.model.search_model.MangaData

data class ApiSearchResponse(
    val data: List<Data>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int
) {
    fun toDomain(): MangaData {
        return MangaData(
            data = data.toDomain(),
            limit = limit,
            offset = offset,
            response = response,
            result = result,
            total = total
        )
    }

    fun List<Data>.toDomain(): List<DataDomain> {
        return map { it.toDomain() }
    }

    fun toEntity(): SearchResponseEntity {
        return SearchResponseEntity(
            data = data.toEntity(),
            limit = limit,
            offset = offset,
            response = response,
            result = result,
            total = total
        )
    }

    fun List<Data>.toEntity(): List<SearchDataEntity> {
        return map { it.toEntity() }
    }


}
