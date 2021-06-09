package com.example.deliveryapp.ui.main

import com.example.deliveryapp.data.commun.onError
import com.example.deliveryapp.data.commun.onSuccess
import com.example.deliveryapp.data.remote.APIHelperImplementationTest
import com.example.deliveryapp.data.repository.RepositoryImplTest
import org.junit.Before
import org.junit.Test

class RestaurantViewModelTest {
    private lateinit var viewModel: RestaurantViewModel

    @Before
    fun setup() {
        val repo = RepositoryImplTest(APIHelperImplementationTest())
        viewModel = RestaurantViewModel(repo)
    }

    @Test
    fun testGetRestaurants() {
        viewModel.listLive.value.onSuccess { result ->
            assert(result.stores.size < 0)

        }.onError { error ->
            assert(!error.equals(null))
        }
    }
}