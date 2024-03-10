package com.android.myapplication.JWT

import android.hardware.camera2.params.TonemapCurve
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.myapplication.HTTP.ExceptionDto
import com.android.myapplication.R
import com.android.myapplication.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.Types.NULL

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val jwtService = RetrofitClient.jwtservice

        var globalAccessToken:String = ""
        var globalRefreshToken:String = ""

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        findViewById<Button>(R.id.btn_register).setOnClickListener{
            val data = User("abcd1234@gmail.com","abcd1234","USER")
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = jwtService.registerTest(data)
                    Log.e("Response", responseData.toString())
                    // 응답 데이터를 사용하여 작업 수행
                } catch (e: Exception) {
                    if (e is retrofit2.HttpException){
                        if (e.code() == 400){
                            val errorBody = e.response()?.errorBody()?.string()
                            val gson = Gson()
                            val errorResponse : ExceptionDto? = gson.fromJson(errorBody, ExceptionDto::class.java)
                            Log.e("400에러",errorResponse.toString())
                        }else {
                            Log.e("Error", e.message.toString())
                        }
                    } else {
                        Log.e("Error", e.message.toString())
                    }
                }
            }
        }
        findViewById<Button>(R.id.btn_users).setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = jwtService.usersTest()
                    // 응답 데이터를 사용하여 작업 수행
                    Log.e("Response", responseData.toString())
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
        findViewById<Button>(R.id.btn_user_get).setOnClickListener{
            val token = "Bearer $globalAccessToken"
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = jwtService.userGetTest(token)
                    Log.e("Response", responseData.toString())
                    // 응답 데이터를 사용하여 작업 수행
                } catch (e: Exception) {
                    if (e is retrofit2.HttpException){
                        if (e.code() == 404){
                            val errorBody = e.response()?.errorBody()?.string()
                            val gson = Gson()
                            val errorResponse : ExceptionDto? = gson.fromJson(errorBody, ExceptionDto::class.java)
                            Log.e("404에러",errorResponse.toString())
                        }else {
                            Log.e("Error", e.message.toString())
                        }
                    } else {
                        Log.e("Error", e.message.toString())
                    }
                }
            }
        }
        findViewById<Button>(R.id.btn_refresh).setOnClickListener{
            val accessToken = "Bearer $globalAccessToken"
            val refreshToken = "Bearer $globalRefreshToken"
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = jwtService.refreshTest(accessToken,refreshToken)
                    Log.e("Response", responseData.toString())
                    globalAccessToken = responseData.accessToken
                    globalRefreshToken = responseData.refreshToken
                    // 응답 데이터를 사용하여 작업 수행
                } catch (e: Exception) {
                    if (e is retrofit2.HttpException){
                        if (e.code() == 401){
                            val errorBody = e.response()?.errorBody()?.string()
                            val gson = Gson()
                            val errorResponse : ExceptionDto? = gson.fromJson(errorBody, ExceptionDto::class.java)
                            Log.e("401에러",errorResponse.toString())
                        }else if(e.code()==404){
                            val errorBody = e.response()?.errorBody()?.string()
                            val gson = Gson()
                            val errorResponse : ExceptionDto? = gson.fromJson(errorBody, ExceptionDto::class.java)
                            Log.e("404에러",errorResponse.toString())
                        } else {
                            Log.e("Error", e.message.toString())
                        }
                    } else {
                        Log.e("Error", e.message.toString())
                    }
                }
            }
        }
        findViewById<Button>(R.id.btn_login).setOnClickListener{
            val email = "abcd1234@gmail.com"
            val passWord = "abcd1234"
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = jwtService.loginTest(email,passWord)
                    // 응답 데이터를 사용하여 작업 수행
                    Log.e("Response", responseData.toString())
                    // 받은 토큰 저장
                    globalAccessToken = responseData.accessToken
                    globalRefreshToken = responseData.refreshToken
                } catch (e: Exception) {
                    if (e is retrofit2.HttpException){
                        if (e.code() == 404){
                            val errorBody = e.response()?.errorBody()?.string()
                            val gson = Gson()
                            val errorResponse : ExceptionDto? = gson.fromJson(errorBody, ExceptionDto::class.java)
                            Log.e("404에러",errorResponse.toString())
                        }else {
                            Log.e("Error", e.message.toString())
                        }
                    } else {
                        Log.e("Error", e.message.toString())
                    }
                }
            }
        }
    }
}