package com.movies.themoviestestapp.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppModules : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppModules)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }
}