package com.noatnoat.genres.domain.repository

import com.noatnoat.genres.domain.model.chapter_model.ChapterIdData
import com.noatnoat.genres.domain.model.chapter_model.ChapterImgDomain
import com.noatnoat.genres.domain.model.search_model.MangaData

interface ComicRepository {
    suspend fun getMangaList(limit: Int?,
                             offset: Int?,
                             includedTags: List<String>,
                             excludedTags: List<String>): MangaData?

    suspend fun getMangaChapterIds(mangaId: String, limit: Int, offset: Int): ChapterIdData?

    suspend fun getChapters(chapterId: String): ChapterImgDomain?
}