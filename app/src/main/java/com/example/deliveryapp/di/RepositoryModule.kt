package com.example.deliveryapp.di

import com.example.deliveryapp.data.remote.APIHelperImplementation
import com.example.deliveryapp.data.remote.APIService
import com.example.deliveryapp.data.repository.Repository
import com.example.deliveryapp.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun provideAppRepository(api: APIService): Repository {
        val dataSourceImpl = APIHelperImplementation(api)
        return RepositoryImpl(dataSourceImpl)
    }
}
