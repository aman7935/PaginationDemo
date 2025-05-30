package com.example.paginationdemo.api

import androidx.lifecycle.MutableLiveData
import com.example.paginationdemo.model.Data
import com.example.paginationdemo.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface UserApi {


    @GET("/api/admin/getusers")
    suspend fun getUsers(@HeaderMap headers: Map<String, String>, @Query("role") role: String, @Query("page") page: Int, @Query("limit") limit : Int): Response<UserResponse>

}