package com.example.courseapp.di

import com.example.courseapp.mvp.presenter.CurrenciesPresenter
import com.example.courseapp.mvp.presenter.LatestChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun provideCurrenciesPresenter():CurrenciesPresenter = CurrenciesPresenter()

    @Provides
    @Singleton
    fun provideLatestChartPresenter():LatestChartPresenter = LatestChartPresenter()

}