package com.intuit.cats.di

import com.intuit.cats.datasource.remote.CatRemoteDataSource
import com.intuit.cats.datasource.remote.CatRemoteDataSourceImpl
import com.intuit.cats.network.OkHttpClientBuilder
import com.intuit.cats.network.SimpleOkHttpClientBuilder
import com.intuit.cats.repo.CatRepository
import com.intuit.cats.repo.CatRepositoryImpl
import com.intuit.cats.util.DefaultDispatcherProvider
import com.intuit.cats.util.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [AppModule.Declarations::class])
class AppModule {

    @Module
    interface Declarations {

        @Binds
        fun bindsDispatcherProvider(defaultDispatcherProvider: DefaultDispatcherProvider): DispatcherProvider

        @Binds
        fun bindsCatRepository(catRepositoryImpl: CatRepositoryImpl): CatRepository

        @Binds
        fun bindsCatRemoteDataSource(catRemoteDataSourceImpl: CatRemoteDataSourceImpl): CatRemoteDataSource
    }

    @Provides
    fun providesOkHttpClientBuilder(): OkHttpClientBuilder {
        return SimpleOkHttpClientBuilder
    }
}