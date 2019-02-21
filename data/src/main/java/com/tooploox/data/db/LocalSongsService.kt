package com.tooploox.data.db

import android.content.Context
import com.google.gson.Gson
import com.tooploox.data.db.model.SongsDbModel
import io.reactivex.Observable
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

class LocalSongsService @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    fun fetchSongs(): Observable<SongsDbModel> {
        return try {
            val json: String
            val inputStream = context.assets.open("songs.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
            Observable.just(gson.fromJson(json, SongsDbModel::class.java))
        } catch (e: IOException) {
            Observable.error(e)
        }
    }
}