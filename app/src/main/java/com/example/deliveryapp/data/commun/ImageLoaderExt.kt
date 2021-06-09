package com.example.deliveryapp.data.commun


import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.deliveryapp.R
import com.example.deliveryapp.di.GlideApp


private const val FILE_SCHEME = "file"

// Load callback provided if there is any task to perform once image is loaded or failed.
@SuppressLint("CheckResult")
fun ImageView.load(imageUrl: String, loadCallback: (() -> Unit)? = null) {

    if (imageUrl.isBlank()) {
        // Load placeholder if image is missing maybe?
        GlideApp.with(context).load(setImageResource(R.mipmap.ic_launcher))

    } else {
        if (isLoadable()) {

            // don't use GlideUrl for local files as it causes refresh problems
            val request = if (Uri.parse(imageUrl).scheme == FILE_SCHEME) {
                GlideApp.with(context).asGif().load(imageUrl)
            } else {
                GlideApp.with(context)
                    .load(DecodedImageUrl(imageUrl))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .skipMemoryCache(true)
                    .override(1500, 1500)
            }

            if (loadCallback != null) {
                request.listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        post { loadCallback() }
                        return false
                    }
                } as Nothing)
            } else {
                request
            }
                .into(this)
        }
    }
}

fun ImageView.loadCircleCrop(imageUri: String) {
    if (isLoadable()) {
        // don't use GlideUrl for local files as it causes refresh problems
        if (Uri.parse(imageUri).scheme == FILE_SCHEME) {
            GlideApp.with(context)
                .load(imageUri)
                .circleCrop()
                .into(this)

        } else {
            GlideApp.with(context)
                .load(DecodedImageUrl(imageUri))
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true)
                .into(this)
        }
    }
}

private fun ImageView.isLoadable() = if (context is Activity) {
    val activity = context as Activity
    (!activity.isFinishing && !activity.isDestroyed)
} else {
    true
}


