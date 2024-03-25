package com.noatnoat.base.data.readingdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters")
data class Chapter(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "coverImageUrl") val coverImageUrl: String,
    @ColumnInfo(name = "comicName") val comicName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "status") val status: String,

    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "lastChapter") val lastChapter: String,

    @ColumnInfo(name = "lastVolume") val lastVolume: String,

    @ColumnInfo(name = "lastVolume") val publishDemographic: String,

    @ColumnInfo(name = "lastVolume") val orginalLanguage: String,

    @ColumnInfo(name = "imageUrls") val imageUrls: List<String>
)
