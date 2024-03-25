package com.noatnoat.genres.data.datasource.api.model.search_model

import com.google.gson.annotations.SerializedName
import com.noatnoat.genres.data.datasource.database.model.search_model.DescriptionEntity
import com.noatnoat.genres.domain.model.search_model.DescriptionDomain

data class Description(
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
)  {
    fun toDomain(): DescriptionDomain {
        return DescriptionDomain(
            en = en,
            es = es,
            esLa = esLa,
            fr = fr,
            id = id,
            it = it,
            ja = ja,
            ko = ko,
            mn = mn,
            pl = pl,
            ptBr = ptBr,
            ru = ru,
            th = th,
            uk = uk,
            vi = vi,
            zh = zh,
            zhHk = zhHk
        )
    }

    fun toEntity(): DescriptionEntity {
        return DescriptionEntity(
            en = en,
            es = es,
            esLa = esLa,
            fr = fr,
            id = id,
            it = it,
            ja = ja,
            ko = ko,
            mn = mn,
            pl = pl,
            ptBr = ptBr,
            ru = ru,
            th = th,
            uk = uk,
            vi = vi,
            zh = zh,
            zhHk = zhHk
        )
    }
}