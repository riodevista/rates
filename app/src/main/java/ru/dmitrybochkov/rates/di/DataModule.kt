package ru.dmitrybochkov.rates.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.dmitrybochkov.rates.data.api.v1.NetworkService
import ru.dmitrybochkov.rates.data.db.AppDatabase
import ru.dmitrybochkov.rates.data.db.RateDao
import ru.dmitrybochkov.rates.data.repository.DefaultRepository
import ru.dmitrybochkov.rates.data.repository.Repository
import javax.inject.Singleton

/**
 * Created by Dmitry Bochkov on 14.11.2019
 */

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase {
        return Room
                .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRateDao(appDatabase: AppDatabase): RateDao {
        return appDatabase.rateDao()
    }

    @Provides
    @Singleton
    internal fun provideRepository(networkService: NetworkService, rateDao: RateDao): Repository {
        return DefaultRepository(rateDao, networkService)
    }
}
