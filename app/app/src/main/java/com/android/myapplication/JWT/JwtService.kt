package com.android.myapplication.JWT

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface JwtService {

    @POST("/register")
    suspend fun registerTest(@Body data: User):UserDto

    @GET("/users")
    suspend fun usersTest():String

    @GET("/user/get")
    suspend fun userGetTest(@Header("Authorization") Authorization: String): User

    @GET("/refresh")
    suspend fun refreshTest(@Query("accessToken") accessToken: String, @Header("RefreshToken") RefreshToken: String): TokenDto

    @GET("/login")
    suspend fun loginTest(@Query("email") email: String, @Query("passWord") passWord: String): TokenDto
}