package com.intuit.cats.repo

import androidx.lifecycle.LiveData
import com.intuit.cats.models.Cat
import com.intuit.cats.network.APIResponse

interface CatRepository {

    fun getCatBreeds(limit: Int, page: Int): LiveData<APIResponse<List<Cat>>>
}