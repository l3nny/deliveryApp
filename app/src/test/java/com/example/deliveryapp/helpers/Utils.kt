package com.example.deliveryapp.helpers

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.google.common.io.Resources.getResource
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File


internal fun getJson(path: String): String {
    val uri = getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}

internal inline fun <reified T> fromJsonToObject(json: String ): T?{
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter : JsonAdapter<T> = moshi.adapter(T::class.java)
    return jsonAdapter.fromJson(json)

}