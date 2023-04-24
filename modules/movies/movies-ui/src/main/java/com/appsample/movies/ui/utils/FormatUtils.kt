package com.appsample.movies.ui.utils

import android.content.Context
import com.appsample.movies.ui.R
import java.text.NumberFormat
import java.util.*

object FormatUtils {

    fun formatRating(context: Context, rating: Float): String {
        return context.getString(R.string.rating_label, formatRatingValue(rating))
    }

    private fun formatRatingValue(rating: Float): String {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        formatter.minimumFractionDigits = 1
        formatter.maximumFractionDigits = 1
        return formatter.format(rating)
    }

    fun formatMoney(context: Context, money: Int): String {
        return context.getString(R.string.dollar_label, formatMoneyValue(money))
    }

    private fun formatMoneyValue(money: Int): String {
        return NumberFormat.getNumberInstance(Locale.US).format(money)
    }
}
