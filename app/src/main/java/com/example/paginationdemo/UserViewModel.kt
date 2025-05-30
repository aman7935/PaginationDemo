package com.example.paginationdemo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paginationdemo.model.Data
import com.example.paginationdemo.model.User
import com.example.paginationdemo.model.UserResponse
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

//    private val _users = MutableLiveData<List<UserResponse>>()
//    val users: LiveData<List<UserResponse>> = _users

     val _users = MutableLiveData<Data>()
//    val users: LiveData<List<Data>> = _users


    /*var currentPage = 1
    var isLoading = false
    var totalPages = 1
    val currentUserList = mutableListOf<Data>()*/

    /*fun loadUsers() {
        *//*if (isLoading || currentPage > totalPages) return
        isLoading = true*//*

        viewModelScope.launch {
            val response = repository.getUsers(currentPage)
            response?.let {
                totalPages = it.body()?.total_pages ?: 0
                currentUserList.addAll(it.body()?.data ?: emptyList())
                _users.value = currentUserList
                currentPage++
            }
            isLoading = false
        }
    }*/

    /*fun loadUser(page : Int, auth : Map<String, String>,role: String, limit : Int) {

        viewModelScope.launch {
            try {
                val response = repository.getUsers(page, auth, role, limit)
                response?.let {
                    _users.postValue(it.body()?.data!!)
                    Log.d(TAG, "loadUser: ${it.body()?.data}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "loadUser: ${e.printStackTrace()}")
            }
        }

    }*/


    /*fun loadUser(page: Int, auth: Map<String, String>, role: String, limit: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getUsers(page, auth, role, limit)
                if (response.isSuccessful) {
                    val userList = response.body()?.data
                    if (userList != null) {
                        _users.postValue(response.body()?.data?.users)
                        Log.d(TAG, "loadUser: ${response.body()?.data?.users}")
                    } else {
                        Log.d(TAG, "loadUser: Data is null")
                    }
                } else {
                    Log.d(TAG, "loadUser: API error - ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "loadUser: Exception", e)
            }
        }
    }*/

    fun loadUser(page: Int, auth: Map<String, String>, role: String, limit: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getUsers(page, auth, role, limit)
                response?.let {
                    val newUsers = it.body()?.data
                    if (newUsers?.users?.isNotEmpty()==true) {
                        _users.postValue(newUsers)
                    }
                    else{
                        Log.d(TAG, "loadUser: Data is null")
                    }
                    Log.d(TAG, "Loaded page $page with ${newUsers?.users?.size} users.")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error loading users: ${e.localizedMessage}")
            }
        }
    }


}