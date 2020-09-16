package com.intuit.cats.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.intuit.cats.datasource.remote.CatRemoteDataSource
import com.intuit.cats.models.Cat
import com.intuit.cats.network.APIResponse
import com.intuit.cats.util.DispatcherProvider
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catRemoteDataSource: CatRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : CatRepository {

    override fun getCatBreeds(limit: Int, page: Int): LiveData<APIResponse<List<Cat>>> {
        return liveData (dispatcherProvider.getIODispatcher()) {
            emit(APIResponse.Loading())

            val response = catRemoteDataSource.getCatBreeds(limit, page)
            emit(response)
        }
    }
}