package com.example.ecommercekotlin.model.entity

data class Cart(
    val color_id: String,
    val created_at: String,
    val id: Int,
    val product_id: String,
    val quantity: String,
    val size_id: String,
    val updated_at: String,
    val user_id: Int
)
