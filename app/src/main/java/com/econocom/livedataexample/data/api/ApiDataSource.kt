package com.econocom.livedataexample.data.api

import com.econocom.livedataexample.data.api.model.FilmDataResponse
import com.econocom.livedataexample.data.api.model.FilmItemResponse
import io.reactivex.Single

interface ApiDataSource {
    fun films(): Single<List<FilmItemResponse>>
    fun films(id: String): Single<FilmDataResponse>
}