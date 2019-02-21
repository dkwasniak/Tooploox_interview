package com.tooploox.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tooploox.R
import com.tooploox.applicationComponent
import com.tooploox.domain.presenter.SongsViewPresenter
import com.tooploox.domain.view.SongsView
import com.tooploox.domain.viewmodel.SongViewModel
import com.tooploox.utils.gone
import com.tooploox.utils.showSnackbar
import com.tooploox.utils.visible
import kotlinx.android.synthetic.main.activity_songs.*
import javax.inject.Inject

class SongsActivity : AppCompatActivity(), SongsView {


    companion object {

        const val ITUNES_KEY = "itunes_key"

        const val LOCAL_KEY = "local_key"

        fun create(context: Context, isiTunesSelected: Boolean, isLocalSelected: Boolean): Intent {
            return Intent(context, SongsActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putBoolean(ITUNES_KEY, isiTunesSelected)
                bundle.putBoolean(LOCAL_KEY, isLocalSelected)
                putExtras(bundle)
            }
        }
    }

    @Inject
    lateinit var presenter: SongsViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs)
        applicationComponent.inject(this)
        initToolbar()
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        supportActionBar?.title = getString(R.string.songs_activity_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.attach(this)
        presenter.fetchSongs(intent?.extras?.getBoolean(ITUNES_KEY) ?: false,
                intent?.extras?.getBoolean(LOCAL_KEY) ?: false)
    }

    override fun onDetachedFromWindow() {
        presenter.detach()
        super.onDetachedFromWindow()
    }

    override fun showError() {
        super.showError()
        contentView.showSnackbar(R.string.something_went_wrong)
    }

    override fun showSongs(songs: List<SongViewModel>) {
        songsRecyclerView.visible()
        songsRecyclerView.setItems(songs)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visible()
        } else {
            progressBar.gone()
        }
    }
}
