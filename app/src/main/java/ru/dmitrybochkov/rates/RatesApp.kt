package ru.dmitrybochkov.rates

import android.app.Application
import ru.dmitrybochkov.rates.di.*

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
class RatesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .dataModule(DataModule())
                .networkServiceModule(NetworkServiceModule())
                .useCasesModule(UseCasesModule())
                .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}
