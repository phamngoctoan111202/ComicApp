package com.noatnoat.account

import com.noatnoat.account.data.dataModule
import com.noatnoat.account.domain.domainModule
import com.noatnoat.base.presentation.presentationModule



val accountKoinModule = listOf (
    dataModule,
    domainModule,
    presentationModule,
)
