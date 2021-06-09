package com.example.deliveryapp.data.repository

import com.example.deliveryapp.data.commun.ViewStateResult
import com.example.deliveryapp.data.model.ResponseRestaurantDetail
import com.example.deliveryapp.data.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getRestaurants(): Flow<ViewStateResult<Restaurant>>
    suspend fun getRestaurantById(id: Int): Flow<ViewStateResult<ResponseRestaurantDetail>>
}