package com.example.deliveryapp.data.commun

import android.content.Context
import android.icu.text.NumberFormat
import android.widget.Toast
import okhttp3.ResponseBody
import java.time.Instant
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt


// See https://developer.android.com/reference/kotlin/android/icu/util/Currency
// Defaulting to US currency here (will show up properly regardless of current locale)

fun formatCurrency(price: String): String {
    val temp = addDot(price)

   return NumberFormat.getCurrencyInstance(Locale.US).format(temp.toBigDecimal())
}

fun addDot(text: String): String {
    if (text.length >= 4) {
        return text.substring(0, 2) + "." + text.substring(3)
    } else if (text.length == 3) {
        return text.substring(0, 1) + "." + text.substring(1)
    }
    return text
}

/*fun sum(numbers: List<Any>): Double {
    var sum = 0.0

    for (item in numbers) {
        val temp = when (item) {
            is String -> item.toDouble()
            is Number -> item.toDouble()
            else -> error("Unsupported type")
        }
        sum += temp
    }

    return sum
}*/

fun isClose(openTime: String): Boolean {
    return (Instant.parse(openTime) > Instant.now())
}

fun Double.roundTo(numFractionDigits: Int): Double {
    val factor = 10.0.pow(numFractionDigits.toDouble())
    return (this * factor).roundToInt() / factor
}

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.showError(error: DataSourceException) {
    when (error.messageResource) {
        is Int -> toast(getString(error.messageResource))
        is ResponseBody? -> toast(error.messageResource!!.string())
    }

}

