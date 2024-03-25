package com.noatnoat.genres.data.datasource.api.model.chapter_model

import com.noatnoat.genres.data.datasource.database.model.chapter_model.AttributesEntity
import com.noatnoat.genres.domain.model.chapter_model.AttributesDomain


data class Attributes(
    val chapter: String?,
    val createdAt: String?,
    val externalUrl: Any?,
    val pages: Int?,
    val publishAt: String?,
    val readableAt: String?,
    val title: String?,
    val translatedLanguage: String?,
    val updatedAt: String?,
    val version: Int?,
    val volume: Any?
) {
    fun toDomain(): AttributesDomain {
        return AttributesDomain(
            chapter = this.chapter ?: "",
            createdAt = this.createdAt ?: "",
            externalUrl = this.externalUrl?.toString() ?: "",
            pages = this.pages ?: 0,
            publishAt = this.publishAt ?: "",
            readableAt = this.readableAt ?: "",
            title = this.title ?: "",
            translatedLanguage = this.translatedLanguage ?: "",
            updatedAt = this.updatedAt ?: "",
            version = this.version ?: 0,
            volume = this.volume?.toString() ?: ""
        )
    }


    fun toEntity(): AttributesEntity {
        return AttributesEntity(
            chapter = this.chapter ?: "",
            createdAt = this.createdAt ?: "",
            externalUrl = this.externalUrl?.toString() ?: "",
            pages = this.pages ?: 0,
            publishAt = this.publishAt ?: "",
            readableAt = this.readableAt ?: "",
            title = this.title ?: "",
            translatedLanguage = this.translatedLanguage ?: "",
            updatedAt = this.updatedAt ?: "",
            version = this.version ?: 0,
            volume = this.volume?.toString() ?: ""
        )
    }

}
