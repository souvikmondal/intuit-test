package com.intuit.cats.datasource.remote

import com.intuit.cats.models.Cat
import com.intuit.cats.network.APIResponse
import com.intuit.cats.network.OkHttpClientBuilder
import com.intuit.cats.network.RetrofitBuilder
import com.intuit.cats.network.buildResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class CatRemoteDataSourceImpl @Inject constructor(
    private val okHttpClientBuilder: OkHttpClientBuilder
) : CatRemoteDataSource {

    companion object CatRemoteDataSource {
        const val BASE_URL = "https://api.thecatapi.com/v1/"
    }

    private val catAPI: CatAPI by lazy {
        RetrofitBuilder().baseUrl(BASE_URL).okHttpClient(okHttpClientBuilder.build()).build(CatAPI::class.java)
    }

    override suspend fun getCatBreeds(limit: Int, page: Int): APIResponse<List<Cat>> {
        return catAPI.getCatBreeds(limit, page).buildResponse()
    }

    private interface CatAPI {

        @GET("breeds/")
        suspend fun getCatBreeds(@Query("limit") limit: Int, @Query("page") page: Int): Response<List<Cat>>
    }
}