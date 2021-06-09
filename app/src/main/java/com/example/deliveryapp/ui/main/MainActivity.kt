package com.example.deliveryapp.ui.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deliveryapp.databinding.ListRestaurantBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryapp.data.commun.*
import com.example.deliveryapp.ui.detail.MainActivityDetail
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ListRestaurantBinding
    private lateinit var adapter: RestaurantListAdapter
    private val mainViewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ListRestaurantBinding.inflate(layoutInflater)
        setContentView(viewDataBinding.root)
        setupAdapter()
        initObserver()
        geRestaurants()
    }

    private fun setupAdapter() {
        adapter = RestaurantListAdapter {
            startActivity(MainActivityDetail.getIntent(this, it))
        }
        val layoutManager = LinearLayoutManager(this)
        viewDataBinding.recyclerView.layoutManager = layoutManager
        viewDataBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                layoutManager.orientation
            )
        )
        viewDataBinding.recyclerView.adapter = adapter
    }

    private fun geRestaurants() {
        mainViewModel.getRestaurants()
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.listLive.collect {
                it.onSuccess { restaurant ->
                    viewDataBinding.progressBar.visibility = View.GONE
                    adapter.submitList(restaurant.stores)
                }
                    .onError { error ->
                        showError(error)
                        viewDataBinding.progressBar.visibility = View.GONE
                    }.onLoading {
                        viewDataBinding.progressBar.visibility = View.VISIBLE
                    }
            }
        }
    }
}