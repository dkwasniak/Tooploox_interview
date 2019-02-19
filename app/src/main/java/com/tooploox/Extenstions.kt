package com.tooploox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

fun ViewGroup.inflate(@LayoutRes id: Int): View {
    return View.inflate(this.context, id, this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.layoutInflater(): LayoutInflater {
    return LayoutInflater.from(this.context)
}

fun View.onClickWithDebounce(consumer: (Any) -> Unit) {
    RxView.clicks(this)
            .throttleFirst(300, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe(consumer)
}

fun View.showSnackbar(@StringRes id: Int) {
    Snackbar.make(this, context.getString(id), Snackbar.LENGTH_LONG).show()
}