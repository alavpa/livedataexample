package com.econocom.livedataexample.data

import com.econocom.livedataexample.data.api.ApiDataSource
import com.econocom.livedataexample.data.api.model.toDomain
import com.econocom.livedataexample.domain.Repository
import com.econocom.livedataexample.domain.model.FilmData
import com.econocom.livedataexample.domain.model.FilmItem
import io.reactivex.Single

class DataRepository(
    private val api: ApiDataSource
) : Repository {

    override fun films(id: String): Single<FilmData> {
        return api.films(id).map { it.toDomain() }
    }

    override fun films(): Single<List<FilmItem>> {
        return api.films().map { list -> list.map { it.toDomain() } }
    }
}