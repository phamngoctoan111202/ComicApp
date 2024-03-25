package com.noatnoat.genres.data.datasource.database.model.search_model

import com.google.gson.annotations.SerializedName
import com.noatnoat.genres.domain.model.search_model.AltTitleDomain

data class AltTitleEntity(
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
{
    fun toDomain(): AltTitleDomain {
        return AltTitleDomain(
            en = en,
            ja = ja,
            es = es,
            esLa = esLa,
            fa = fa,
            fr = fr,
            id = id,
            it = it,
            jaRo = jaRo,
            ko = ko,
            koRo = koRo,
            lt = lt,
            mn = mn,
            ms = ms,
            ne = ne,
            pl = pl,
            ptBr = ptBr,
            ru = ru,
            th = th,
            tl = tl,
            tr = tr,
            uk = uk,
            vi = vi,
            zh = zh,
            zhHk = zhHk
        )
    }

}