package ru.dmitrybochkov.rates.data.api.v1


import com.google.gson.JsonObject
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
interface NetworkService {

    @GET("latest")
    fun getRates(
            @Query("base") base: String
    ): Flowable<Response<JsonObject>>
}
