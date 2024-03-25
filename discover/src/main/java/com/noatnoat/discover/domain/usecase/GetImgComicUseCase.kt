package com.noatnoat.discover.domain.usecase

import com.noatnoat.discover.domain.model.chapter_model.ChapterImgDomain
import com.noatnoat.discover.domain.repository.ComicRepository

class GetImgComicUseCase(private val comicRepository: ComicRepository) {
    suspend operator fun invoke(chapterId: String): ChapterImgDomain? {
        return comicRepository.getChapters(chapterId)
    }
}