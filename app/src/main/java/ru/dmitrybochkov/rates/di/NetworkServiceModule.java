package ru.dmitrybochkov.rates.di;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import ru.dmitrybochkov.rates.data.api.v1.NetworkService;
import ru.dmitrybochkov.rates.data.api.RetrofitGenerator;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
@Module
public class NetworkServiceModule {

    @Provides
    @NonNull
    @Singleton
    NetworkService provideNetworkService() {
        return RetrofitGenerator.createService();
    }

}
