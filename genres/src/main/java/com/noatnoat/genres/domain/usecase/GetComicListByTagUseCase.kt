package com.noatnoat.genres.domain.usecase

import com.noatnoat.genres.domain.model.search_model.MangaData
import com.noatnoat.genres.domain.repository.ComicRepository

internal class GetComicListByTagUseCase(
    private val comicRepository: ComicRepository,
) {

    suspend operator fun invoke(limit: Int?, offset: Int?, includedTags: List<String>,
                                excludedTags: List<String>): MangaData? {

        return comicRepository.getMangaList(limit, offset,includedTags, excludedTags )
    }
}
