package com.example.deliveryapp.data.repository

import com.example.deliveryapp.data.commun.ViewStateResult
import com.example.deliveryapp.data.model.ResponseRestaurantDetail
import com.example.deliveryapp.data.model.Restaurant
import com.example.deliveryapp.data.remote.APIHelperImplementationTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class RepositoryImplTest(private val aPIHelperImplementationTest: APIHelperImplementationTest) :
    Repository {
    override suspend fun getRestaurants(): Flow<ViewStateResult<Restaurant>> {
        return flow {
            aPIHelperImplementationTest.getRestaurants().run {
                when (this) {
                    is ViewStateResult.Success -> {
                        data?.let { emit(ViewStateResult.Success(it)) }
                    }
                    is ViewStateResult.Error -> {
                        emit(ViewStateResult.Error(exception))
                    }
                    else -> {
                        emit(ViewStateResult.Loading)
                    }
                }
            }
        }.onStart { emit(ViewStateResult.Loading) }
    }

    override suspend fun getRestaurantById(id: Int): Flow<ViewStateResult<ResponseRestaurantDetail>> {
        return flow {
            aPIHelperImplementationTest.getRestaurantById(id)
                .run {
                    when (this) {
                        is ViewStateResult.Success -> {
                            data?.let { emit(ViewStateResult.Success(it)) }
                        }
                        is ViewStateResult.Error -> {
                            emit(ViewStateResult.Error(exception))
                        }
                        else -> {
                            emit(ViewStateResult.Loading)
                        }
                    }
                }
        }.onStart { emit(ViewStateResult.Loading) }
    }
}