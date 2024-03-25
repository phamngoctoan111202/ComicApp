package com.noatnoat.discover.data

import androidx.room.Room
import com.noatnoat.discover.data.datasource.api.service.ComicRetrofitService
import com.noatnoat.discover.data.datasource.database.ComicDatabase
import com.noatnoat.discover.data.repository.ComicRepositoryImpl
import com.noatnoat.discover.domain.repository.ComicRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single { get<Retrofit>().create(ComicRetrofitService::class.java) }


    single<ComicRepository> { ComicRepositoryImpl(get(), get()) }


    single {
        Room.databaseBuilder(
            get(),
            ComicDatabase::class.java,
            "Comics.db",
        ).build()
    }

    single { get<ComicDatabase>().comics() }
}
