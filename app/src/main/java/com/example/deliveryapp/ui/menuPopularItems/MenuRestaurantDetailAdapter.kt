package com.example.deliveryapp.ui.menuPopularItems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.R
import com.example.deliveryapp.data.model.MenuItem


class MenuRestaurantDetailAdapter (private val clickListener: (MenuItem) -> Unit): RecyclerView.Adapter<MenuRestaurantDetailAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.menu_popular_items, parent, false
            )
        )
    }

    inner class MenuViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bindViewHolder(itemClass: MenuItem, clickListener: (MenuItem) -> Unit) {
            viewDataBinding.setVariable(BR.item, itemClass)
            viewDataBinding.root.setOnClickListener{
                clickListener(itemClass)
            }
            viewDataBinding.executePendingBindings()
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<MenuItem>) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindViewHolder(differ.currentList[position], clickListener)

    }
}




