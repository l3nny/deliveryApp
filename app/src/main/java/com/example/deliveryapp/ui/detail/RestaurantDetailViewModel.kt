package com.example.deliveryapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.data.commun.ViewStateResult
import com.example.deliveryapp.data.model.ResponseRestaurantDetail
import com.example.deliveryapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel  @Inject constructor(
    private val repository: Repository

) : ViewModel() {

    private val _listLive = MutableStateFlow<ViewStateResult<ResponseRestaurantDetail>>(ViewStateResult.Loading)

    val listLive: StateFlow<ViewStateResult<ResponseRestaurantDetail>> = _listLive

    fun getRestaurantsById(id : Int) = viewModelScope.launch(Dispatchers.Main) {
        repository.getRestaurantById(id).collect {
            _listLive.emit(it)
        }
    }
}