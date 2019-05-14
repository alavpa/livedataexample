package com.econocom.livedataexample

import android.app.Application
import com.econocom.livedataexample.data.di.dataModule
import com.econocom.livedataexample.domain.di.domainModule
import com.econocom.livedataexample.viewmodel.di.viewmodelModule
import org.koin.core.context.startKoin
import timber.log.Timber

class LVApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    viewmodelModule
                )
            )
        }
    }
}