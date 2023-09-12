package com.football.view.fifa.util.idmodule

import com.football.view.fifa.network.services.FIFAImageService
import com.football.view.fifa.network.services.FIFAMetadataService
import com.football.view.fifa.network.services.HeadersInterceptor
import com.football.view.fifa.network.services.FIFAService
import com.football.view.fifa.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkProviderModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaMetadataOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaMetadataRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaRetrofit


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaImageOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaImageRetrofit







    @FifaMetadataOkHttpClient
    @Provides
    @Singleton
    fun provideFifaMetadataOkHttpClient(
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(headersInterceptor)
            .build()
    }
    @FifaMetadataRetrofit
    @Provides
    @Singleton
    fun provideFifaMetadataRetrofit(@FifaMetadataOkHttpClient okHttpClient: OkHttpClient): FIFAMetadataService {
        return Retrofit.Builder()
            .baseUrl(Constants.METADATA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build().create(FIFAMetadataService::class.java)
    }

    @FifaOkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(headersInterceptor)
            .build()
    }
    @FifaRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(@FifaOkHttpClient okHttpClient: OkHttpClient): FIFAService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build().create(FIFAService::class.java)
    }

    @FifaImageOkHttpClient
    @Provides
    @Singleton
    fun provideFifaImageOkHttpClient(
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(headersInterceptor)
            .build()
    }
    @FifaImageRetrofit
    @Provides
    @Singleton
    fun provideFifaImageRetrofit(@FifaImageOkHttpClient okHttpClient: OkHttpClient): FIFAImageService {
        return Retrofit.Builder()
            .baseUrl(Constants.IMAGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build().create(FIFAImageService::class.java)
    }


}

//import com.example.view.fifa.network.services.HeadersInterceptor
//import com.example.view.fifa.network.services.FIFAService
//import com.example.view.fifa.util.Constants
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import io.reactivex.schedulers.Schedulers
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Qualifier
//import javax.inject.Singleton
//@Module
//@InstallIn(SingletonComponent::class)
//class NetworkProviderModule {
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        headersInterceptor: HeadersInterceptor
//    ): OkHttpClient {
//        val logInterceptor = HttpLoggingInterceptor()
//        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return OkHttpClient.Builder()
//            .addInterceptor(logInterceptor)
//            .addInterceptor(headersInterceptor)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): FIFAService {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .client(okHttpClient)
//            .build().create(FIFAService::class.java)
//    }
//}