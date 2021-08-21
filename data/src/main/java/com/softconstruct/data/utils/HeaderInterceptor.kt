package com.softconstruct.data.utils

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(HEADER_KEY, HEADER_VALUE)
            .build()
        return chain.proceed(request)
    }
}