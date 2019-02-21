package com.tooploox.view.songlist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tooploox.domain.viewmodel.SongViewModel

class SongsRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val songsAdapter: SongsRecyclerViewAdapter = SongsRecyclerViewAdapter()

    init {
        layoutManager = LinearLayoutManager(context)
        adapter = songsAdapter
        addItemDecoration(SongRecyclerViewItemDecorator(context))
    }

    fun setItems(songs: List<SongViewModel>) {
        songsAdapter.items = songs
        songsAdapter.notifyDataSetChanged()
    }
}