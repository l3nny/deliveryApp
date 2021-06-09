package com.example.deliveryapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


//Dagger
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun app(application: Application): MainApplication = application as MainApplication
}