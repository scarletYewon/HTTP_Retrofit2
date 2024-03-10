package com.android.myapplication

import com.android.myapplication.HTTP.ApiService
import com.android.myapplication.JWT.JwtService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    val apiservice: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://49.142.8.5:8080/") // 실제 엔드포인트 URL로 변경해야 합니다
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }
    val jwtservice: JwtService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jwt-test.run.goorm.io") // 실제 엔드포인트 URL로 변경해야 합니다
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(JwtService::class.java)
    }
}