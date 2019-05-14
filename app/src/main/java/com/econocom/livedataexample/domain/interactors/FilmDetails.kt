package com.econocom.livedataexample.domain.interactors

import com.econocom.livedataexample.domain.Repository
import com.econocom.livedataexample.domain.model.FilmData
import io.reactivex.Single

class FilmDetails(private val repository: Repository) {

    fun build(id: String): Single<FilmData> {
        return repository.films(id)
    }
}