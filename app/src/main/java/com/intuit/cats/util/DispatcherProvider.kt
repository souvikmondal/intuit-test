package com.intuit.cats.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    fun getMainDispatcher(): CoroutineDispatcher

    fun getIODispatcher(): CoroutineDispatcher

    fun getCPUDispatcher(): CoroutineDispatcher
}