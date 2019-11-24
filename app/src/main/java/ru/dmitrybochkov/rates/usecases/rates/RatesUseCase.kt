package ru.dmitrybochkov.rates.usecases.rates

import androidx.lifecycle.LiveData
import ru.dmitrybochkov.rates.data.repository.Repository
import ru.dmitrybochkov.rates.domain.Rate
import ru.dmitrybochkov.rates.usecases.BaseUseCase
import javax.inject.Inject

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
class RatesUseCase @Inject constructor(private val repository: Repository) : BaseUseCase() {

    val rates: LiveData<List<Rate>>
        get() {
            updateRates()
            return repository.rates
        }

    fun updateRates() {
        // TODO: 14.11.2019 Dmitry Bochkov: handle STATUS and ERROR
        repository.updateRates("RUB").subscribe({ response -> }, { error -> })
    }
}
