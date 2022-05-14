package com.example.courseapp.di

import com.example.courseapp.chart.LatestChart
import com.example.courseapp.formatters.YearValueFormatter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChartModule {

    @Provides
    @Singleton
    fun provideLatestChart():LatestChart = LatestChart()

    @Provides
    @Singleton
    fun provideYearFormatter():YearValueFormatter = YearValueFormatter()

}