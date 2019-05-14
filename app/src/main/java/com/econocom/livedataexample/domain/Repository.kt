package com.econocom.livedataexample.domain

import com.econocom.livedataexample.domain.model.FilmData
import com.econocom.livedataexample.domain.model.FilmItem
import io.reactivex.Single

interface Repository {
    fun films(): Single<List<FilmItem>>
    fun films(id: String): Single<FilmData>
}