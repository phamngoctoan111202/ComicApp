package com.noatnoat.discover.data.datasource.database.model.search_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.noatnoat.discover.domain.model.search_model.DataDomain
import com.noatnoat.discover.domain.model.search_model.MangaData

@Entity(tableName = "search")
data class SearchResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "data")
    val data: List<SearchDataEntity>,
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
){
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

    fun List<SearchDataEntity>.toDomain(): List<DataDomain> {
        return map { it.toDomain() }
    }

}
