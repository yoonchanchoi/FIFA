package com.example.searchstudy.network.services


//import com.example.searchstudy.BuildConfig
import com.example.view.fifa.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HeadersInterceptor @Inject constructor(
//    private val prefs: Prefs
) : Interceptor {

    companion object {
        private const val CLIENT_ID = BuildConfig.CLIENT_ID
    }


//    companion object {
//        private const val CLIENT_ID = BuildConfig.CLIENT_ID
////        private const val CLIENT_SECRET = BuildConfig.CLIENT_SECRET
//    }


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization",CLIENT_ID)
            .build()
        return chain.proceed(request)
    }
}