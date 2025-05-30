package com.example.paginationdemo.model

data class Pagination(
    val limit: Int,
    val page: Int,
    val record: Int,
    val total: Int,
    val totalPages: Int
)