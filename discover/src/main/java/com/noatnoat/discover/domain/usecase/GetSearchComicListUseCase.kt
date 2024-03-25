package com.noatnoat.discover.domain.usecase

import com.noatnoat.discover.domain.model.search_model.MangaData
import com.noatnoat.discover.domain.repository.ComicRepository

internal class GetSearchComicListUseCase(
    private val comicRepository: ComicRepository,
) {

    suspend operator fun invoke(limit: Int, offset: Int, includedTags: List<String>, excludedTags: List<String>, title: String): MangaData? {

        return comicRepository.getMangaList(limit, offset, includedTags, excludedTags, title)
    }
}
