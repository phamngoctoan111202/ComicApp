package com.noatnoat.genres.data.repository

import android.util.Log
import com.noatnoat.genres.data.datasource.api.response.ApiChapterImageResponse
import com.noatnoat.genres.data.datasource.api.response.ApiChapterResponse
import com.noatnoat.genres.data.datasource.api.response.ApiSearchResponse
import com.noatnoat.genres.data.datasource.api.service.ComicRetrofitService
import com.noatnoat.genres.data.datasource.database.ComicDao
import com.noatnoat.genres.domain.model.chapter_model.ChapterIdData
import com.noatnoat.genres.domain.model.chapter_model.ChapterImgDomain
import com.noatnoat.genres.domain.model.search_model.MangaData
import com.noatnoat.genres.domain.repository.ComicRepository


class ComicRepositoryImpl(private val service: ComicRetrofitService,
                          private val comicDao: ComicDao
) : ComicRepository {

    override suspend fun getMangaList(
        limit: Int?,
        offset: Int?,
        includedTags: List<String>,
        excludedTags: List<String>
    ): MangaData? {

        var apiComicResponse: ApiSearchResponse? = null

        try {
            val response = service.getMangaBySearchAsync(
                limit,
                offset,
                includedTags,
                excludedTags,
                includes = listOf("author", "artist", "cover_art")
            )
            apiComicResponse = response.body()

            if (apiComicResponse != null) {
                comicDao.clearSearchData()
                comicDao.insertSearchResponse(apiComicResponse.toEntity())
            }
        } catch (e: Exception) {

        }

        val cachedData = comicDao.getSearchResponse()

        if (cachedData != null) {
            return cachedData.toDomain()
        }

        return (apiComicResponse?.toDomain() ?: MangaData(
            data = emptyList(),
            limit = limit,
            offset = offset,
            response = "No response",
            result = "No result",
            total = 0
        ))
    }



    override suspend fun getMangaChapterIds(mangaId: String, limit: Int, offset: Int): ChapterIdData? {
        var apiComicResponse: ApiChapterResponse? = null

        try {
            val response = service.getMangaChaptersIdAsync(mangaId, limit, offset, includeFuturePublishAt = 0, includeEmptyPages = 0, includeExternalUrl = 0)
            apiComicResponse = response.body()

            if (apiComicResponse != null) {
                comicDao.insertChapterResponse(apiComicResponse.toEntity(mangaId))
            }
        } catch (e: Exception) {

        }

        val cachedData = comicDao.getChapterResponse(mangaId)
        if(cachedData != null) {
            return cachedData.toDomain()

        }

        return apiComicResponse?.toDomain()
            ?: ChapterIdData(
                data = emptyList(),
                limit = limit,
                offset = offset,
                response = "No response",
                result = "No result",
                total = 0
            )
    }


    override suspend fun getChapters(chapterId: String): ChapterImgDomain? {
        var apiComicResponse: ApiChapterImageResponse? = null

        try {
            val response = service.getChapterImgAsync(chapterId)
            apiComicResponse = response.body()

            if (apiComicResponse != null) {
                apiComicResponse.toEntity(chapterId)?.let { comicDao.insertChapterResponseImage(it) }
            }
        } catch (e: Exception) {

        }

        val cachedData = comicDao.getChapterResponseImage(chapterId)

        if(cachedData != null) {
            return cachedData.toDomain()
        }


        return apiComicResponse?.toDomain() ?: ChapterImgDomain(
            data = emptyList(),
            dataSaver = emptyList(),
            hash = ""
        )
    }



}
