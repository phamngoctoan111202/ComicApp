package com.noatnoat.discover.data.datasource.database.model.chapter_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.noatnoat.discover.domain.model.chapter_model.ChapterIdData

@Entity(tableName = "chapter_response")
data class ChapterResponseEntity(
    @PrimaryKey
    val mangaId: String,
    @ColumnInfo(name = "data")
    val data: List<ChapterDataEntity>,
    @ColumnInfo(name = "limit")
    val limit: Int,
    @ColumnInfo(name = "offset")
    val offset: Int,
    @ColumnInfo(name = "response")
    val response: String,
    @ColumnInfo(name = "result")
    val result: String,
    @ColumnInfo(name = "total")
    val total: Int
) {
    fun toDomain(): ChapterIdData {
        return ChapterIdData(
            data = data.map { it.toDomain() },
            limit = limit,
            offset = offset,
            response = response,
            result = result,
            total = total
        )
    }
}

