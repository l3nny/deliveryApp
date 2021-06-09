package com.example.deliveryapp.data.remote

import com.example.deliveryapp.data.model.ResponseRestaurantDetail
import com.example.deliveryapp.data.model.Restaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("v1/store_feed")
    suspend fun getRestaurants(@Query("lat") lat: Double = 37.422740,
                               @Query("lng") lng: Double = -122.139956,
                               @Query("limit") limit: Int = 50,
                               @Query("offset") offset: Int? = 0 ): Response<Restaurant>

    @GET("v2/restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id: Int): Response<ResponseRestaurantDetail>

}

