package com.noatnoat.discover.presentation

import coil.ImageLoader
import com.noatnoat.discover.presentation.detailscreen.DetailViewModel
import com.noatnoat.discover.presentation.discoverscreen.DiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule = module {

    viewModel { DiscoverViewModel(get(), get())}
    viewModel {DetailViewModel(get(), get(), get())}
}
