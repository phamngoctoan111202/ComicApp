package com.noatnoat.discover.domain.repository

import com.noatnoat.discover.domain.model.chapter_model.ChapterIdData
import com.noatnoat.discover.domain.model.chapter_model.ChapterImgDomain
import com.noatnoat.discover.domain.model.search_model.MangaData

interface ComicRepository {
    suspend fun getMangaList(limit: Int, offset: Int, includedTags: List<String>, excludedTags: List<String>, title: String): MangaData?
    suspend fun getMangaChapterIds(mangaId: String, limit: Int = 10, offset: Int = 0): ChapterIdData?

    suspend fun getChapters(chapterId: String): ChapterImgDomain?
}