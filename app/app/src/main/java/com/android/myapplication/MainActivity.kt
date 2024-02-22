package com.android.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Retrofit 인스턴스 생성
        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://49.142.8.5:8080/") // 실제 엔드포인트 URL로 변경해야 합니다
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        // API 서비스 인스턴스 생성
        val apiService = retrofit.create(ApiService::class.java)

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
    }

}