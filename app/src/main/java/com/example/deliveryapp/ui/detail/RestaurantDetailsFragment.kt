package com.example.deliveryapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.deliveryapp.data.model.Store
import com.example.deliveryapp.databinding.FragmentRestaurantDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import com.example.deliveryapp.data.commun.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class RestaurantDetailsFragment : Fragment() {

    private val viewModel: RestaurantDetailViewModel by viewModels()
    private lateinit var binding: FragmentRestaurantDetailsBinding
    private val store: Store? by lazy {
        requireActivity().intent.extras?.getParcelable(MainActivityDetail.EXTRA) as? Store
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        store?.let {
            viewModel.getRestaurantsById(it.id)
            binding.distance.text = it.distance_from_consumer.roundTo(2).toString() + " Mi"
            it.header_img_url.let { it1 -> binding.imageView.load(it1) }
            binding.nameRestaurant.text = it.name
            initObserver()

        }
    }


    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.listLive.collect {
                it.onSuccess { restaurant ->
                    binding.progressBar.visibility = View.GONE
                    binding.imageView3.loadCircleCrop(restaurant.cover_img_url)
                    binding.description.text = restaurant.description
                    binding.rating.text = restaurant.average_rating.toString()
                }
                    .onError {
                        binding.progressBar.visibility = View.GONE
                    }.onLoading {
                        binding.progressBar.visibility = View.VISIBLE
                    }
            }
        }
    }


}