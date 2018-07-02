package com.test.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PicsService {
    private var retrofit: Retrofit? = null


    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    val api: PicsAPI
        get() {
            val BASE_URL = "https://api.flickr.com/services/"

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }

            return retrofit!!.create(PicsAPI::class.java)
        }
}
