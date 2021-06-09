package com.example.deliveryapp.ui.detailMenuPopularitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.deliveryapp.R
import com.example.deliveryapp.data.commun.formatCurrency
import com.example.deliveryapp.data.commun.load
import com.example.deliveryapp.databinding.MenuPopularItemDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMenuPopularItemsFragment : DialogFragment() {

    private lateinit var binding: MenuPopularItemDetailBinding
   /* private val menuItem: MenuItem? by lazy {
        requireActivity().intent.extras?.getParcelable(MainActivityDetailMenuPopular.EXTRA) as? MenuItem
    } */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MenuPopularItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val menuItemARG = arguments?.let {
            com.example.deliveryapp.ui.detailMenuPopularitems.DetailMenuPopularItemsFragmentArgs.fromBundle(
                it
            ).item
        }
        menuItemARG?.let {

            it.img_url.let { it1 ->
                binding.imageItem.load(it1)
                binding.descriptionItem.text = it.description
                binding.menuName.text = it.name
                binding.menuPrice.text = formatCurrency(it.price.toString())

            }
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)


    }

}