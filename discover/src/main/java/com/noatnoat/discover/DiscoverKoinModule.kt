package com.noatnoat.discover

import com.noatnoat.discover.presentation.presentationModule
import com.noatnoat.discover.data.dataModule
import com.noatnoat.discover.domain.domainModule

val discoverKoinModule = listOf (
    dataModule,
    domainModule,
    presentationModule,
)
