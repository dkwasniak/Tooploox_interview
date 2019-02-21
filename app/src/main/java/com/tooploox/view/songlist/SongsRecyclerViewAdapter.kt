package com.tooploox.view.songlist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tooploox.R
import com.tooploox.applicationComponent
import com.tooploox.domain.viewmodel.SongViewModel
import com.tooploox.domainmodule.Source
import com.tooploox.utils.TooplooxImageLoader
import com.tooploox.utils.layoutInflater
import kotlinx.android.synthetic.main.song_list_item.view.*
import javax.inject.Inject

class SongsRecyclerViewAdapter : RecyclerView.Adapter<SongsRecyclerViewAdapter.SongViewHolder>() {

    @Inject
    lateinit var imageLoader: TooplooxImageLoader

    var items: List<SongViewModel> = emptyList()

    init {
        applicationComponent.inject(this)
    }

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


    inner class SongViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(songsViewModel: SongViewModel) {
            with(songsViewModel) {
                view.songTitleTextView.text = title
                view.artistNameTextView.text = artistName
                view.releaseDateTextView.text = releaseData
                imageLoader.loadInto(coverImageUrl, view.songPhotoImageView)
                when (source) {
                    Source.LOCAL -> view.songSourceImageView.setImageResource(R.drawable.ic_local)
                    Source.ITUNES -> view.songSourceImageView.setImageResource(R.drawable.ic_itunes)
                }
            }

        }
    }

}