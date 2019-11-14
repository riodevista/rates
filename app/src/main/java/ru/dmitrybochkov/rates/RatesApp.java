package ru.dmitrybochkov.rates;

import android.app.Application;

import ru.dmitrybochkov.rates.di.AppComponent;
import ru.dmitrybochkov.rates.di.AppModule;
import ru.dmitrybochkov.rates.di.DaggerAppComponent;
import ru.dmitrybochkov.rates.di.DataModule;
import ru.dmitrybochkov.rates.di.NetworkServiceModule;
import ru.dmitrybochkov.rates.di.UseCasesModule;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
public class RatesApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = buildComponent();

    }


    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .dataModule(new DataModule())
                .networkServiceModule(new NetworkServiceModule())
                .useCasesModule(new UseCasesModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
