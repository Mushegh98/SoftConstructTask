package com.softconstruct.data.dataservice.appservice

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class PreferenceServiceImpl(private val context: Context): PreferenceService {

    val sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    inline fun <reified T> get(key: String): T? {
        val value = sharedPreferences.getString(key, null)
        return value?.let {
            val jsonAdapter: JsonAdapter<T> =
                Moshi.Builder().build().adapter(T::class.java)
            jsonAdapter.fromJson(it)
        }
    }

    inline fun <reified T> put(data: T, key: String): Boolean {
        val jsonAdapter: JsonAdapter<T> =
            Moshi.Builder().build().adapter(T::class.java)
        val jsonString = jsonAdapter.toJson(data)
        return sharedPreferences.edit().putString(key, jsonString).commit()
    }
}