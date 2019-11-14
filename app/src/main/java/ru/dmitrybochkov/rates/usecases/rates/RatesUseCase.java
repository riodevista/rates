package ru.dmitrybochkov.rates.usecases.rates;

import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import io.reactivex.functions.Consumer;
import retrofit2.Response;
import ru.dmitrybochkov.rates.data.repository.Repository;
import ru.dmitrybochkov.rates.domain.Rate;
import ru.dmitrybochkov.rates.usecases.BaseUseCase;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
public class RatesUseCase extends BaseUseCase {

    private final Repository repository;

    @Inject
    public RatesUseCase(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Rate>> getRates() {
        updateRates();
        return repository.getRates();
    }

    public void updateRates() {
        // TODO: 14.11.2019 Dmitry Bochkov: handle STATUS and ERROR
        repository.updateRates("RUB").subscribe(response -> {}, error -> {});
    }
}
