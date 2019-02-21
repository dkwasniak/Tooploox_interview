package com.tooploox.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tooploox.R
import com.tooploox.applicationComponent
import com.tooploox.domain.presenter.MainViewPresenter
import com.tooploox.domain.view.MainView
import com.tooploox.utils.onClickWithDebounce
import com.tooploox.utils.showSnackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applicationComponent.inject(this)
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        localSourceButton.onSelectedCallback = {
            presenter.onLocalSourceSelect(it)
        }
        iTunesSourceButton.onSelectedCallback = {
            presenter.oniTunesSourceSelect(it)
        }
        nextButton.onClickWithDebounce {
            presenter.onNextButtonClicked()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.attach(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detach()
    }

    override fun showMissingSelectionInfo() {
        contentView.showSnackbar(R.string.missing_selection)
    }

    override fun goToSongsList(isiTunesSelected: Boolean, isLocalSelected: Boolean) {
        startActivity(SongsActivity.create(this, isiTunesSelected, isLocalSelected))
    }

}
