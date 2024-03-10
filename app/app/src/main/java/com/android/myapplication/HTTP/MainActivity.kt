package com.android.myapplication.HTTP

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.content.Intent
import com.android.myapplication.JWT.MainActivity2
import com.android.myapplication.R
import com.android.myapplication.RetrofitClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val apiService = RetrofitClient.apiservice

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_get).setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = apiService.getData()
                    // 응답 데이터를 사용하여 작업 수행
                    Log.e("Response", responseData.toString())
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
        findViewById<Button>(R.id.btn_post_path).setOnClickListener{
            val temp = "pathTest"
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = apiService.pathTest(temp)
                    // 응답 데이터를 사용하여 작업 수행
                    Log.e("Response", responseData.toString())
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
        findViewById<Button>(R.id.btn_post_parameter).setOnClickListener{
            val temp="hello"
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = apiService.paramTest(temp)
                    // 응답 데이터를 사용하여 작업 수행
                    println("Response: $responseData")
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
        }
        findViewById<Button>(R.id.btn_post_header).setOnClickListener{
            val token="200"
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = apiService.headerTest(token)
                    // 응답 데이터를 사용하여 작업 수행
                    Log.e("Response", responseData.toString())
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
        findViewById<Button>(R.id.btn_post_body).setOnClickListener{
            val requestData = MyRequestData("yewon","1234")
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = apiService.bodyTest(requestData)
                    // 응답 데이터를 사용하여 작업 수행
                    Log.e("Response", responseData.toString())
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
        findViewById<Button>(R.id.btn_status_get).setOnClickListener{
            val id = 1
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = apiService.userTest(id)
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
        findViewById<Button>(R.id.btn_status_post).setOnClickListener{
            val data = User(1,"a123@naver.com","abcd1234")
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val responseData = apiService.signTest(data)
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

        findViewById<Button>(R.id.btn_jwt).setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }

}