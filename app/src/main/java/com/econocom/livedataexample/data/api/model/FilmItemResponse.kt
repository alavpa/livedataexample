package com.econocom.livedataexample.data.api.model

import com.econocom.livedataexample.data.api.model.exceptions.MandatoryError
import com.econocom.livedataexample.domain.model.FilmItem

data class FilmItemResponse(
    val id: String?,
    val title: String?,
    val description: String?,
    val director: String?,
    val producer: String?,
    val release_date: String?,
    val rt_score: String?
)

fun FilmItemResponse.toDomain(): FilmItem {
    return FilmItem(
        id ?: throw MandatoryError(),
        title ?: throw MandatoryError(),
        description,
        director,
        producer,
        release_date,
        rt_score
    )
}