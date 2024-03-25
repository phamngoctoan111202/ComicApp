package com.noatnoat.discover.data.datasource.database.model.chapter_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.noatnoat.discover.domain.model.chapter_model.ChapterImgDomain

@Entity(tableName = "chapter_image_response")
data class ChapterResponseImageEntity(
    @PrimaryKey
    val chapterId: String,
    @ColumnInfo(name = "data")
    val data: List<String?>,
    @ColumnInfo(name = "data_saver")
    val dataSaver: List<String?>,
    @ColumnInfo(name = "hash")
    val hash: String?
) {
    fun toDomain(): ChapterImgDomain {
        return ChapterImgDomain(
            data = this.data,
            dataSaver = this.dataSaver,
            hash = this.hash
        )
    }
}

