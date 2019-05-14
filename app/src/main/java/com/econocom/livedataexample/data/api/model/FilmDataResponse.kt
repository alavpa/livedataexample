package com.econocom.livedataexample.data.api.model

import com.econocom.livedataexample.data.api.model.exceptions.MandatoryError
import com.econocom.livedataexample.domain.model.FilmData

data class FilmDataResponse(
    val id: String?,
    val title: String?,
    val description: String?,
    val director: String?,
    val producer: String?,
    val release_date: String?,
    val rt_score: String?
)

fun FilmDataResponse.toDomain(): FilmData {
    return FilmData(
        id,
        title ?: throw MandatoryError(),
        description ?: throw MandatoryError(),
        director,
        producer,
        release_date,
        rt_score
    )
}