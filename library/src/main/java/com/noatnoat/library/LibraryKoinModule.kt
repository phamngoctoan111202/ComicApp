package com.noatnoat.library

import com.noatnoat.base.presentation.presentationModule
import com.noatnoat.library.data.dataModule
import com.noatnoat.library.domain.domainModule


val libraryKoinModule = listOf (
    dataModule,
    domainModule,
    presentationModule,
)
