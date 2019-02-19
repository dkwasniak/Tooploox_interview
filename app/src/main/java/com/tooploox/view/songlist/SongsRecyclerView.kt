package com.tooploox.view.songlist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tooploox.model.SongsViewModel

class SongsRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val songsAdapter: SongsRecyclerViewAdapter = SongsRecyclerViewAdapter()

    init {
        layoutManager = LinearLayoutManager(context)
        adapter = songsAdapter
    }

    fun setItems(songs: List<SongsViewModel>) {
        songsAdapter.items = songs
        songsAdapter.notifyDataSetChanged()
    }
}