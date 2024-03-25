package com.noatnoat.genres.domain.usecase

import com.noatnoat.genres.domain.model.chapter_model.ChapterImgDomain
import com.noatnoat.genres.domain.repository.ComicRepository

class GetImgComicUseCase(private val comicRepository: ComicRepository) {
    suspend operator fun invoke(chapterId: String): ChapterImgDomain? {
        return comicRepository.getChapters(chapterId)
    }
}