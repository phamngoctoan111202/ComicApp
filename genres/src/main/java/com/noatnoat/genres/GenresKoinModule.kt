package com.noatnoat.genres

import com.noatnoat.genres.data.dataModule
import com.noatnoat.genres.domain.domainModule
import com.noatnoat.genres.presentation.presentationModule


val genresKoinModule = listOf (
    dataModule,
    domainModule,
    presentationModule,
)
