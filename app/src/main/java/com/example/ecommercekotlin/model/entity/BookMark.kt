package com.example.ecommercekotlin.model.entity

data class BookMark(
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val product_id: Int,
    val updated_at: String,
    val user_id: Int,
    val product: Product
)