package com.noatnoat.genres.data.datasource.database.model.search_model

import com.noatnoat.genres.domain.model.search_model.AttributesXXDomain

data class AttributesXXEntity(
    val biographyEntity: BiographyEntity?,
    val booth: Any?,
    val createdAt: String?,
    val description: String?,
    val fanBox: Any?,
    val fantia: Any?,
    val fileName: String?,
    val imageUrl: Any?,
    val locale: String?,
    val melonBook: Any?,
    val name: String?,
    val naver: Any?,
    val nicoVideo: Any?,
    val pixiv: Any?,
    val skeb: Any?,
    val tumblr: Any?,
    val twitter: String?,
    val updatedAt: String?,
    val version: Int?,
    val volume: String?,
    val website: String?,
    val weibo: Any?,
    val youtube: Any?
) {
    fun toDomain(): AttributesXXDomain {
        return AttributesXXDomain(
            biography = this.biographyEntity?.toDomain(),
            booth = this.booth,
            createdAt = this.createdAt,
            description = this.description,
            fanBox = this.fanBox,
            fantia = this.fantia,
            fileName = this.fileName,
            imageUrl = this.imageUrl,
            locale = this.locale,
            melonBook = this.melonBook,
            name = this.name,
            naver = this.naver,
            nicoVideo = this.nicoVideo,
            pixiv = this.pixiv,
            skeb = this.skeb,
            tumblr = this.tumblr,
            twitter = this.twitter,
            updatedAt = this.updatedAt,
            version = this.version,
            volume = this.volume,
            website = this.website,
            weibo = this.weibo,
            youtube = this.youtube
        )
    }
}