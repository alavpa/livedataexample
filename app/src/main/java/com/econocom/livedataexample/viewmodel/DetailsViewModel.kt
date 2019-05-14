package com.econocom.livedataexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.econocom.livedataexample.domain.interactors.FilmDetails
import com.econocom.livedataexample.viewmodel.base.ViewModelBase

class DetailsViewModel(private val filmDetails: FilmDetails) : ViewModelBase() {

    var filmLiveData = MutableLiveData<FilmDataView>()

    fun load(id: String) {
        filmDetails.build(id).execute { film ->
            filmLiveData.postValue(FilmDataView(film.title, film.description))
        }
    }

    inner class FilmDataView(val title: String, val description: String)
}