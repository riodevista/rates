package ru.dmitrybochkov.rates.di;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import ru.dmitrybochkov.rates.data.repository.Repository;
import ru.dmitrybochkov.rates.usecases.rates.RatesUseCase;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */

@Module
public class UseCasesModule {

    @Provides
    @Singleton
    RatesUseCase provideRatesUseCase(@NonNull final Repository repository) {
        return new RatesUseCase(repository);
    }
}
