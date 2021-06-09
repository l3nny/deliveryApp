package com.example.deliveryapp.data.remote

import com.example.deliveryapp.data.commun.ViewStateResult
import com.example.deliveryapp.data.model.ResponseRestaurantDetail
import com.example.deliveryapp.data.model.Restaurant

interface ApiHelper {
    suspend fun getRestaurants(): ViewStateResult<Restaurant?>
    suspend fun getRestaurantById(id:Int): ViewStateResult<ResponseRestaurantDetail?>
}