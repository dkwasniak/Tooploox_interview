package com.tooploox.data.db

import android.content.Context
import com.google.gson.Gson
import com.tooploox.data.R
import com.tooploox.data.db.model.SongsDbModel
import com.tooploox.data.service.LocalSongsService
import io.reactivex.Single
import java.io.IOException
import java.nio.charset.Charset

class LocalSongsServiceImpl(
        private val context: Context,
        private val gson: Gson
) : LocalSongsService {

    override fun fetchSongs(): Single<SongsDbModel> {
        return try {
            val json: String
            val inputStream = context.resources.openRawResource(R.raw.songs)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
            Single.just(gson.fromJson(json, SongsDbModel::class.java))
        } catch (e: IOException) {
            Single.error(e)
        }
    }
}