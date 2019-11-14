package ru.dmitrybochkov.rates.view_models;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import ru.dmitrybochkov.rates.RatesApp;
import ru.dmitrybochkov.rates.domain.Rate;
import ru.dmitrybochkov.rates.usecases.rates.RatesUseCase;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
public class RatesViewModel extends BaseViewModel {

    @Inject
    RatesUseCase ratesUseCase;

    public RatesViewModel() {
        super();
        RatesApp.getAppComponent().inject(this);
    }

    public LiveData<List<Rate>> getRates() {
        return ratesUseCase.getRates();
    }

    public void updateRates() {
        ratesUseCase.updateRates();
    }
}
