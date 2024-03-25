package com.noatnoat.genres.domain

import com.noatnoat.genres.domain.usecase.GetDetailComicUseCase
import com.noatnoat.genres.domain.usecase.GetImgComicUseCase
import com.noatnoat.genres.domain.usecase.GetComicListByTagUseCase
import org.koin.dsl.module

internal val domainModule = module {

    single {
        GetComicListByTagUseCase(get())
    }

    single {
        GetDetailComicUseCase(get())
    }

    single {
        GetImgComicUseCase(get())
    }
}
