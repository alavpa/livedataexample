package com.econocom.livedataexample.domain.di

import com.econocom.livedataexample.domain.interactors.FilmDetails
import com.econocom.livedataexample.domain.interactors.FilmList
import org.koin.dsl.module

val domainModule = module {
    factory { FilmList(get()) }
    factory { FilmDetails(get()) }
}