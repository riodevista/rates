package ru.dmitrybochkov.rates.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dmitry Bochkov on 14.11.2019
 */

@Module
class AppModule(private val appContext: Context) {

    @Provides
    @Singleton
    internal fun provideAppContext(): Context {
        return appContext
    }
}
