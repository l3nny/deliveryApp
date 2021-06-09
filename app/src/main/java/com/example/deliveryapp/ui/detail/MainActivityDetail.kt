package com.example.deliveryapp.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deliveryapp.R
import com.example.deliveryapp.data.model.Store
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)
    }

    companion object {
        const val EXTRA = "restaurant"
        @JvmStatic fun getIntent(context: Context, store: Store): Intent {
             return Intent(context, MainActivityDetail::class.java).apply {
                putExtra(EXTRA, store)
            }
        }
    }
}