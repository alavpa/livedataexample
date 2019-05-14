package com.econocom.livedataexample.data.di

import com.econocom.livedataexample.BuildConfig
import com.econocom.livedataexample.data.DataRepository
import com.econocom.livedataexample.data.api.Api
import com.econocom.livedataexample.data.api.ApiDataSource
import com.econocom.livedataexample.data.api.InternetInterceptor
import com.econocom.livedataexample.data.api.ServerClient
import com.econocom.livedataexample.domain.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { httpLoggingInterceptor() }
    single { InternetInterceptor() }
    single { getOkHttpClient() }
    single { serverClient(get()) }
    single<ApiDataSource> { Api(get()) }
    single<Repository> { DataRepository(get()) }
}

fun serverClient(client: OkHttpClient): ServerClient {
    return Retrofit.Builder()
        .baseUrl(ServerClient.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
        .create(ServerClient::class.java)
}

fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor())
        .addInterceptor(InternetInterceptor())
        .build()
}

fun httpLoggingInterceptor(): HttpLoggingInterceptor {
    return if (BuildConfig.DEBUG) {
        okhttp3.logging.HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        okhttp3.logging.HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }
}