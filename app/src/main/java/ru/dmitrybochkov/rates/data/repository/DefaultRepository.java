package ru.dmitrybochkov.rates.data.repository;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.lifecycle.LiveData;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import ru.dmitrybochkov.rates.data.api.v1.NetworkService;
import ru.dmitrybochkov.rates.data.db.RateDao;
import ru.dmitrybochkov.rates.domain.Rate;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */

public class DefaultRepository implements Repository {

    private final RateDao rateDao;
    private final NetworkService networkService;

    public DefaultRepository(RateDao rateDao, NetworkService networkService) {
        this.rateDao = rateDao;
        this.networkService = networkService;
    }

    @Override
    public Flowable<Response<JsonObject>> updateRates(String base) {
        return networkService.getRates(base)
                .subscribeOn(Schedulers.io())
                .doOnNext(response -> {
                    if (response.body() != null) {
                       saveRates(response.body().getAsJsonObject("rates"));
                    }
                });

    }

    @Override
    public LiveData<List<Rate>> getRates() {
        return rateDao.loadAll();
    }

    private void saveRates(JsonObject rates) {
        Set<Map.Entry<String, JsonElement>> entries = rates.entrySet();
        ArrayList<Rate> currencies = new ArrayList<>(entries.size());
        for (Map.Entry<String, JsonElement> entry: entries) {
            currencies.add(new Rate(entry.getKey(), entry.getValue().getAsFloat()));
        }
        rateDao.insertOrReplace(currencies);
    }
}
