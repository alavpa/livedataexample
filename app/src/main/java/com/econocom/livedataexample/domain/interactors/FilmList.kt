package com.econocom.livedataexample.domain.interactors

import com.econocom.livedataexample.domain.Repository
import com.econocom.livedataexample.domain.model.FilmItem
import io.reactivex.Single

class FilmList(private val repository: Repository) {

    fun build(): Single<List<FilmItem>> {
        return repository.films()
    }
}