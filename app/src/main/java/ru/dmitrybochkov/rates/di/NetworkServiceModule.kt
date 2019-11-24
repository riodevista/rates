package ru.dmitrybochkov.rates.di

import dagger.Module
import dagger.Provides
import ru.dmitrybochkov.rates.data.api.RetrofitGenerator
import ru.dmitrybochkov.rates.data.api.v1.NetworkService
import javax.inject.Singleton

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
@Module
class NetworkServiceModule {

    @Provides
    @Singleton
    internal fun provideNetworkService(): NetworkService {
        return RetrofitGenerator.createService()
    }

}
