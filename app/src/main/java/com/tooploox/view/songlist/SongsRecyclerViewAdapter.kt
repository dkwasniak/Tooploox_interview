package com.tooploox.view.songlist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tooploox.R
import com.tooploox.layoutInflater
import com.tooploox.model.SongsViewModel
import kotlinx.android.synthetic.main.song_list_item.view.*

class SongsRecyclerViewAdapter : RecyclerView.Adapter<SongsRecyclerViewAdapter.SongViewHolder>() {

    var items: List<SongsViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = parent.layoutInflater().inflate(R.layout.song_list_item, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class SongViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(songsViewModel: SongsViewModel) {
            view.songTitleTextView.text = songsViewModel.title
            view.artistNameTextView.text = songsViewModel.artist
        }
    }

}