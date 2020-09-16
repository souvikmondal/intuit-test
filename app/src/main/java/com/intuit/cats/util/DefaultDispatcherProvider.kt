package com.intuit.cats.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultDispatcherProvider @Inject constructor(
) : DispatcherProvider {
    override fun getMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun getIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    override fun getCPUDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}