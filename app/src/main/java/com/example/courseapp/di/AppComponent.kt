package com.example.courseapp.di

import com.example.courseapp.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ChartModule::class, MvpModule::class, RestModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}