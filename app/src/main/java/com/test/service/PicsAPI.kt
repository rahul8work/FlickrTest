package com.test.service

import com.google.gson.JsonObject
import com.test.model.Data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsAPI {

    @GET("rest/")
    fun results(@Query("method") method: String,
                @Query("api_key") api_key: String,
                @Query("format") format: String,
                @Query("nojsoncallback") nojsoncallback: String,
                @Query("safe_search") safe_search: Int): Call<JsonObject>

}
