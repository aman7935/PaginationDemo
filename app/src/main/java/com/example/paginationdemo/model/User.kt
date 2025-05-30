package com.example.paginationdemo.model

data class User(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val designation: String,
    val devices: List<Any>,
    val email: String,
    val isVerified: Boolean,
    val name: String,
    val otp: String,
    val reportingManagerId: ReportingManagerId,
    val role: String,
    val siteId: SiteId,
    val updatedAt: String,
    val userName: String
)