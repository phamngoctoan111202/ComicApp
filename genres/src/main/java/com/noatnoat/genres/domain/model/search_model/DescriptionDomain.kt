package com.noatnoat.genres.domain.model.search_model

import com.google.gson.annotations.SerializedName

data class DescriptionDomain(
    @SerializedName("en") val en: String?,
    @SerializedName("es") val es: String?,
    @SerializedName("es-la") val esLa: String?,
    @SerializedName("fr") val fr: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("it") val it: String?,
    @SerializedName("ja") val ja: String?,
    @SerializedName("ko") val ko: String?,
    @SerializedName("mn") val mn: String?,
    @SerializedName("pl") val pl: String?,
    @SerializedName("pt-br") val ptBr: String?,
    @SerializedName("ru") val ru: String?,
    @SerializedName("th") val th: String?,
    @SerializedName("uk") val uk: String?,
    @SerializedName("vi") val vi: String?,
    @SerializedName("zh") val zh: String?,
    @SerializedName("zh-hk") val zhHk: String?
)
