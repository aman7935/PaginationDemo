package com.example.paginationdemo

import androidx.lifecycle.MutableLiveData
import com.example.paginationdemo.api.RetrofitInstance
import com.example.paginationdemo.api.UserApi
import com.example.paginationdemo.model.Data
import com.example.paginationdemo.model.UserResponse
import retrofit2.Response

class UserRepository(private val apiService: UserApi) {
    suspend fun getUsers(page: Int, auth : Map<String, String>,role: String, limit : Int): Response<UserResponse> {
        return RetrofitInstance.userApi.getUsers( auth,role,page,limit )
    }
}
