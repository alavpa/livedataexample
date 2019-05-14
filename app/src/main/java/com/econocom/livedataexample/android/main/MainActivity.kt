package com.econocom.livedataexample.android.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.econocom.livedataexample.R
import com.econocom.livedataexample.android.adapters.FilmAdapter
import com.econocom.livedataexample.android.details.DetailsActivity
import com.econocom.livedataexample.domain.model.FilmData
import com.econocom.livedataexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel()
    private val adapter = FilmAdapter {
        DetailsActivity.open(this, it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        film_list?.layoutManager = LinearLayoutManager(this)
        film_list?.adapter = adapter

        vm.attach(this)
        vm.observeList {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        vm.load()
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.detach()
    }
}
