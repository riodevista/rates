package ru.dmitrybochkov.rates.view_models

import androidx.lifecycle.LiveData
import ru.dmitrybochkov.rates.RatesApp
import ru.dmitrybochkov.rates.domain.Rate
import ru.dmitrybochkov.rates.usecases.rates.RatesUseCase
import javax.inject.Inject

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
class RatesViewModel : BaseViewModel() {

    @Inject
    internal lateinit var ratesUseCase: RatesUseCase

    val rates: LiveData<List<Rate>>
        get() = ratesUseCase.rates

    init {
        RatesApp.appComponent.inject(this)
        RatesApp.appComponent.inject(this)
    }

    fun updateRates() {
        ratesUseCase.updateRates()
    }
}
