package com.sopt.now

import com.sopt.now.data.model.RequestLoginDto
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseLoginDto
import com.sopt.now.data.model.ResponseSignUpDto
import com.sopt.now.data.model.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>

    @POST("/member/login")
    fun login(
        @Body request: RequestLoginDto
    ): Call<ResponseLoginDto>

    @GET("/member/info")
    fun getUserInfo(@Header("memberId") memberId: String): Call<ResponseUserInfoDto>
}