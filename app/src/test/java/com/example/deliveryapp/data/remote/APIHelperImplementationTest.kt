package com.example.deliveryapp.data.remote

import com.example.deliveryapp.R
import com.example.deliveryapp.data.commun.DataSourceException
import com.example.deliveryapp.data.commun.ViewStateResult
import com.example.deliveryapp.data.model.ResponseRestaurantDetail
import com.example.deliveryapp.data.model.Restaurant
import com.example.deliveryapp.helpers.fromJsonToObject
import com.example.deliveryapp.helpers.getJson

class APIHelperImplementationTest : ApiHelper {
    override suspend fun getRestaurants(): ViewStateResult<Restaurant?> {
        val result = fromJsonToObject<Restaurant>(getJson("restaurant.json"))

        return if (result != null) {
            ViewStateResult.Success(result)
        } else {
            ViewStateResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }


    override suspend fun getRestaurantById(id: Int): ViewStateResult<ResponseRestaurantDetail?> {
        val result = fromJsonToObject<ResponseRestaurantDetail>(getJson("store.json"))

        return if (result != null) {
            ViewStateResult.Success(result)
        } else {
            ViewStateResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }


}