package com.intuit.cats.di

import com.intuit.cats.views.CatDetailFragment
import com.intuit.cats.views.CatListFragment
import com.intuit.cats.views.MainActivity
import dagger.Component

@Component(modules = [ViewModelModule::class, AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(catListFragment: CatListFragment)

    fun inject(catDetailFragment: CatDetailFragment)
}