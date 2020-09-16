package com.intuit.cats.datasource.remote

import com.intuit.cats.models.Cat
import com.intuit.cats.network.APIResponse

interface CatRemoteDataSource {

    suspend fun getCatBreeds(limit: Int, page: Int): APIResponse<List<Cat>>
}