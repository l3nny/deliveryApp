package com.example.deliveryapp.ui.detail

import com.example.deliveryapp.data.commun.onError
import com.example.deliveryapp.data.commun.onSuccess
import com.example.deliveryapp.data.remote.APIHelperImplementationTest
import com.example.deliveryapp.data.repository.RepositoryImplTest
import org.junit.Before
import org.junit.Test

class RestaurantDetailViewModelTest {

    private lateinit var viewModel: RestaurantDetailViewModel

    @Before
    fun setup() {
        val repo = RepositoryImplTest(APIHelperImplementationTest())
        viewModel = RestaurantDetailViewModel(repo)
    }

    @Test
    fun testGetRestaurantsById() {
        viewModel.listLive.value.onSuccess { result ->
            assert(result.id == 62087)

        }.onError { error ->
            assert(!error.equals(null))
        }
    }
}