package com.intuit.cats.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.intuit.cats.models.Cat
import com.intuit.cats.network.APIResponse
import com.intuit.cats.repo.CatRepository
import javax.inject.Inject

class CatViewModel @Inject constructor(
    private val catsRepository: CatRepository
) : ViewModel() {

    companion object {
        const val LIMIT = 100
        const val PAGE = 0
    }

    private val _catsBreedPagedLiveData: MutableLiveData<Pair<Int, Int>> = MutableLiveData()
    val catsLiveData: LiveData<APIResponse<List<Cat>>> = _catsBreedPagedLiveData.switchMap {
        catsRepository.getCatBreeds(it.first, it.second)
    }

    fun fetchCatBreeds(limit: Int = LIMIT, page: Int = PAGE) {
        _catsBreedPagedLiveData.value = LIMIT to PAGE
    }

    fun getCat(id: String): Cat? {
        val response = catsLiveData.value
        return if (response != null && response is APIResponse.Success) {
            response.data.find { it.id == id }
        } else {
            null
        }
    }
}