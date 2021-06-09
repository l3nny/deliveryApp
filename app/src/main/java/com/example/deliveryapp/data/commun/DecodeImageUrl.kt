package com.example.deliveryapp.data.commun

import com.bumptech.glide.load.model.GlideUrl
import java.net.URL

class DecodedImageUrl(private val url: String) : GlideUrl(url) {

    // In the future, may need to do more than just this replace
    override fun toStringUrl() = url.replace("%24", "$")

    override fun toURL() = URL(toStringUrl())

}