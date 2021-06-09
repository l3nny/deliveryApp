package com.example.deliveryapp.ui.menuPopularItems

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryapp.data.model.Store
import com.example.deliveryapp.databinding.ListMenuPopularItemsBinding
import com.example.deliveryapp.ui.detail.MainActivityDetail
import com.example.deliveryapp.ui.detail.RestaurantDetailsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuPopularItemsFragment : Fragment() {


    private lateinit var adapter: MenuRestaurantDetailAdapter
    private lateinit var binding: ListMenuPopularItemsBinding
    private val store: Store? by lazy {
        requireActivity().intent.extras?.getParcelable(MainActivityDetail.EXTRA) as? Store
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MenuRestaurantDetailAdapter{
            findNavController().navigate(RestaurantDetailsFragmentDirections.actionFirstFragmentToSecondFragment(it))
           // startActivity(activity?.let { it1 -> MainActivityDetailMenuPopular.getIntent(it1, it) })
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        store?.let {
            adapter.submitList(it.menus[0].popular_items ?: listOf())
            setupAdapter()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListMenuPopularItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerView.adapter = adapter
    }

}