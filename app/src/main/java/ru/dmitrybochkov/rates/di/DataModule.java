package ru.dmitrybochkov.rates.di;

import android.content.Context;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import ru.dmitrybochkov.rates.data.api.v1.NetworkService;
import ru.dmitrybochkov.rates.data.db.AppDatabase;
import ru.dmitrybochkov.rates.data.db.RateDao;
import ru.dmitrybochkov.rates.data.repository.DefaultRepository;
import ru.dmitrybochkov.rates.data.repository.Repository;

/**
 * Created by Dmitry Bochkov on 14.11.2019
 */

@Module
public class DataModule {

    @Provides
    @NonNull
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room
                .inMemoryDatabaseBuilder(context, AppDatabase.class)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    RateDao provideRateDao(AppDatabase appDatabase) {
        return appDatabase.rateDao();
    }

    @Provides
    @NonNull
    @Singleton
    Repository provideRepository(NetworkService networkService, RateDao rateDao) {
        return new DefaultRepository(rateDao, networkService);
    }
}
