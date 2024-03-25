package com.noatnoat.genres.presentation

import androidx.lifecycle.SavedStateHandle
import com.noatnoat.genres.presentation.detailscreen.DetailViewModel
import com.noatnoat.genres.presentation.genresscreen.GenresViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val presentationModule = module {

    viewModel { (state: SavedStateHandle) -> GenresViewModel(get(), get(), state) }
    viewModel {DetailViewModel(get(), get(), get())}
}
