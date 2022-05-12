package com.example.courseapp.di

import com.example.courseapp.activities.MainActivity
import com.example.courseapp.fragments.CurrenciesListFragment
import com.example.courseapp.mvp.presenter.CurrenciesPresenter
import com.example.courseapp.mvp.presenter.LatestChartPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ChartModule::class, MvpModule::class, RestModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)

}