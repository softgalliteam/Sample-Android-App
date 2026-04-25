package com.learning.exp.model

import com.learning.exp.model.dataclasses.ResponseData
import retrofit2.http.GET

interface ApiCallRetrofitService {
    @GET("objects")
    suspend fun getComputerList(): ResponseData
}