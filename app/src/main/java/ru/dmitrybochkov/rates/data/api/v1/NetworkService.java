package ru.dmitrybochkov.rates.data.api.v1;


import com.google.gson.JsonObject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
public interface NetworkService {

    @GET("latest")
    Flowable<Response<JsonObject>> getRates(
            @Query("base") String base
    );
}
