package com.example.deliveryapp.ui.main

import com.example.deliveryapp.data.commun.SharedPreferenceUtil
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.R
import androidx.databinding.library.baseAdapters.BR
import com.example.deliveryapp.data.commun.get
import com.example.deliveryapp.data.commun.remove
import com.example.deliveryapp.data.commun.set
import com.example.deliveryapp.data.model.Store

class RestaurantListAdapter(private val clickListener: (Store) -> Unit) :
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_list_restaurant, parent, false
            )
        )
    }

    inner class RestaurantViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        private val prefs: SharedPreferences by lazy {
            SharedPreferenceUtil.customPrefs(
                viewDataBinding.root.context,
                "favorites"
            )
        }
        private val image: ImageView = viewDataBinding.root.findViewById(R.id.imageButton)

        fun bindViewHolder(itemClass: Store, clickListener: (Store) -> Unit) {


            viewDataBinding.setVariable(BR.item, itemClass)
            viewDataBinding.root.setOnClickListener {
                clickListener(itemClass)
            }
            viewDataBinding.executePendingBindings()




            var value: Int? = prefs[itemClass.id.toString()]
            if (value.toString().isNotEmpty() && value.toString() != "-1") {
                image.setImageResource(R.drawable.ic_baseline_star_24)
            }

            image.setOnClickListener {
                value = prefs[itemClass.id.toString()]
                if (value.toString().isNotEmpty() && value.toString() != "-1") {
                    image.setImageResource(R.drawable.ic_baseline_star_border_24)
                    prefs.remove(value.toString())
                } else {
                    prefs[itemClass.id.toString()] = itemClass.id
                    image.setImageResource(R.drawable.ic_baseline_star_24)
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Store>) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bindViewHolder(differ.currentList[position], clickListener)
    }
}
















