package ru.dmitrybochkov.rates.data.repository

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import io.reactivex.Flowable
import retrofit2.Response
import ru.dmitrybochkov.rates.domain.Rate

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
interface Repository {

    val rates: LiveData<List<Rate>>

    fun updateRates(base: String): Flowable<Response<JsonObject>>
}
