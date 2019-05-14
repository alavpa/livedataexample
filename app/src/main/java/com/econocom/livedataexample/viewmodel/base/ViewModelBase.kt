package com.econocom.livedataexample.viewmodel.base

import androidx.lifecycle.*
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

    var owner: LifecycleOwner? = null

    fun attach(owner: LifecycleOwner) {
        this.owner = owner
    }

    fun detach() {
        this.owner = null
    }

    fun <T> MutableLiveData<T>.observe(result: (T) -> Unit) {
        owner?.let { lifeCycleOwner ->
            this.observe(lifeCycleOwner, Observer { result(it) })
        }
    }

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

    fun <T> removeObservers(vararg liveDatas: LiveData<T>) {
        owner?.let { lifeCycleOwner ->
            liveDatas.forEach {
                it.removeObservers(lifeCycleOwner)
            }
        }
    }
}