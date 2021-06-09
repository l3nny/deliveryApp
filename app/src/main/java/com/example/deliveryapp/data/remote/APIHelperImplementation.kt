package com.example.deliveryapp.data.remote

import com.example.deliveryapp.data.commun.DataSourceException
import com.example.deliveryapp.data.commun.RequestErrorHandler
import com.example.deliveryapp.data.commun.ViewStateResult
import com.example.deliveryapp.data.model.ResponseRestaurantDetail
import com.example.deliveryapp.data.model.Restaurant
import javax.inject.Inject

class APIHelperImplementation @Inject constructor(
    private val apiService: APIService
) : ApiHelper {
    override suspend fun getRestaurants(): ViewStateResult<Restaurant?> {
        return try {
            val result = apiService.getRestaurants()
            if (result.isSuccessful) {
                ViewStateResult.Success(result.body())
            } else {
                ViewStateResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            ViewStateResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getRestaurantById(id:Int): ViewStateResult<ResponseRestaurantDetail?> {
        return try {
            val result = apiService.getRestaurantById(id)
            if (result.isSuccessful) {
                ViewStateResult.Success(result.body())
            } else {
                ViewStateResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            ViewStateResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}

