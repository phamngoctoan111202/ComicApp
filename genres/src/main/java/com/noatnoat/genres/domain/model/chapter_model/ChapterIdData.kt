package com.noatnoat.genres.domain.model.chapter_model

data class ChapterIdData(
    val data: List<DataDomain>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int
)