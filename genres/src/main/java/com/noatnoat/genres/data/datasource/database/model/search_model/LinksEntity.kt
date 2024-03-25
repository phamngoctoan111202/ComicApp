package com.noatnoat.genres.data.datasource.database.model.search_model

import com.noatnoat.genres.domain.model.search_model.LinksDomain

data class LinksEntity(
    val al: String?,
    val amz: String?,
    val ap: String?,
    val bw: String?,
    val cdj: String?,
    val ebj: String?,
    val engtl: String?,
    val kt: String?,
    val mal: String?,
    val mu: String?,
    val nu: String?,
    val raw: String?
) {
    fun toDomain(): LinksDomain? {
        return LinksDomain(
            al = al,
            amz = amz,
            ap = ap,
            bw = bw,
            cdj = cdj,
            ebj = ebj,
            engtl = engtl,
            kt = kt,
            mal = mal,
            mu = mu,
            nu = nu,
            raw = raw
        )
    }

}