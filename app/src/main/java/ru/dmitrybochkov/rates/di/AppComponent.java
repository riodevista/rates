package ru.dmitrybochkov.rates.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.dmitrybochkov.rates.view_models.RatesViewModel;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
@Component(modules = {AppModule.class, DataModule.class, NetworkServiceModule.class, UseCasesModule.class})
@Singleton
public interface AppComponent {


    void inject(RatesViewModel ratesViewModel);
}

