package com.test.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.test.model.Data
import com.test.model.RawResponse
import com.test.service.PicsService
import com.test.view.PicsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PicsPresenter(private val picsView: PicsView) {
    private var picsService: PicsService? = null

    init {

        if (this.picsService == null) {
            this.picsService = PicsService()
        }
    }

    fun getPics() {
        picsService!!
                .api
                .results("flickr.photos.getRecent", "e9b904988093e98713921260a3ea1b0c", "json", "?", 1).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                        Log.i("onFailure", "isHere")
                    }

                    override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                        Log.i("onResponse", "isHere"+response?.body().toString())
                        val received = Gson().fromJson(response?.body(), RawResponse::class.java)
                        picsView.picsReady(received.photos?.photo)
                    }
                })
    }
}
