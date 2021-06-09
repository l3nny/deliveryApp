package com.example.deliveryapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.data.commun.ViewStateResult
import com.example.deliveryapp.data.model.Restaurant
import com.example.deliveryapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: Repository

) : ViewModel() {

    private val _listLive = MutableStateFlow<ViewStateResult<Restaurant>>(ViewStateResult.Loading)

    val listLive: StateFlow<ViewStateResult<Restaurant>> = _listLive

    fun getRestaurants() = viewModelScope.launch(Dispatchers.Main) {
        repository.getRestaurants().collect {
            _listLive.emit(it)
        }
    }
}