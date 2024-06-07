package com.sopt.now

import com.sopt.now.data.model.RequestLoginDto
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseLoginDto
import com.sopt.now.data.model.ResponseSignUpDto
import com.sopt.now.data.model.ResponseUserInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(@Body signUpDto: RequestSignUpDto): Response<ResponseSignUpDto>

    @POST("/member/login")
    suspend fun login(@Body loginDto: RequestLoginDto): Response<ResponseLoginDto>


    @GET("/member/info")
    suspend fun getUserInfo(): Response<ResponseUserInfoDto>
}