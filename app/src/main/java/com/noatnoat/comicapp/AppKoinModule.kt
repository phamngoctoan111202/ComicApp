package com.noatnoat.comicapp


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.noatnoat.comicapp.api.AuthenticationInterceptor
import com.noatnoat.comicapp.api.UserAgentInterceptor
import com.noatnoat.discover.data.datasource.api.service.ComicRetrofitService
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val appModule = module {

    single { AuthenticationInterceptor(BuildConfig.GRADLE_API_TOKEN) }

    singleOf(::UserAgentInterceptor)

    single {
        HttpLoggingInterceptor { message ->
            Timber.d("Http: $message")
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<AuthenticationInterceptor>())
            .addInterceptor(get<UserAgentInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.mangadex.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(ComicRetrofitService::class.java)
    }
}
