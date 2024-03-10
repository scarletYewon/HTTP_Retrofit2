package com.android.myapplication.HTTP

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// API 인터페이스 정의
interface ApiService {
    @GET("/api/test")
    suspend fun getData(): String

    @POST("/api/path-test/{temp}")
    suspend fun pathTest(@Path("temp") temp: String): String

    @GET("/api/param-test")
    suspend fun paramTest(@Query("temp") temp: String): String

    @POST("/api/header-test")
    suspend fun headerTest(@Header("token") token:String): String

    @POST("/api/body-test")
    suspend fun bodyTest(@Body data: MyRequestData): String

    @GET("/user")
    suspend fun userTest(@Query("id") id: Int): User

    @POST("/user")
    suspend fun signTest(@Body data: User): User
}