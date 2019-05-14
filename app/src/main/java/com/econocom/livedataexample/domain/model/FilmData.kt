package com.econocom.livedataexample.domain.model

data class FilmData(
    val id: String?,
    val title: String,
    val description: String,
    val director: String?,
    val producer: String?,
    val releaseDate: String?,
    val score: String?
)