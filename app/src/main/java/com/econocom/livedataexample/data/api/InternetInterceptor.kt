package com.econocom.livedataexample.data.api

import com.econocom.livedataexample.data.api.model.exceptions.NoInternetError
import okhttp3.Interceptor
import okhttp3.Response

class InternetInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            return chain.proceed(request)
        } catch (throwable: Throwable) {
            throw NoInternetError()
        }
    }
}