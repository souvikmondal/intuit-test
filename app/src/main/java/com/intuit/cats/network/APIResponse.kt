package com.intuit.cats.network

import com.intuit.cats.R

sealed class APIResponse<T> {
    class Loading<T>: APIResponse<T>()
    data class Success<T>(val data: T): APIResponse<T>()
    class Empty<T> : APIResponse<T>()
    data class Error<T>(val code: Int) : APIResponse<T>()
}

fun <T> getErrorResId(error: APIResponse.Error<T>): Int {
    // return same error for brevity
    return R.string.default_error_text
}