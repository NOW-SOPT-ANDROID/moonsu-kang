package com.sopt.now.data.datasource

import com.sopt.now.data.model.ResponseUserInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserInfoDataSource {
    @GET("/member/info")
    suspend fun fetchUserInfo(@Header("memberId") memberId: String): Response<ResponseUserInfoDto>
}
