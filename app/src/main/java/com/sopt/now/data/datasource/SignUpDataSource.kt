package com.sopt.now.data.datasource

import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseSignUpDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpDataSource {
    @POST("/member/join")
    suspend fun signUp(@Body signUpRequest: RequestSignUpDto): Response<ResponseSignUpDto>
}