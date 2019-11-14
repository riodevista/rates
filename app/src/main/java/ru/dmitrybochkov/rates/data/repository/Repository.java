package ru.dmitrybochkov.rates.data.repository;

import com.google.gson.JsonObject;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Response;
import ru.dmitrybochkov.rates.domain.Rate;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
public interface Repository {

    Flowable<Response<JsonObject>> updateRates(String base);

    LiveData<List<Rate>> getRates();
}
