package com.example.ecommercekotlin.model.entity

data class Cart2(
    val color_id: Int,
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val product: Product,
    val product_id: Int,
    val quantity: Int,
    val size_id: Int,
    val updated_at: String,
    val user_id: Int
)