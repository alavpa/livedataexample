package com.econocom.livedataexample.viewmodel.di

import com.econocom.livedataexample.viewmodel.DetailsViewModel
import com.econocom.livedataexample.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    factory(named("ioThread")) { Schedulers.io() }
    factory(named("mainThread")) { AndroidSchedulers.mainThread() }
    factory { CompositeDisposable() }
}