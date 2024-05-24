package com.sopt.now.compose

import okhttp3.Interceptor
import okhttp3.Response

object MemberIdInterceptor : Interceptor {
    var memberId: String? = null
        private set

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // 응답 헤더에서 Location 값 추출해서 memberId에 저장 때리기
        response.header("Location")?.let {
            memberId = it
        }

        return response
    }
}