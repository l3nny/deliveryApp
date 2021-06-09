package com.example.deliveryapp.data.repository

import com.example.deliveryapp.data.commun.ViewStateResult
import com.example.deliveryapp.data.commun.onFlowStarts
import com.example.deliveryapp.data.model.ResponseRestaurantDetail
import com.example.deliveryapp.data.model.Restaurant
import com.example.deliveryapp.data.remote.ApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiHelper
) : Repository {
    override suspend fun getRestaurants(): Flow<ViewStateResult<Restaurant>> =
        flow {
            api.getRestaurants().run {
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
        }.onFlowStarts()

    override suspend fun getRestaurantById(id: Int): Flow<ViewStateResult<ResponseRestaurantDetail>> =
        flow {
            api.getRestaurantById(id).run {
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
        }.onFlowStarts()
}