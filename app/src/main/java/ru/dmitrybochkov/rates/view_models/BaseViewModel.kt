package ru.dmitrybochkov.rates.view_models

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
open class BaseViewModel : ViewModel() {

    protected var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}