package com.econocom.livedataexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.econocom.livedataexample.domain.interactors.FilmList
import com.econocom.livedataexample.viewmodel.base.ViewModelBase

class MainViewModel(private val filmList: FilmList) : ViewModelBase() {

    private var listLiveData = MutableLiveData<List<FilmItemView>>()

    fun load() {
        filmList.build().execute { list ->
            listLiveData.postValue(list.map { FilmItemView(it.id, it.title) })
        }
    }

    fun observeList(onLoad: (List<FilmItemView>) -> Unit) {
        listLiveData.observe(onLoad)
    }

    override fun onCleared() {
        super.onCleared()
        removeObservers(listLiveData)
    }

    inner class FilmItemView(val id: String, val title: String)
}