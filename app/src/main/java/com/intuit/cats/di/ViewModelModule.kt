package com.intuit.cats.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intuit.cats.vm.CatViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatViewModel::class)
    abstract fun bindCatViewModel(viewModel: CatViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CatsViewModelFactory): ViewModelProvider.Factory
}