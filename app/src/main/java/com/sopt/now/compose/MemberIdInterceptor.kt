package com.sopt.now.compose

import okhttp3.Interceptor
import okhttp3.Response

object MemberIdInterceptor : Interceptor {
    var memberId: String? = null
        private set

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // 요청에 MemberID 헤더 추가
        memberId?.let {
            request = request.newBuilder()
                .addHeader("MemberID", it)
                .build()
        }

        val response = chain.proceed(request)

        // 응답 헤더에서 Location 값 추출해서 memberId에 저장
        response.header("Location")?.let {
            memberId = it
        }

        return response
    }
}
