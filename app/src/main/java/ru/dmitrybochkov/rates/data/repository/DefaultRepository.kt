package ru.dmitrybochkov.rates.data.repository

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import ru.dmitrybochkov.rates.data.api.v1.NetworkService
import ru.dmitrybochkov.rates.data.db.RateDao
import ru.dmitrybochkov.rates.domain.Rate
import java.util.*

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */

class DefaultRepository(private val rateDao: RateDao, private val networkService: NetworkService) : Repository {

    override val rates: LiveData<List<Rate>>
        get() = rateDao.loadAll()

    override fun updateRates(base: String): Flowable<Response<JsonObject>> {
        return networkService.getRates(base)
                .subscribeOn(Schedulers.io())
                .doOnNext { response ->
                    if (response.body() != null) {
                        saveRates(response.body()!!.getAsJsonObject("rates"))
                    }
                }

    }

    private fun saveRates(rates: JsonObject) {
        val entries = rates.entrySet()
        val currencies = ArrayList<Rate>(entries.size)
        for ((key, value) in entries) {
            currencies.add(Rate(key, value.asFloat))
        }
        rateDao.insertOrReplace(currencies)
    }
}
