package io.compwiz.countrylister

import android.app.Application
import io.compwiz.countrylister.data.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CompWiz: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CompWiz)
            modules(listOf(networkingModule))
        }
    }
}