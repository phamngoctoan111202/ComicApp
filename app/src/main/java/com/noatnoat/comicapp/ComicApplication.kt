package com.noatnoat.comicapp

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.noatnoat.base.baseModule
import com.noatnoat.comicapp.appModule
import com.noatnoat.discover.discoverKoinModule
import com.noatnoat.genres.genresKoinModule
import com.noatnoat.library.libraryKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

class ComicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
        initDynamicColorScheme()
    }

    private fun initDynamicColorScheme() {

        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@ComicApplication)

            modules(appModule)
            modules(baseModule)
            modules(discoverKoinModule)
            modules(genresKoinModule)
            modules(libraryKoinModule)
//            modules(featureFavouriteModules)
//            modules(com.igorwojda.showcase.album.featureAlbumModules)
//            modules(featureProfilesModules)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
