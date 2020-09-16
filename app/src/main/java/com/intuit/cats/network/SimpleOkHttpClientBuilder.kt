package com.intuit.cats.network

import okhttp3.OkHttpClient

object SimpleOkHttpClientBuilder : OkHttpClientBuilder {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    override fun build() = okHttpClient
}