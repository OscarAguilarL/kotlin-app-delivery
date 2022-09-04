package com.oscar_aguilar.appdelivery.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitClient {
    fun getClient(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
