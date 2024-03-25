package com.noatnoat.discover.domain

import com.noatnoat.discover.domain.usecase.GetDetailComicUseCase
import com.noatnoat.discover.domain.usecase.GetImgComicUseCase
import com.noatnoat.discover.domain.usecase.GetSearchComicListUseCase
import org.koin.dsl.module

internal val domainModule = module {

    single {
        GetSearchComicListUseCase(get())
    }

    single {
        GetDetailComicUseCase(get())
    }

    single {
        GetImgComicUseCase(get())
    }
}
