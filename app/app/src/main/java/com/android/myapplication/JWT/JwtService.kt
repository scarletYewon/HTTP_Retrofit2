package com.android.myapplication.JWT

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface JwtService {
//    @GET("/api/test")
//    suspend fun getData(): String
//
//    @POST("/api/path-test/{temp}")
//    suspend fun pathTest(@Path("temp") temp: String): String
//
//    @GET("/api/param-test")
//    suspend fun paramTest(@Query("temp") temp: String): String
//
//    @POST("/api/header-test")
//    suspend fun headerTest(@Header("token") token:String): String
//
//    @POST("/api/body-test")
//    suspend fun bodyTest(@Body data:MyRequestData): String
//
//    @GET("/user")
//    suspend fun userTest(@Query("id") id: Int): User
//
//    @POST("/user")
//    suspend fun signTest(@Body data:User): User
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