package com.intuit.cats.network

import retrofit2.Response

fun <T> Response<T>.buildResponse(): APIResponse<T> {
    return if (this.isSuccessful) {
        this.body()?.let { APIResponse.Success(it) } ?: APIResponse.Empty()
    } else {
        APIResponse.Error(this.code())
    }
}