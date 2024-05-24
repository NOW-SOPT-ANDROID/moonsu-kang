package com.sopt.now.data.datasource

import com.sopt.now.data.model.RequestLoginDto
import com.sopt.now.data.model.ResponseLoginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginDataSource {
    @POST("/member/login")
    suspend fun login(@Body loginRequest: RequestLoginDto): Response<ResponseLoginDto>
}