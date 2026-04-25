package com.learning.exp.model

import android.util.Log
import com.google.gson.Gson
import com.learning.exp.model.dataclasses.ResponseData
import com.learning.exp.utils.Constants.BASE_URL
import com.learning.exp.utils.Constants.BASE_URL_RETROFIT
import com.learning.exp.utils.Constants.TAG
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCalRepository {

    fun getComputerListOkHttp(): ResponseData {

        // OK HHTP Client to make network request
        val client = OkHttpClient()
        val request = Request.Builder().url(BASE_URL).build()
        client.newCall(request).execute().use { response ->
            val myResponse = response.body?.string().toString()
            val computerList = Gson().fromJson(myResponse, ResponseData::class.java)
            Log.d(TAG, computerList.toString())
            return computerList
        }
    }


    suspend fun getComputerListRetrofit(): ResponseData {

        // Retrofit to make network request
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_RETROFIT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiCallRetrofitService::class.java)
        val computerList = apiService.getComputerList()
        return computerList
    }
}
