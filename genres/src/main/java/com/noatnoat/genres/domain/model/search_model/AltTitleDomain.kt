package com.noatnoat.genres.domain.model.search_model

import com.google.gson.annotations.SerializedName

data class AltTitleDomain(
    @SerializedName("en") val en: String?,
    @SerializedName("es") val es: String?,
    @SerializedName("es-la") val esLa: String?,
    @SerializedName("fa") val fa: String?,
    @SerializedName("fr") val fr: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("it") val it: String?,
    @SerializedName("ja") val ja: String?,
    @SerializedName("ja-ro") val jaRo: String?,
    @SerializedName("ko") val ko: String?,
    @SerializedName("ko-ro") val koRo: String?,
    @SerializedName("lt") val lt: String?,
    @SerializedName("mn") val mn: String?,
    @SerializedName("ms") val ms: String?,
    @SerializedName("ne") val ne: String?,
    @SerializedName("pl") val pl: String?,
    @SerializedName("pt-br") val ptBr: String?,
    @SerializedName("ru") val ru: String?,
    @SerializedName("th") val th: String?,
    @SerializedName("tl") val tl: String?,
    @SerializedName("tr") val tr: String?,
    @SerializedName("uk") val uk: String?,
    @SerializedName("vi") val vi: String?,
    @SerializedName("zh") val zh: String?,
    @SerializedName("zh-hk") val zhHk: String?
)
