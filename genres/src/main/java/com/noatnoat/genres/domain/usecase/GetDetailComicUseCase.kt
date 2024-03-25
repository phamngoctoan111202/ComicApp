package com.noatnoat.genres.domain.usecase

import com.noatnoat.genres.domain.model.chapter_model.ChapterIdData
import com.noatnoat.genres.domain.repository.ComicRepository

class GetDetailComicUseCase(private val comicRepository: ComicRepository) {
    suspend operator fun invoke(mangaId: String, limit:Int, offset:Int): ChapterIdData? {
        return comicRepository.getMangaChapterIds(mangaId, limit, offset)
    }
}