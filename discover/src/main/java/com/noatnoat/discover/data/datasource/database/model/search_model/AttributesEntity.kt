package com.noatnoat.discover.data.datasource.database.model.search_model

import com.noatnoat.discover.domain.model.search_model.AltTitleDomain
import com.noatnoat.discover.domain.model.search_model.AttributesDomain
import com.noatnoat.discover.domain.model.search_model.TagDomain

data class AttributesEntity(
    val altTitleEntities: List<AltTitleEntity?>,
    val availableTranslatedLanguages: List<String?>,
    val chapterNumbersResetOnNewVolume: Boolean?,
    val contentRating: String?,
    val createdAt: String?,
    val descriptionEntity: DescriptionEntity?,
    val isLocked: Boolean?,
    val lastChapter: String?,
    val lastVolume: String?,
    val latestUploadedChapter: String?,
    val linksEntity: LinksEntity?,
    val originalLanguage: String?,
    val publicationDemographic: String?,
    val state: String?,
    val status: String?,
    val tagEntities: List<TagEntity?>,
    val titleEntity: TitleEntity?,
    val updatedAt: String?,
    val version: Int?,
    val year: Int?
) {
    fun toDomain(): AttributesDomain {
        return AttributesDomain(
            altTitleDomains = altTitleEntities.toAltTitleDomain(),
            availableTranslatedLanguages = availableTranslatedLanguages,
            chapterNumbersResetOnNewVolume = chapterNumbersResetOnNewVolume,
            contentRating = contentRating,
            createdAt = createdAt,
            descriptionDomain = descriptionEntity?.toDomain(),
            isLocked = isLocked,
            lastChapter = lastChapter,
            lastVolume = lastVolume,
            latestUploadedChapter = latestUploadedChapter,
            linksDomain = linksEntity?.toDomain(),
            originalLanguage = originalLanguage,
            publicationDemographic = publicationDemographic,
            state = state,
            status = status,
            tagDomains = tagEntities.toTagDomain(),
            titleDomain = titleEntity?.toDomain(),
            updatedAt = updatedAt,
            version = version,
            year = year
        )
    }

    fun List<AltTitleEntity?>.toAltTitleDomain(): List<AltTitleDomain?> {
        return map { it?.toDomain() }
    }

    fun List<TagEntity?>.toTagDomain(): List<TagDomain?> {
        return map { it?.toDomain() }
    }



}