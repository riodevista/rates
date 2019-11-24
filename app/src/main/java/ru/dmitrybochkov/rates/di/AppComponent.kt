package ru.dmitrybochkov.rates.di

import dagger.Component
import ru.dmitrybochkov.rates.view_models.RatesViewModel
import javax.inject.Singleton

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
@Component(modules = [AppModule::class, DataModule::class, NetworkServiceModule::class, UseCasesModule::class])
@Singleton
interface AppComponent {


    fun inject(ratesViewModel: RatesViewModel)
}

