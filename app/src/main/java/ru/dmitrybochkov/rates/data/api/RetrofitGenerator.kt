package ru.dmitrybochkov.rates.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.dmitrybochkov.rates.data.api.v1.NetworkService

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */

object RetrofitGenerator {

    //fixer.io
    private val API_BASE_URL = "https://revolut.duckdns.org/"

    fun createService(): NetworkService {
        val okHttpClient = OkHttpClient()

        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(NetworkService::class.java)
    }

}