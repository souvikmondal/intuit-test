package com.intuit.cats.network

import okhttp3.OkHttpClient

interface OkHttpClientBuilder {
    fun build(): OkHttpClient
}