package com.noatnoat.genres.data.datasource.api.model.search_model

import com.noatnoat.genres.data.datasource.database.model.search_model.AltTitleEntity
import com.noatnoat.genres.data.datasource.database.model.search_model.AttributesEntity
import com.noatnoat.genres.data.datasource.database.model.search_model.TagEntity
import com.noatnoat.genres.domain.model.search_model.AltTitleDomain
import com.noatnoat.genres.domain.model.search_model.AttributesDomain
import com.noatnoat.genres.domain.model.search_model.TagDomain


data class Attributes(
    val altTitles: List<AltTitle?>,
    val availableTranslatedLanguages: List<String?>,
    val chapterNumbersResetOnNewVolume: Boolean?,
    val contentRating: String?,
    val createdAt: String?,
    val description: Description?,
    val isLocked: Boolean?,
    val lastChapter: String?,
    val lastVolume: String?,
    val latestUploadedChapter: String?,
    val links: Links?,
    val originalLanguage: String?,
    val publicationDemographic: String?,
    val state: String?,
    val status: String?,
    val tags: List<Tag?>,
    val title: Title?,
    val updatedAt: String?,
    val version: Int?,
    val year: Int?
) {
    fun toDomain(): AttributesDomain {
        return AttributesDomain(
            altTitleDomains = altTitles.toAltTitleDomain(),
            availableTranslatedLanguages = availableTranslatedLanguages,
            chapterNumbersResetOnNewVolume = chapterNumbersResetOnNewVolume,
            contentRating = contentRating,
            createdAt = createdAt,
            descriptionDomain = description?.toDomain(),
            isLocked = isLocked,
            lastChapter = lastChapter,
            lastVolume = lastVolume,
            latestUploadedChapter = latestUploadedChapter,
            linksDomain = links?.toDomain(),
            originalLanguage = originalLanguage,
            publicationDemographic = publicationDemographic,
            state = state,
            status = status,
            tagDomains = tags.toTagDomain(),
            titleDomain = title?.toDomain(),
            updatedAt = updatedAt,
            version = version,
            year = year
        )
    }

    fun toEntity(): AttributesEntity {
        return AttributesEntity(
            altTitleEntities = altTitles.toAltTitleEntity(),
            availableTranslatedLanguages = availableTranslatedLanguages,
            chapterNumbersResetOnNewVolume = chapterNumbersResetOnNewVolume,
            contentRating = contentRating,
            createdAt = createdAt,
            descriptionEntity = description?.toEntity(),
            isLocked = isLocked,
            lastChapter = lastChapter,
            lastVolume = lastVolume,
            latestUploadedChapter = latestUploadedChapter,
            linksEntity = links?.toEntity(),
            originalLanguage = originalLanguage,
            publicationDemographic = publicationDemographic,
            state = state,
            status = status,
            tagEntities = tags.toTagEntity(),
            titleEntity = title?.toEntity(),
            updatedAt = updatedAt,
            version = version,
            year = year
        )
    }

    fun List<AltTitle?>.toAltTitleDomain(): List<AltTitleDomain?> {
        return map { it?.toDomain() }
    }

    fun List<Tag?>.toTagDomain(): List<TagDomain?> {
        return map { it?.toDomain() }
    }

    fun List<AltTitle?>.toAltTitleEntity(): List<AltTitleEntity?> {
        return map { it?.toEntity() }
    }

    fun List<Tag?>.toTagEntity(): List<TagEntity?> {
        return map { it?.toEntity() }
    }

}