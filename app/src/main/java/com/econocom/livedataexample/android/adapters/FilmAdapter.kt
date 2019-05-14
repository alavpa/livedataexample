package com.econocom.livedataexample.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.econocom.livedataexample.R
import com.econocom.livedataexample.viewmodel.MainViewModel

class FilmAdapter(
    var items: List<MainViewModel.FilmItemView> = listOf(),
    private val onClick: (MainViewModel.FilmItemView) -> Unit
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title = itemView.findViewById<TextView>(R.id.title)
        fun bind(item: MainViewModel.FilmItemView, onClick: (MainViewModel.FilmItemView) -> Unit) {
            title.text = item.title
            itemView.setOnClickListener { onClick(item) }
        }
    }
}