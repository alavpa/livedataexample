package com.econocom.livedataexample.data.api

import com.econocom.livedataexample.data.api.model.FilmDataResponse
import com.econocom.livedataexample.data.api.model.FilmItemResponse
import com.econocom.livedataexample.data.api.model.exceptions.ServerError
import io.reactivex.Single
import retrofit2.Response

class Api(private val serverClient: ServerClient) : ApiDataSource {
    override fun films(id: String): Single<FilmDataResponse> {
        return serverClient.films(id).map { it.process() }
    }

    override fun films(): Single<List<FilmItemResponse>> {
        return serverClient.films().map { it.process() }
    }

    private fun <T> Response<T>.process(): T {
        return if (this.isSuccessful) this.body() ?: throw ServerError()
        else throw ServerError()
    }
}