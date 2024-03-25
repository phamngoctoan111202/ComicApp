package com.noatnoat.discover.data.datasource.api.service

import com.noatnoat.discover.data.datasource.api.response.ApiChapterImageResponse
import com.noatnoat.discover.data.datasource.api.response.ApiChapterResponse
import com.noatnoat.discover.data.datasource.api.response.ApiSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicRetrofitService {
    @GET("manga/{id}/feed")
    suspend fun getMangaChaptersIdAsync(
        @Path("id") mangaId: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("includeFuturePublishAt") includeFuturePublishAt: Int,
        @Query("includeEmptyPages") includeEmptyPages: Int,
        @Query("includeExternalUrl") includeExternalUrl: Int
    ): Response<ApiChapterResponse>

    @GET("/at-home/server/{id}")
    suspend fun getChapterImgAsync(
        @Path("id") chapterId: String
    ): Response<ApiChapterImageResponse>


    @GET("manga")
    suspend fun getMangaBySearchAsync(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("includedTags[]") includedTags: List<String>,
        @Query("excludedTags[]") excludedTags: List<String>,
        @Query("includes[]") includes: List<String>,
        @Query("title") title: String // Thêm tham số title vào yêu cầu GET
    ): Response<ApiSearchResponse>

}
