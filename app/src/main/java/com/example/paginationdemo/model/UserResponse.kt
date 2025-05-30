package com.example.paginationdemo.model

data class UserResponse(
    val `data`: Data,
    val message: String,
    val success: Boolean
)