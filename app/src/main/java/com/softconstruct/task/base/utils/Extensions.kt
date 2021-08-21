package com.softconstruct.task.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.softconstruct.task.R

fun Context.hasNetwork(): Boolean {
    var isConnected = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        isConnected = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            @Suppress("DEPRECATION")
            connectivityManager.activeNetworkInfo?.run {
                isConnected = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }
    return isConnected
}

fun TextView.setSoftConstructFonts() {
    val font = context?.let { ResourcesCompat.getFont(it, R.font.roboto_black) }
    val font2 = context?.let { ResourcesCompat.getFont(it, R.font.roboto_regular) }
    val spannableString = SpannableStringBuilder(context?.getString(R.string.softconstruct))
    spannableString.setSpan(
        font?.let { CustomTypefaceSpan("", it) },
        0,
        4,
        Spanned.SPAN_EXCLUSIVE_INCLUSIVE
    )
    spannableString.setSpan(
        font2?.let { CustomTypefaceSpan("", it) },
        4,
        12,
        Spanned.SPAN_EXCLUSIVE_INCLUSIVE
    )
    text = spannableString
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}