package com.noatnoat.genres.domain.model.search_model

data class MangaData(
    val data: List<DataDomain?>,
    val limit: Int?,
    val offset: Int?,
    val response: String,
    val result: String,
    val total: Int
)