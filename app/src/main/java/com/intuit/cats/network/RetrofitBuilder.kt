package com.intuit.cats.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private lateinit var baseUrl: String
    private lateinit var okHttpClient: OkHttpClient
    private var gson: Gson? = null

    fun baseUrl(baseUrl: String): RetrofitBuilder {
        this.baseUrl = baseUrl
        return this
    }

    fun okHttpClient(okHttpClient: OkHttpClient): RetrofitBuilder {
        this.okHttpClient = okHttpClient
        return this
    }

    fun gson(gson: Gson) {
        this.gson = gson
    }

    fun <T> build(service: Class<T>): T {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create(gson ?: Gson()))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(service)
    }
}