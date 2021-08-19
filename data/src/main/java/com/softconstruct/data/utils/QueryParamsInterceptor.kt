package com.softconstruct.data.utils

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class QueryParamsInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter(PAGE_SIZE_KEY, PAGE_SIZE_VALUE.toString())
            .addQueryParameter(SHOW_FIELDS_KEY, SHOW_FIELDS_VALUE)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}