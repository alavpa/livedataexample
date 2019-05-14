package com.econocom.livedataexample.viewmodel.base

import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import timber.log.Timber

open class ViewModelBase : ViewModel(), KoinComponent {

    private val ioThread: Scheduler by inject(named("ioThread"))
    private val mainThread: Scheduler by inject(named("mainThread"))
    private val compositeDisposable: CompositeDisposable by inject()

    fun <T> Single<T>.execute(
        onError: (Throwable) -> Unit = { Timber.e(it) },
        onSuccess: (T) -> Unit
    ) {
        val disposable = this.subscribeOn(ioThread)
            .observeOn(mainThread)
            .subscribe(onSuccess, onError)

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}