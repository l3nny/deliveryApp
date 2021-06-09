package com.example.deliveryapp.data.model

data class ResponseRestaurantDetail(
    val id: Int,
    val name: String,
    val description: String,
    val cover_img_url: String,
    val header_img_url: String?,
    val status: String,
    val menus: List<Menus>,
    val average_rating: Double,
)




