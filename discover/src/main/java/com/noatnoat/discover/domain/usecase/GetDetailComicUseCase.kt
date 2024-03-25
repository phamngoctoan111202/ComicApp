package com.noatnoat.discover.domain.usecase

import com.noatnoat.discover.domain.model.chapter_model.ChapterIdData
import com.noatnoat.discover.domain.repository.ComicRepository

class GetDetailComicUseCase(private val comicRepository: ComicRepository) {
    suspend operator fun invoke(mangaId: String): ChapterIdData? {
        return comicRepository.getMangaChapterIds(mangaId)
    }
}