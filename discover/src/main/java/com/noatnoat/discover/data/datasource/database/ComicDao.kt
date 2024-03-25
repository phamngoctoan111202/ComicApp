package com.noatnoat.discover.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noatnoat.discover.data.datasource.database.model.chapter_model.ChapterResponseEntity
import com.noatnoat.discover.data.datasource.database.model.chapter_model.ChapterResponseImageEntity
import com.noatnoat.discover.data.datasource.database.model.search_model.SearchResponseEntity

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchResponse(searchResponseEntity: SearchResponseEntity)

    @Query("SELECT * FROM search")
    suspend fun getSearchResponse(): SearchResponseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapterResponse(chapterResponseEntity: ChapterResponseEntity)

    @Query("SELECT * FROM chapter_response WHERE mangaId = :mangaId")
    suspend fun getChapterResponse(mangaId: String): ChapterResponseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapterResponseImage(chapterResponseImageEntity: ChapterResponseImageEntity)

    @Query("SELECT * FROM chapter_image_response WHERE chapterId = :chapterId")
    suspend fun getChapterResponseImage(chapterId: String): ChapterResponseImageEntity

    @Query("DELETE FROM search")
    suspend fun clearSearchData()

    @Query("DELETE FROM chapter_response")
    suspend fun clearChapterResponseData()

    @Query("DELETE FROM chapter_image_response")
    suspend fun clearChapterImgResponseData()
}
