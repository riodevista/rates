package ru.dmitrybochkov.rates.di

import dagger.Module
import dagger.Provides
import ru.dmitrybochkov.rates.data.repository.Repository
import ru.dmitrybochkov.rates.usecases.rates.RatesUseCase
import javax.inject.Singleton

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */

@Module
class UseCasesModule {

    @Provides
    @Singleton
    internal fun provideRatesUseCase(repository: Repository): RatesUseCase {
        return RatesUseCase(repository)
    }
}
