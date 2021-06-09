package com.example.deliveryapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Restaurant(val stores: List<Store>)

@Parcelize
data class Store(
    val id: Int,
    val name: String,
    val description: String,
    val cover_img_url: String,
    val header_img_url: String,
    val next_open_time: String,
    val status: Status,
    val menus: List<Menus>,
    val average_rating: Double,
    val distance_from_consumer: Double
) : Parcelable

@Parcelize
data class Status(val asap_minutes_range: List<Int>?) : Parcelable

@Parcelize
data class Menus(val popular_items: List<MenuItem>?) : Parcelable

@Parcelize
data class MenuItem(
    val price: Int,
    val img_url: String,
    val description: String,
    val name: String,
    val id: Int
) : Parcelable

