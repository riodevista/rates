package ru.dmitrybochkov.rates.di;

import android.content.Context;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitry Bochkov on 14.11.2019
 */

@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return appContext;
    }
}
