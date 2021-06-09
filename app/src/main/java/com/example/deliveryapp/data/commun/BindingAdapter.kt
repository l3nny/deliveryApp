package com.example.deliveryapp.data.commun


import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("src")
    fun setImage(view: ImageView, url: String) {
        view.load(url)
    }


    @JvmStatic
    @BindingAdapter(
        value = ["textPrice"],
    )
    fun setText(view: TextView, amount: Int) {
        view.text = formatCurrency(amount.toString())
    }

    @JvmStatic
    @BindingAdapter(
        value = ["textShort"],
    )
    fun setTextShort(view: TextView, text: String) {
        val temp = text.split(",")

        val temp2 = when {
            temp.size > 1 -> {
                temp[0] + "," + temp[1]
            }
            temp.isEmpty() -> {
                "Empty"
            }
            else -> {
                temp[0]
            }
        }
        view.text = temp2
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter(
        value = ["text1", "text2"],
    )
    fun setTextDynamic(
        view: TextView,
        openTime: String,
        waitTimeMinutes: List<Int>
    ) {
        if (isClose(openTime)) {
            view.text = "Closed"
        } else {
            view.text = waitTimeMinutes.first().toString()+" Mins"
        }

    }
}