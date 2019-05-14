package com.econocom.livedataexample.data.api

import com.econocom.livedataexample.data.api.model.FilmDataResponse
import com.econocom.livedataexample.data.api.model.FilmItemResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerClient {

    companion object {
        const val BASE_URL = "https://ghibliapi.herokuapp.com/"
    }

    @GET("films")
    fun films(): Single<Response<List<FilmItemResponse>>>

    @GET("films/{id}")
    fun films(@Path("id") id: String): Single<Response<FilmDataResponse>>
}