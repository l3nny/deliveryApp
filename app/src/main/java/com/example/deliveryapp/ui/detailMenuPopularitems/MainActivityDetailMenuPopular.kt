package com.example.deliveryapp.ui.detailMenuPopularitems

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deliveryapp.data.model.MenuItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityDetailMenuPopular: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailMenuPopularItemsFragment().show(supportFragmentManager, "Tablet_specific")
    }

    companion object {
        const val EXTRA = "menuPopular"
        @JvmStatic fun getIntent(context: Context, menuItem: MenuItem): Intent {
            return Intent(context, MainActivityDetailMenuPopular::class.java).apply {
                putExtra(EXTRA, menuItem)
            }
        }
    }
}