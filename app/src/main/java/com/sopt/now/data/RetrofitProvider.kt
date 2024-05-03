package com.sopt.now.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitProvider {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL

    // HTTP 로깅 인터셉터 설정
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // OkHttpClient 설정
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)  // 로깅 인터셉터 추가
        .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃
        .readTimeout(30, TimeUnit.SECONDS)    // 읽기 타임아웃
        .writeTimeout(30, TimeUnit.SECONDS)   // 쓰기 타임아웃
        .build()

    // Retrofit 인스턴스 생성
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
}
