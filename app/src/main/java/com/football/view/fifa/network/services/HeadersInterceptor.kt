package com.football.view.fifa.network.services

import com.football.view.fifa.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HeadersInterceptor @Inject constructor(
) : Interceptor {

    companion object {
        private const val CLIENT_ID = BuildConfig.CLIENT_ID
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", CLIENT_ID)
            .build()
        return chain.proceed(request)
    }
}