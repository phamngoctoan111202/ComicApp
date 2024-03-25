package com.noatnoat.base

import com.noatnoat.base.presentation.nav.NavManager
import com.noatnoat.base.presentation.presentationModule
import org.koin.dsl.module

val baseModule = module {

    single { NavManager() }
}
