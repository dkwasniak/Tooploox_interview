package com.tooploox.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tooploox.R
import com.tooploox.applicationComponent
import com.tooploox.domain.presenter.SongsViewPresenter
import com.tooploox.domain.view.SongsView
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
    }

    override fun onDetachedFromWindow() {
        presenter.detach()
        super.onDetachedFromWindow()
    }
}
