package com.john.giveaways

import android.app.Application
import com.john.giveaways.di.applicationModule
import com.john.giveaways.di.networkModule
import com.john.giveaways.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GiveawayApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GiveawayApp)
            modules(listOf(networkModule, applicationModule, viewModule))
        }
    }
}