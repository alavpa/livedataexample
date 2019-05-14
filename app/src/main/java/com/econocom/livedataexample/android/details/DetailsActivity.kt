package com.econocom.livedataexample.android.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.econocom.livedataexample.R
import com.econocom.livedataexample.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.film_description
import kotlinx.android.synthetic.main.activity_details.film_title
import kotlinx.android.synthetic.main.activity_details.toolbar
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_ID = "EXTRA_ID"
        fun open(activity: Activity, id: String) {
            Intent(activity, DetailsActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
            }.also {
                activity.startActivity(it)
            }
        }
    }

    private val vm: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        vm.filmLiveData.observe(this, Observer {
            film_title?.text = it.title
            film_description?.text = it.description
        })

        vm.load(intent.getStringExtra(EXTRA_ID))
    }
}