package com.noatnoat.discover.domain.model.search_model

data class AttributesDomain(
    val altTitleDomains: List<AltTitleDomain?>,
    val availableTranslatedLanguages: List<String?>,
    val chapterNumbersResetOnNewVolume: Boolean?,
    val contentRating: String?,
    val createdAt: String?,
    val descriptionDomain: DescriptionDomain?,
    val isLocked: Boolean?,
    val lastChapter: String?,
    val lastVolume: String?,
    val latestUploadedChapter: String?,
    val linksDomain: LinksDomain?,
    val originalLanguage: String?,
    val publicationDemographic: String?,
    val state: String?,
    val status: String?,
    val tagDomains: List<TagDomain?>,
    val titleDomain: TitleDomain?,
    val updatedAt: String?,
    val version: Int?,
    val year: Int?
)